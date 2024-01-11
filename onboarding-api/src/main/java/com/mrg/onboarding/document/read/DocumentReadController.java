package com.mrg.onboarding.document.read;


import com.mrg.onboarding.document.dto.DocumentDto;
import com.mrg.onboarding.document.model.Document;
import com.mrg.onboarding.document.dto.DocumentHtmlDto;
import com.mrg.onboarding.document.dto.DocumentRawDto;
import com.mrg.onboarding.document.render.RenderService;
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
public class DocumentReadController {

    private final DocumentReadService documentService;
    private final RenderService renderService;

    @GetMapping(value = "{uuid}")
    public ResponseEntity<Map<String,String>> getDocumentBySections(@PathVariable UUID uuid){
        try{
            Optional<Document> document = documentService.getDocumentByUuid(uuid);
            if(document.isPresent()){
                return new ResponseEntity<>(renderService.renderMarkDownSectionFormat(document.get()), HttpStatus.FOUND);
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
                return new ResponseEntity<>(renderService.renderMarkDownRawFormat(document.get()), HttpStatus.OK);
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
                return new ResponseEntity<>(renderService.renderMarkDownHtmlFormat(document.get()), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<DocumentDto>> listDocuments(){
        try{
            List<DocumentDto> documentList = documentService.getAll();
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
