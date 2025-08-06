package com.asd.service;

import com.asd.dto.ReciveAlert;
import com.asd.dto.sub.PartitionInfo;
import com.asd.mapper.ProcessMapper;
import com.asd.repository.AgentLiveServerRepository;
import com.db.entitie.AgentLiveServer;

import com.db.entitie.Harddisk;
import com.db.entitie.ProcessReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgentLiveServerService {

    @Inject
    AgentLiveServerRepository repository;


    @Transactional
    public AgentLiveServer saveOrUpdate(ReciveAlert reciveAlert) {


        AgentLiveServer incoming=new AgentLiveServer();
        incoming.setFreeRam(reciveAlert.getClientUtilization().getRamUtilzation().getFree());
        incoming.setLogicalCores(reciveAlert.getClientUtilization().getCpuUtilzation().getLogicalCores());
        incoming.setPhysicalCores(reciveAlert.getClientUtilization().getCpuUtilzation().getPhysicalCores());
        incoming.setUsedRam(reciveAlert.getClientUtilization().getRamUtilzation().getUsed());
        incoming.setTotalRam(reciveAlert.getClientUtilization().getRamUtilzation().getTotal());
        incoming.setIpAddress(reciveAlert.getClientUtilization().getIp());
        incoming.setCpuUtilization(reciveAlert.getClientUtilization().getCpuUtilzation().getUtilization());
        incoming.setRamUtilization(reciveAlert.getClientUtilization().getRamUtilzation().getUtilization());

        int i=0;

        List<Harddisk> harddisks=new ArrayList<>();
        for(PartitionInfo partitionInfo:reciveAlert.getClientUtilization().getHarddiskUtilization().getPartitions())
        {
            Harddisk harddiskUsage=new Harddisk(partitionInfo.getPath(),partitionInfo.getUtilization());
            harddiskUsage.setFree(partitionInfo.getFreeSpace());
            harddiskUsage.setTotalSpace(partitionInfo.getTotalSpace());
            harddiskUsage.setUsedSpace(partitionInfo.getUsedSpace());
            harddisks.add(harddiskUsage);
        }
        List<com.db.entitie.ProcessReport> processReports=new ArrayList<>();
        for(com.asd.dto.sub.ProcessReport processReport:reciveAlert.getProcessReports())
        {
            com.db.entitie.ProcessReport processReportUsage=new com.db.entitie.ProcessReport();
            processReports.add(ProcessMapper.getProcessEntity(processReport));
        }

        incoming.setProecessReport(processReports);
        incoming.setHarddiskUtilizations(harddisks);
        incoming.setLastUpdate(LocalDateTime.now());


        AgentLiveServer existing = repository.findByIp(incoming.getIpAddress());

        if (existing != null) {
            System.out.println("1");
            existing.setCpuUtilization(incoming.getCpuUtilization());
            existing.setRamUtilization(incoming.getRamUtilization());
            existing.setLastUpdate(incoming.getLastUpdate());
            existing.setFreeRam(incoming.getFreeRam());
            existing.setTotalRam(incoming.getTotalRam());
            existing.setUsedRam(incoming.getUsedRam());
            existing.setLogicalCores(incoming.getLogicalCores());
            existing.setPhysicalCores(incoming.getPhysicalCores());


            existing.getProecessReport().clear();
            existing.getHarddiskUtilizations().clear();

            for (ProcessReport pr : incoming.getProecessReport()) {
                if (pr.getPid() != 0) {
                    ProcessReport managedPr = repository.getEntityManager().merge(pr);
                    existing.getProecessReport().add(managedPr);
                } else {
                    existing.getProecessReport().add(pr);
                }
            }

            for (Harddisk hd : incoming.getHarddiskUtilizations()) {
                existing.getHarddiskUtilizations().add(hd);
            }

            return existing;
        } else {
            System.out.println("2");
            repository.persist(incoming);
            return incoming;
        }
    }

    @Transactional
    public AgentLiveServer findbyip(String ipAddress) {
      return   repository.findByIp(ipAddress);
    }

}
