package com.asd.mapper;

import com.asd.dto.sub.ProcessReport;

public class ProcessMapper {

    public  static com.db.entitie.ProcessReport getProcessEntity(ProcessReport processReport)
    {
        com.db.entitie.ProcessReport processReport1=new com.db.entitie.ProcessReport();
        processReport1.setMemoryUsage(processReport.getMemoryUsage());
        processReport1.setCpuUsage(processReport.getCpuUsage());
        processReport1.setProcessName(processReport.getProcessName());
        processReport1.setProcessPath(processReport.getProcessPath());
        processReport1.setPid(processReport.getPid());
        processReport1.setPort(processReport.getPort());
        processReport1.setStartTime(processReport.getStartTime());
        return processReport1;
    }
}
