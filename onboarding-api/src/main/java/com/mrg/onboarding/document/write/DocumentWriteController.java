package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.write.request.DocumentWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentWriteController {

    private final DocumentWriteManager documentWriteManager;

    @PostMapping(value = "/save")
    public ResponseEntity<UUID> save(@RequestBody DocumentWriteRequest documentWriteRequest){
        try{
            return new ResponseEntity<>(documentWriteManager.saveDocument(documentWriteRequest),HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
