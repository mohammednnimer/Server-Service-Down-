package com.asd.service;

import com.asd.repository.EmailRepo;
import com.db.entitie.EmailParticipants;
import jakarta.inject.Inject;

public class EmailService {


    @Inject
    EmailRepo emailRepo;


  public void addEmail(EmailParticipants emailParticipants){
      emailRepo.persist(emailParticipants);
  }

}
