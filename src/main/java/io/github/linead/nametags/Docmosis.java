package io.github.linead.nametags;

import io.github.linead.nametags.domain.Attendee;
import io.github.linead.nametags.domain.DocmosisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class Docmosis {

    private static String HOST = "https://dws2.docmosis.com/services/rs/render";

    @Autowired
    Environment env;

    public byte[] render(Map<String, List<Attendee>> attendeeList) {
        DocmosisRequest req =new DocmosisRequest();
        req.setAccessKey(getKey());
        req.setTemplateName("labels2.doc");
        req.setOutputName("all_labels.pdf");
        req.setData(attendeeList);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        ResponseEntity<byte[]> response = restTemplate.postForEntity(HOST, req, byte[].class);
        return response.getBody();


    }



    public String getKey() { return env.getProperty("docmosis_api.key"); }

}
