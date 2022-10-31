package com.nstsolution.java_utils.communs.annomalie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageAnnomalieService {

    private final AnnomalieRepository annomalieRepository;


    @Autowired public MessageAnnomalieService(AnnomalieRepository annomalieRepository) {
        this.annomalieRepository = annomalieRepository;
    }


    // TODO fix it later hh
    public MessageAnnomalieDto get(Boolean exist){
        MessageAnnomalieDto messageAnnomalieDto = new MessageAnnomalieDto();
        messageAnnomalieDto.setCodeErrro("EE8484");
        messageAnnomalieDto.setCodeErrro("exist deja :) ");

        return messageAnnomalieDto;
    }

}
