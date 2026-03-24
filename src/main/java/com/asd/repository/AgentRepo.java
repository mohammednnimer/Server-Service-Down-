package com.asd.repository;

import com.db.entitie.Agent;
import com.db.entitie.EmailParticipants;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgentRepo implements PanacheRepository<Agent> {



    public Agent findByServerId(String id) {
        return find("server = ?1", id).firstResult();
    }

    public Agent findByToken(String token) {
        return find("token = ?1", token).firstResult();
    }


    public boolean existsByToken(String token) {
        return count("token = ?1", token) > 0;
    }
    public String getServerIpByToken(String token) {
        return (String) getEntityManager().createNativeQuery("""
        SELECT p.ip_address 
        FROM agent a ,  panel_server_tbl p
        WHERE a.server_id=p.id  and a.token = :token  
""").setParameter("token",token) .getSingleResultOrNull();



    }




    public int deleteByID(String serverId) {
        return getEntityManager().createQuery("delete from Agent where server = :server")
                .setParameter("server", serverId)
                .executeUpdate();
    }



}
