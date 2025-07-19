package com.asd.service;


import com.asd.dto.SearchCriteria;
import com.asd.dto.UpdateService;
import com.asd.repository.ServerRepo;
import com.asd.repository.ServiceRepo;
import com.db.entitie.PanelServer;
import com.db.entitie.PanelService;
import com.utils.constant.ErrorMsgs;
import com.utils.constant.SuccMsgs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ServicePanel {



    @Inject
    ServiceRepo serviceRepo;

    @Inject
    ServerRepo serverRepo;

    public List<PanelService> ListByDNSAndPORT(String DNS, int PORT) {
        return serviceRepo.ListByDNSAndPORT(DNS,PORT);
    }
    public List<PanelService> ListByDNS(String DNS) {
        return serviceRepo.ListByDNS(DNS);
    }
    public List<PanelService> ListByPORT(int PORT) {
        return serviceRepo.ListByPORT(PORT);
    }
    public List<PanelService> ListAllServer() {
        return serviceRepo.listAll();
    }
    public List<PanelService> GetAllService(int limit, int page){
        return serviceRepo.getAllServices(limit,page);
    }



    public List<PanelService> GetServiesByIdServer(String id)
    {
        return serviceRepo.ListByServerID(id);
    }

    @Transactional
    public boolean Create(PanelService service) {

        if(serverRepo.getServer(service.getServer())==null){

            return false;
        }
        service.setId(UUID.randomUUID().toString());
        service.setCreationDate(LocalDateTime.now());
        service.setIsDeleted(false);
        serviceRepo.persist(service);
        return true;
    }
    public PanelService GetPanelServiceById(String id) {
        return serviceRepo.getService(id);

    }

    @Transactional
    public boolean delete(String id) {
        PanelService service= serviceRepo.getService(id);
        if(service==null){
            return false;
        }
        else{
            service.setIsDeleted(true);
            service.setUpdatedDate(LocalDateTime.now());
            return true;
        }
    }


    @Transactional
    public boolean update(UpdateService updatedService) {
       PanelService existingService = serviceRepo.getService(updatedService.getId());
        if (existingService == null) {
            return false;
        }
        if (updatedService.getServiceName() != null) {
            existingService.setServiceName(updatedService.getServiceName());
        }
        if (updatedService.getServicePort() != null) {
            existingService.setServicePort(updatedService.getServicePort());
        }
        if (updatedService.getServiceDns() != null) {
            existingService.setServiceDns(updatedService.getServiceDns());
        }

        if (updatedService.getWorkDirPath() != null) {
            existingService.setWorkDirPath(updatedService.getWorkDirPath());
        }
        if (updatedService.getUpdatedBy() != null) {
            existingService.setUpdatedBy(updatedService.getUpdatedBy());
        }
        existingService.setUpdatedDate(LocalDateTime.now());
        return true;
    }


    public List<PanelService> GetFilter(SearchCriteria filtering) {
        return  serviceRepo.search(filtering.getDNS(),filtering.getPORT(), filtering.getGernalSearch());
    }
    public Response DeleteService( String Id) {
        boolean IsSucc= delete(Id);
        if(IsSucc){
            return Response.ok(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.deletsucc)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound)).build();


    }





    public Response GetService(Integer limit,Integer page) {
            if (limit == null) limit = 50;
            if (page == null) page = 1;

            if (limit <= 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE, ErrorMsgs.limite)).build();
            }

            if (page <= 0) {
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(new com.db.entitie.Response(Response.Status.NOT_ACCEPTABLE, ErrorMsgs.paging)).build();
            }

            return Response.status(Response.Status.OK)
                    .entity(GetAllService(limit, page))
                    .build();
        }




    public Response UpdateService(UpdateService service) {
        boolean isSucc = update(service);
        if (isSucc) {
            return Response.ok(new com.db.entitie.Response(Response.Status.OK, SuccMsgs.updatesucc)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound))
                .build();
    }
    public Response GetServiceById( String id) {
        PanelService servicePanel =  GetPanelServiceById(id);
        if(servicePanel ==null)
        {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new com.db.entitie.Response(Response.Status.NOT_FOUND, ErrorMsgs.NotFound)).build();
        }
        return Response.ok(GetPanelServiceById(id)).build();
    }





}
