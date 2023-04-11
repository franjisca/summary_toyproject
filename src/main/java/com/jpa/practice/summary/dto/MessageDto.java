package com.jpa.practice.summary.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {

    private String message;
    private String redirectUrl;
    private boolean isWarning;

}
