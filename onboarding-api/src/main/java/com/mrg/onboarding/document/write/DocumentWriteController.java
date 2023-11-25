package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.dto.DocumentRawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentWriteController {

    private final DocumentWriteService documentWriteService;

    @PostMapping(value = "/draft")
    public ResponseEntity<?> saveAsDraft(@RequestBody DocumentWriteRequest documentWriteRequest){
        try{
            documentWriteService.saveAsDraft(documentWriteRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/publish")
    public ResponseEntity<?> saveAsPublish(@RequestBody DocumentWriteRequest documentWriteRequest){
        try{
            documentWriteService.saveAsPublished(documentWriteRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
