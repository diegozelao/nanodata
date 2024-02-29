package com.example.server;

import com.example.server.domain.xml.NotaFiscal;
import com.example.server.domain.xml.NotaFiscalRepository;
import com.example.server.domain.xml.XmlInfo;
import com.example.server.domain.xml.XmlInfoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.xpath.XPath;
import java.beans.XMLDecoder;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

import java.util.logging.XMLFormatter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ApiController {

    @Autowired
    private NotaFiscalRepository repository;

    @Autowired
    private XmlInfoRepository repositoryXml;
    @GetMapping("/")
    public ResponseEntity getAllNotaFiscal() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/notafiscal")
    public ResponseEntity getAllNota() {
        List<NotaFiscal> notaFiscals = repository.findAll();
        return ResponseEntity.ok(notaFiscals);
    }
    @GetMapping("/xml/{idNfe}")
    public ResponseEntity getXml(@PathVariable(name="idNfe") String idNfe) {
        return ResponseEntity.ok(repositoryXml.findByIdNfe(idNfe));
    }
    @PostMapping(value = "/xml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String processXml(@RequestParam("files") List<MultipartFile> files) throws IOException, DocumentException {

        SAXReader reader = new SAXReader();
        for (MultipartFile file : files) {
            ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
            Document document = reader.read(stream);
            Element nfeProc = document.getRootElement();
            Element NFe = nfeProc.element("NFe");
            Element infNFe = NFe.element("infNFe");
            Element ide = infNFe.element("ide");
            Element dhEmi = ide.element("dhEmi");
            Element nNF = ide.element("nNF");
            Element cUF = ide.element("cUF");
            Element emit = infNFe.element("emit");
            Element emitCNPJ = emit.element("CNPJ");
            Element emitxFant = emit.element("xFant");
            Element dest = infNFe.element("dest");
            Element destCNPJ = dest.element("CNPJ");
            Element destxNome = dest.element("xNome");
            Element total = infNFe.element("total");
            Element ICMSTot = total.element("ICMSTot");
            Element vTotTrib = ICMSTot.element("vTotTrib");
            Element vNF = ICMSTot.element("vNF");

            BigDecimal vTotTribBig = new BigDecimal(vTotTrib.getData().toString());
            BigDecimal vNFBig = new BigDecimal(vNF.getData().toString());

            String infNFeId = infNFe.attributeValue("Id");
            String dhEmiVal = dhEmi.getData().toString();
            String nNFVal = (String) nNF.getData();
            String cUFVal = (String) cUF.getData();
            String emitCNPJVal = (String) emitCNPJ.getData();
            String emitxFantVal = (String) emitxFant.getData();
            String destCNPJVal = (String) destCNPJ.getData();
            String destxNomeVal = (String) destxNome.getData();
            BigDecimal vTotTribVal = vTotTribBig;
            BigDecimal vNFVal = vNFBig;

            NotaFiscal notaFiscal = new NotaFiscal(
                infNFeId,
                dhEmiVal,
                nNFVal,
                cUFVal,
                emitCNPJVal,
                emitxFantVal,
                destCNPJVal,
                destxNomeVal,
                vTotTribVal,
                vNFVal
            );

            try {
                repository.save(notaFiscal);
            } catch (Exception e) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> jsonData = new HashMap<>();
                if (e.getCause().getMessage().contains("unique constraint")) {
                    jsonData.put("mensagem", "ERROR: Ja existe uma Nota Fiscal com esse ID");
                } else {
                    jsonData.put("mensagem", e.getMessage());
                }
                jsonData.put("sucess", "false");
                String jsonResponse = mapper.writeValueAsString(jsonData);
                ResponseEntity<String> response = ResponseEntity.ok(jsonResponse);
                return response.getBody();
            }
            try {
                repositoryXml.save(new XmlInfo(infNFeId, file.getBytes()));
            } catch (Exception e) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> jsonData = new HashMap<>();
                if (e.getCause().getMessage().contains("unique constraint")) {
                    jsonData.put("mensagem", "ERROR: Ja existe uma Nota Fiscal com esse ID");
                } else {
                    jsonData.put("mensagem", e.getMessage());
                }
                jsonData.put("sucess", "false");
                String jsonResponse = mapper.writeValueAsString(jsonData);
                ResponseEntity<String> response = ResponseEntity.ok(jsonResponse);
                return response.getBody();
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("mensagem", "Arquivos XML processados com sucesso!");
        jsonData.put("sucess", "true");
        String jsonResponse = mapper.writeValueAsString(jsonData);
        ResponseEntity<String> response = ResponseEntity.ok(jsonResponse);
        return response.getBody();
    }

}
