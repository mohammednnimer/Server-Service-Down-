package com.asd.scheduler;
import com.asd.service.Httpsrequest;

import com.asd.service.ServerService;
import com.db.entitie.PanelServer;
import io.quarkus.vertx.ConsumeEvent;
import io.quarkus.scheduler.Scheduled;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ClientRequest {

    private static final int MAX_ATTEMPTS = 3;

    @Inject
    ServerService serverService;

//    @Scheduled(every = "1m")
//    @Blocking
//    void sendRequest() {
//        List<PanelServer> panelServers = serverService.ListAllServer();
//
//        for (PanelServer server : panelServers) {
//            String ip = server.getIpAddress();
//            if (ip == null || ip.isEmpty()) {
//                System.out.println("Invalid IP for server: " + server);
//                continue;
//            }
//            boolean responded = false;
//            try {
//                Httpsrequest client = RestClientBuilder.newBuilder()
//                        .baseUri(URI.create("http://" + ip + ":40006"))
//                        .connectTimeout(3, TimeUnit.SECONDS)
//                        .readTimeout(3, TimeUnit.SECONDS)
//                        .build(Httpsrequest.class);
//
//                for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
//                    try {
//                        Response response = client.broadcast();
//                        if (response.getStatus() == 200) {
//                            responded = true;
//                            break;
//                        }
//                    } catch (Exception e) {
//                        System.out.println("Attempt " + attempt + " failed for server: " + ip + " error: " + e.getMessage());
//                    }
//                }
//            } catch (Exception ex) {
//                System.out.println("Could not create client for: " + ip + " error: " + ex.getMessage());
//            }
//
//            if (!responded) {
//                System.out.println("mohammad nemerrrrrrrrrrrrr");
//                server.setStatus("inactive");
//            }
//        }
// }

}
