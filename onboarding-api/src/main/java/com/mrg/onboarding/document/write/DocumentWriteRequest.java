package com.mrg.onboarding.document.write;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DocumentWriteRequest {

    private UUID uuid;

    @NotBlank(message = "title can not be blank")
    private String title;

    @NotBlank(message = "content can not be blank")
    private String content;

    @NotBlank(message = "status can not be blank")
    private int status;
}
