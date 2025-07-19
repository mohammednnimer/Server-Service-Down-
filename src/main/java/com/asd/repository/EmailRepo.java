package com.asd.repository;

import com.db.entitie.EmailParticipants;
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

    public List<EmailParticipants> getAllParticipants() {
        return listAll();
    }
}
