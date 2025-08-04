package com.asd.service;

import com.asd.repository.AgentLiveServerRepository;
import com.db.entitie.AgentLiveServer;

import com.db.entitie.Harddisk;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AgentLiveServerService {

    @Inject
    AgentLiveServerRepository repository;


    @Transactional
    public AgentLiveServer saveOrUpdate(AgentLiveServer incoming) {
        AgentLiveServer existing = repository.findByIp(incoming.getIpAddress());
        System.out.println("222222222221111111111");

        if (existing != null) {
            System.out.println("11111111111111222222222221111111111");
             existing.setCpuUtilization(incoming.getCpuUtilization());
            existing.setRamUtilization(incoming.getRamUtilization());
            existing.setLastUpdate(incoming.getLastUpdate());
            existing.setFreeRam(incoming.getFreeRam());
            existing.setTotalRam(incoming.getTotalRam());
            existing.setUsedRam(incoming.getUsedRam());
            existing.setLogicalCores(incoming.getLogicalCores());
            existing.setPhysicalCores(incoming.getPhysicalCores());


            List<Harddisk> disks = existing.getHarddiskUtilizations();
            disks.clear();
            disks.addAll(incoming.getHarddiskUtilizations());

            return existing;
        } else {
            repository.persist(incoming);
            return incoming;
        }
    }

    @Transactional
    public AgentLiveServer findbyip(String ipAddress) {
      return   repository.findByIp(ipAddress);
    }

}
