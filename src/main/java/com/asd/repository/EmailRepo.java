package com.asd.repository;

import com.db.entitie.EmailParticipants;
import com.db.entitie.PanelServer;
import com.db.entitie.PanelService;
import com.db.entitie.WhatsappParticipants;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class EmailRepo implements PanacheRepository<EmailParticipants> {
    public long deleteEmail(String email) {
        return delete("email = ?1", email);
    }
    public EmailParticipants existsByEmail(String email) {
        return find("email = ?1", email).firstResult() ;
    }





}
