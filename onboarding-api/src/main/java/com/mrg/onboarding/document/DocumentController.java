package com.mrg.onboarding.document;


import com.mrg.onboarding.document.dto.DocumentHtmlDto;
import com.mrg.onboarding.document.dto.DocumentRawDto;
import com.mrg.onboarding.document.service.DocumentService;
import com.mrg.onboarding.document.service.RenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final RenderService renderService;

    @GetMapping(value = "{uuid}")
    public ResponseEntity<Map<String,String>> getDocumentBySections(@PathVariable UUID uuid){
        try{
            Optional<Document> document = documentService.getDocumentByUuid(uuid);
            if(document.isPresent()){
                return new ResponseEntity<>(renderService.renderMarkDownBySections(document.get()), HttpStatus.FOUND);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/raw/{uuid}")
    public ResponseEntity<DocumentRawDto> getRawDocument(@PathVariable UUID uuid){
        try{
            Optional<Document> document = documentService.getDocumentByUuid(uuid);
            if(document.isPresent()){
                return new ResponseEntity<>(renderService.renderMarkDownRaw(document.get()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/html/{uuid}")
    public ResponseEntity<DocumentHtmlDto> getHtmlDocument(@PathVariable UUID uuid){
        try{
            System.out.println("UUID : " + uuid);
            Optional<Document> document = documentService.getDocumentByUuid(uuid);
            if(document.isPresent()){
                return new ResponseEntity<>(renderService.renderMarkDownHtml(document.get()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Document>> listDocuments(){
        try{
            List<Document> documentList = documentService.getAll();
            if(!documentList.isEmpty()){
                return new ResponseEntity<>(documentList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
