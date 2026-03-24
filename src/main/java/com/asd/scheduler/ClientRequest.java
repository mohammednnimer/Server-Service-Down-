//package com.asd.scheduler;
//import com.asd.service.Httpsrequest;
//
//import com.asd.service.ServerService;
//import com.db.entitie.PanelServer;
//import io.quarkus.vertx.ConsumeEvent;
//import io.quarkus.scheduler.Scheduled;
//
//import io.smallrye.common.annotation.Blocking;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Inject;
//import jakarta.ws.rs.core.Response;
//import org.eclipse.microprofile.rest.client.RestClientBuilder;
//
//import java.net.URI;
//import java.util.List;
//import java.util.concurrent.*;
//
//@ApplicationScoped
//public class ClientRequest {
//
//    @Inject
//    ServerService  serverService;
//    private static final int MAX_ATTEMPTS = 3;
//    private final ExecutorService executor = Executors.newFixedThreadPool(6); // adjust thread pool size as needed
//
//    @Scheduled(every = "1m")
//    @Blocking
//    void sendRequest() {
//        List<PanelServer> panelServers = serverService.ListAllServer();
//
//        List<Future<?>> futures = new CopyOnWriteArrayList<>();
//
//        for (PanelServer server : panelServers) {
//            Future<?> future = executor.submit(new Runnable() {
//                @Override
//                public void run() {
//                    processServer(server);
//                }
//            });
//            futures.add(future);
//        }
//
//        for (Future<?> f : futures) {
//            try {
//                f.get();
//            } catch (Exception e) {
//                System.out.println("Error waiting for task: " + e.getMessage());
//            }
//        }
//    }
//
//    private void processServer(PanelServer server) {
//        String ip = server.getIpAddress();
//        if (ip == null || ip.isEmpty()) {
//            System.out.println("Invalid IP for server: " + server);
//            return;
//        }
//
//        boolean responded = false;
//
//        try {
//            Httpsrequest client = RestClientBuilder.newBuilder()
//                    .baseUri(URI.create("http://" + ip + ":40006"))
//                    .connectTimeout(3, TimeUnit.SECONDS)
//                    .readTimeout(3, TimeUnit.SECONDS)
//                    .build(Httpsrequest.class);
//
//            for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
//                try {
//                    Response response = client.broadcast();
//                    if (response.getStatus() == 200) {
//                        responded = true;
//                        break;
//                    }
//                } catch (Exception e) {
//                    System.out.println("Attempt " + attempt + " failed for server: " + ip + " error: " + e.getMessage());
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Could not create client for: " + ip + " error: " + ex.getMessage());
//        }
//
//        if (!responded) {
//            System.out.println("Server did not respond: " + ip);
//            server.setStatus("inactive");
//        }
//    }
//}
