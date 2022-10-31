package com.nstsolution.java_utils.communs.annomalie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageAnnomalieDto {

    private Long id;
    private String codeErrro;
    private String messageError;
}
