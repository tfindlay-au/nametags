package io.github.linead.nametags;

import io.github.linead.nametags.domain.DocmosisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

public class Docmosis {

    private static String HOST = "https://dws2.docmosis.com/services/rs/render";

    @Autowired
    Environment env;

    public String getKey() { return env.getProperty("docmosis_api.key"); }

}
