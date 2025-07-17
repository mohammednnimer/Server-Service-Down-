package com.asd.repository;

import com.db.entitie.WhatsappParticipants;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WhatsappRepo implements PanacheRepository<WhatsappParticipants> {


    public long deletePhone(String phone) {
        return delete("phoneNumber = ?1", phone);
    }


}
