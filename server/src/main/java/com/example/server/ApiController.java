package com.example.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.beans.XMLDecoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.XMLFormatter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ApiController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }


    @PostMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String processData(@RequestBody String jsonData) {
        // Processe o JSON recebido
        // ...
        return jsonData; // Retorne o JSON original para este exemplo
    }

    @PostMapping(value = "/xml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String processXml(@RequestParam("files") List<MultipartFile> files) throws IOException, DocumentException {
        // Processe cada arquivo XML individualmente
        SAXReader reader = new SAXReader();
        for (MultipartFile file : files) {
            // Transformar XML file ByteArr
            ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
            Document document = reader.read(stream);
            Element root = document.getRootElement();




            System.out.println("XML: " + root.attributeValue("versao"));

            //GRAVAR NO BANCO O BINARIO DO XML

            /* iterator caso nao for o XPath
            public void bar(Document document) throws DocumentException {

                Element root = document.getRootElement();

                // iterate through child elements of root
                for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
                    Element element = it.next();
                    // do something
                }

                // iterate through child elements of root with element name "foo"
                for (Iterator<Element> it = root.elementIterator("foo"); it.hasNext();) {
                    Element foo = it.next();
                    // do something
                }

                // iterate through attributes of root
                for (Iterator<Attribute> it = root.attributeIterator(); it.hasNext();) {
                    Attribute attribute = it.next();
                    // do something
                }
            }
            **/
            //XPath xpath = (XPath) document.selectNodes("/root/element/text()");
            //for (Node node : xpath) {
                // Processe o texto de cada nó
                // ...
            //}

            // Salve o arquivo XML
            //Path path = Paths.get("/path/to/file_" + file.getOriginalFilename());
            //Files.write(path, file.getBytes());
        }

        // Retorne uma resposta JSON
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "6 arquivos XML processados com sucesso!");
        String json = mapper.writeValueAsString(response);
        return json;
    }

}
