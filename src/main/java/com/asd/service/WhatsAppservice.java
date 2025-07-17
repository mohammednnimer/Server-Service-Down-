package com.asd.service;

import com.asd.repository.EmailRepo;
import com.asd.repository.WhatsappRepo;
import com.db.entitie.EmailParticipants;
import com.db.entitie.WhatsappParticipants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class WhatsAppservice {
    @Inject
    WhatsappRepo whatsappRepo;

    @Transactional
    public void addPhone(WhatsappParticipants whatsappParticipants){
        whatsappRepo.persist(whatsappParticipants);
    }
    @Transactional
    public boolean deleteEmail(String phone){
        long count= whatsappRepo.deletePhone(phone);
        return count > 0;
    }

}
