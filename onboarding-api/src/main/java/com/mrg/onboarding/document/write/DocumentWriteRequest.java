package com.mrg.onboarding.document.write;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class DocumentWriteRequest {
    private UUID uuid;
    private String title;
    private String content;
}
