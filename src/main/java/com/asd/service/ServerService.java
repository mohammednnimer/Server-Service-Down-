package com.asd.service;


import com.asd.dto.SearchCriteria;
import com.asd.dto.UpdateServer;
import com.asd.repository.ServerRepo;

import com.asd.repository.ThresholdRepo;
import com.db.entitie.PanelServer;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ServerService {



    @Inject
    ThresholdRepo thresholdRepo;
    @Inject
    ServerRepo serverRepo;


//    public List<PanelServer> GetAllServer(int limit, int page){
//        return serverRepo.GetAllServer(limit, page);
//    }
//    }

//    public List<PanelServer> ListByDNSAndIP(String DNS,String IP) {
//        return serverRepo.ListByDNSAndIP(DNS,IP);
//    }
//    public List<PanelServer> ListByDNS(String DNS) {
//        return serverRepo.ListByDNS(DNS);
//    }
//    public List<PanelServer> ListByIP(String IP) {
//        return serverRepo.ListByIP(IP);
//    }
    public List<PanelServer> ListAllServer() {
        return serverRepo.listAll();
    }
    @Transactional
    public boolean Create(PanelServer server) {
        if(serverRepo.isExist(server.getDns(), server.getIpAddress())){
           return false;
        }
        server.setId(UUID.randomUUID().toString());
        server.setCreationDate(LocalDateTime.now());
        server.setIsdeleted(false);
        serverRepo.persist(server);
        thresholdRepo.addthreshlod(server.getIpAddress());
        return true;

    }
    public PanelServer GetServer(String id) {
       return serverRepo.getServer(id);
    }
    @Transactional
    public boolean delete(String id) {
        PanelServer service= serverRepo.getServer(id);
        if(service==null){
            return false;
        }
        else{
            service.setIsdeleted(true);
            service.setUpdatedDate(LocalDateTime.now());
            thresholdRepo.delete(service.getIpAddress());
            return true;
        }
    }


    @Transactional
    public boolean update(UpdateServer updatedServer) {
        PanelServer existingServer = serverRepo.getServer(updatedServer.getId());
        if (existingServer == null) {
            return false;
        }
        if (updatedServer.getIpAddress() != null) {
            existingServer.setIpAddress(updatedServer.getIpAddress());
        }
        if (updatedServer.getDns() != null) {
            existingServer.setDns(updatedServer.getDns());
        }
        if (updatedServer.getStatus() != null) {
            existingServer.setStatus(updatedServer.getStatus());
        }
        if (updatedServer.getUpdatedBy() != null) {
            existingServer.setUpdatedBy(updatedServer.getUpdatedBy());
        }
        existingServer.setUpdatedDate(java.time.LocalDateTime.now());
        return true;
    }

    public Response GetServer(Integer limit,  Integer page) {
        if(limit == null&&page == null) {
            return Response.status(Response.Status.OK).entity(serverRepo.GetAllServer(50, 1)).build();
        }
        else if(limit == null&&page != null) {
            if(page<0 ){return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE,
                    ErrorMsgs.paging)).build();
            }
            return Response.status(Response.Status.OK).entity(serverRepo.GetAllServer(50, page)).build();

        }else if (limit != null &&page == null){
            if(limit<0 ){return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE,
                    ErrorMsgs.limite)).build();
            }  else{
                return Response.status(Response.Status.OK).entity(serverRepo.GetAllServer(limit, 1)).build();

            }
        }
        return Response.status(Response.Status.OK).entity(serverRepo.GetAllServer(limit, page)).build();

    }
    public List<PanelServer> GetByFilter(SearchCriteria filtering) {
//        if (filtering.getDNS() != null && filtering.getIP() != null) {
//            return Response.ok(ListByDNSAndIP(filtering.getDNS(), filtering.getIP())).build();
//        } else if (filtering.getDNS() != null) {
//            return Response.ok(ListByDNS(filtering.getDNS())).build();
//        } else if (filtering.getIP() != null) {
//            return Response.ok(ListByIP(filtering.getIP())).build();
//        } else {
//            return Response.ok(ListAllServer()).build();
//        }

        return serverRepo.search(filtering.getDNS(), filtering.getIP(), filtering.getGernalSearch());
    }



    public Response GetServerById( String id) {
        PanelServer panelServer=  GetServer(id);
        if(panelServer==null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound)).build();
        }
        return Response.ok(GetServer(id)).build();
    }
    public Response DeleteServer( String Id) {
        boolean IsSucc=delete(Id);
        if(IsSucc){
            return Response.ok(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.deletsucc)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound)).build();
    }


    public Response UpdateServer(com.asd.dto.UpdateServer server) {
        boolean isSucc = update(server);
        if (isSucc) {
            return Response.ok(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.updatesucc)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound))
                .build();
    }



















}
