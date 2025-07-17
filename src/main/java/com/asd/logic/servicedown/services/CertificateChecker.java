package com.asd.logic.servicedown.services;

import com.asd.logic.servicedown.models.CertificateStatusModel;
import com.asd.logic.servicedown.models.ServiceModel;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CertificateChecker {

    public CertificateStatusModel checkCertificate(ServiceModel serviceModel) {

        String httpsUrl = serviceModel.getServiceUrl();
        HttpsURLConnection connection = null;
        CertificateStatusModel statusModel = new CertificateStatusModel();
        statusModel.setServiceName(serviceModel.getServiceUrl());

        try {
            // Create all-trusting SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create URL and open connection
            URL url = new URL(httpsUrl);
            connection = (HttpsURLConnection) url.openConnection();

            // Apply custom SSL settings
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            connection.setHostnameVerifier((hostname, session) -> true);

            connection.connect();

            // Get server certificate
            X509Certificate certificate = (X509Certificate) connection.getServerCertificates()[0];
            Date expirationDate = certificate.getNotAfter();
            Date currentDate = new Date();

            long diffInMillies = expirationDate.getTime() - currentDate.getTime();
            long daysUntilExpiry = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            statusModel.setExpirationDate(expirationDate);
            statusModel.setDaysUntilExpiry(daysUntilExpiry);

            if (daysUntilExpiry <= 0) {
                statusModel.setStatus("EXPIRED");
                statusModel.setMessage("The certificate has expired.");
            } else if (daysUntilExpiry <= 2) {
                statusModel.setStatus("EXPIRING_SOON");
                statusModel.setMessage("The certificate will expire in " + daysUntilExpiry + " days.");
            } else {
                statusModel.setStatus("VALID");
                statusModel.setMessage("Certificate is valid for " + daysUntilExpiry + " more days.");
            }

        } catch (Exception e) {
            statusModel.setStatus("ERROR");
            statusModel.setMessage("Error checking certificate: " + e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        statusModel.setServiceURL(serviceModel.getServiceUrl());

        return statusModel;
    }
}
