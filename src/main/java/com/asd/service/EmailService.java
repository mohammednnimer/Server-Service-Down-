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
  public void addEmail(EmailParticipants emailParticipants){
      emailRepo.persist(emailParticipants);
  }
  @Transactional
    public boolean deleteEmail(String email){
      long count= emailRepo.deleteEmail(email);
      return count > 0;
    }

}
