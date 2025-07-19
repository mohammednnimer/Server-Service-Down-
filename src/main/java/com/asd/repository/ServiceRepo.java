package com.asd.repository;

import com.asd.enums.CertificateStatus;
import com.asd.enums.ServiceStatus;
import com.db.entitie.PanelService;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ServiceRepo  implements PanacheRepository<PanelService> {



    public List<PanelService> ListByDNSAndPORT(String DNS, int PORT) {
        return list("servicePort = ?1 and serviceDns LIKE ?2 and isDeleted = false", PORT, "%" + DNS + "%");
    }
    public List<PanelService> ListByDNS(String DNS) {
        return list("serviceDns like ?1 and isDeleted = false  ","%" + DNS + "%");
    }
    public List<PanelService> ListByPORT(int PORT ) {
        return list("servicePort = ?1 and isDeleted = false ",  PORT );
    }

    public List<PanelService> ListByServerID(String id) {
        return list("server =?1 and isDeleted = false ",id);
    }
    public PanelService getService(String id) {
        return find("id = ?1 and isDeleted = false",id).firstResult();
    }
    public long deleteSereviceById(String id) {
        return delete("id = ?1 ", id);
    }
    public List<PanelService> getAllServices(int limit,int page) {

        int offset = (page-1) * limit;
        PanacheQuery<PanelService> query = find("isDeleted = false");
        if (limit == -1 && page == -1)
            return query.list();

        return find("isDeleted = false").range(offset, limit - 1)
                .list();
    }

    public List<PanelService> getAllDownServices() {
        return find("isDeleted = false and lastShutdown IS NOT NULL").list();
    }

    public PanelService getDownServiceByURL(String url) {
        return find("serviceName = ?1 and isDeleted = false and lastShutdown IS NOT NULL", url).firstResult();
    }

    public PanelService getServiceByURL(String url) {
        return find("serviceName = ?1 and isDeleted = false", url).firstResult();
    }

    @Transactional
    public void updateCertificate(String url, CertificateStatus status) {
        update("certificateStatus = :status where serviceName = :name",
                Parameters.with("status", status).and("name", url));
    }

    @Transactional
    public void updateServiceStatus(String url, ServiceStatus status) {
        update("serviceStatus = :status where serviceName = :name",
                Parameters.with("status", status).and("name", url));
    }

    public List<PanelService> search(String dns, Integer port, String generalSearch) {
        String query = "isDeleted = false";
        if (generalSearch != null && !generalSearch.trim().isEmpty()) {
            String[] terms = generalSearch.trim().split("\\s+");
            for (String term : terms) {
                String lowerTerm = term.toLowerCase();
                query += " AND ("
                        + "LOWER(id) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(serviceDns) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(server) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(serviceName) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(workDirPath) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(createdBy) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(updatedBy) LIKE '%" + lowerTerm + "%' ";

                if (term.matches("\\d+")) {
             //       query += " OR servicePort = " + term + " ";
                    query += " OR CONCAT('', servicePort) LIKE '%" + term + "%' ";
                }
                query += ")";
            }
        }

        if (dns != null && !dns.isEmpty()) {
            query += " AND LOWER(serviceDns) LIKE '%" + dns.toLowerCase() + "%'";
        }

        if (port != null) {
            query += " AND CONCAT('', servicePort) LIKE '%" + port + "%' ";
        }
        query+=" order by creationDate desc ";

        return list(query);
    }










}
