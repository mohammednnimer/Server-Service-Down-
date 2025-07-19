package com.asd.repository;

import com.db.entitie.EmailParticipants;
import com.db.entitie.WhatsappParticipants;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class WhatsappRepo implements PanacheRepository<WhatsappParticipants> {


    public long deletePhone(String phone) {
        return delete("phoneNumber = ?1", phone);
    }

    public WhatsappParticipants existsByPhone(String phone) {
        return find("phoneNumber = ?1", phone).firstResult() ;
    }

    public List<WhatsappParticipants> getAllParticipants() {
        return listAll();
    }

}
