package com.mrg.onboarding.document.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentSectionDto {
    private String heading;
    private String content;
    private Integer order;
}
