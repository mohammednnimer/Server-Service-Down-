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
    public boolean addPhone(WhatsappParticipants whatsappParticipants){
        if (whatsappRepo.existsByphone(whatsappParticipants.getPhoneNumber())!=null) {
            return false;
        }
        whatsappRepo.persist(whatsappParticipants);
        return true;
    }
    @Transactional
    public boolean deletephone(String phone){
        long count= whatsappRepo.deletePhone(phone);
        return count > 0;
    }

}
