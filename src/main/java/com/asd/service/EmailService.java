package com.asd.service;

import com.asd.repository.EmailRepo;
import com.db.entitie.EmailParticipants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class EmailService {


    @Inject
    EmailRepo emailRepo;


    @Transactional
    public boolean  addEmail(EmailParticipants emailParticipants) {
        if (emailRepo.existsByEmail(emailParticipants.getEmail())!=null) {
            return false;
        }
        emailRepo.persist(emailParticipants);
        return true;
    }
  @Transactional
    public boolean deleteEmail(String email){
      long count= emailRepo.deleteEmail(email);
      return count > 0;
    }

}
