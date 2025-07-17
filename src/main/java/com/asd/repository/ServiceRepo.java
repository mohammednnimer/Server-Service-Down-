package com.asd.repository;

import com.db.entitie.PanelServer;
import com.db.entitie.PanelService;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class ServiceRepo  implements PanacheRepository<PanelService> {



    public List<PanelService> ListByDNSAndPORT(String DNS, int PORT) {
        return list("servicePort = ?1 and serviceDns LIKE ?2 and isdeleted = false", PORT, "%" + DNS + "%");
    }
    public List<PanelService> ListByDNS(String DNS) {
        return list("serviceDns like ?1 and isdeleted = false  ","%" + DNS + "%");
    }
    public List<PanelService> ListByPORT(int PORT ) {
        return list("servicePort = ?1 and isdeleted = false ",  PORT );
    }

    public List<PanelService> ListByServerID(String id) {
        return list("server =?1 and isdeleted = false ",id);
    }
    public PanelService getService(String id) {
        return find("id = ?1 and isdeleted = false",id).firstResult();
    }
    public long deleteSereviceById(String id) {
        return delete("id = ?1 ", id);
    }
    public List<PanelService> getAllServices(int limit,int page) {

        int offset = (page-1) * limit;
        return find("isdeleted = false")
                .range(offset, limit - 1)
                .list();
    }

    public List<PanelService> search(String dns, Integer port, String generalSearch) {
        String query = "isdeleted = false";
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
                    query += " OR servicePort = " + term + " ";
                }
                query += ")";
            }
        }

        if (dns != null && !dns.isEmpty()) {
            query += " AND LOWER(serviceDns) LIKE '%" + dns.toLowerCase() + "%'";
        }

        if (port != null) {
            query += " AND servicePort = " + port;
        }

        return list(query);
    }










}
