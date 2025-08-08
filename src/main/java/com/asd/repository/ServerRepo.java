package com.asd.repository;

import com.db.entitie.PanelServer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ServerRepo implements PanacheRepository<PanelServer> {

    public List<PanelServer> GetAllServer(int limit,int page) {
        int offset = (page-1) * limit;
        return find("isdeleted = false order by creationDate desc").range(offset,offset+ limit - 1).list();
    }

    public PanelServer getServer(String id) {
        return find("id = ?1 and isdeleted = false ",id).firstResult();
    }
    public long deleteServerById(String id) {
        return delete("id = ?1", id);
    }

    public List<PanelServer> search(String dns, String ip, String generalSearch) {
        String query = "isdeleted = false";

        if (generalSearch != null && !generalSearch.trim().isEmpty()) {
            String[] terms = generalSearch.trim().split("\\s+");
            for (String term : terms) {
                String lowerTerm = term.toLowerCase();
                query += " AND ("
                        + "LOWER(id) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(dns) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(ipAddress) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(status) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(createdBy) LIKE '%" + lowerTerm + "%' "
                        + "OR LOWER(updatedBy) LIKE '%" + lowerTerm + "%'"
                        + ")";
            }
        }

        if (dns != null && !dns.isEmpty()) {
            query += " AND LOWER(dns) LIKE '%" + dns.toLowerCase() + "%'";
        }

        if (ip != null && !ip.isEmpty()) {
            query += " AND LOWER(ipAddress) LIKE '%" + ip.toLowerCase() + "%'";
        }

        query+=" order by creationDate desc ";
        return list(query);
    }

    public boolean isExist(String dns,String ip){
        return (find("dns = ?1 and ipAddress = ?2 and isdeleted = false ",dns,ip).count() > 0);
    }






}
