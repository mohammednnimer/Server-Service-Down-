package com.asd.repository;

import com.db.entitie.EmailParticipants;
import com.db.entitie.PanelServer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailRepo implements PanacheRepository<EmailParticipants> {
    public long deleteEmail(String email) {
        return delete("Email = ?1", email);
    }
}
