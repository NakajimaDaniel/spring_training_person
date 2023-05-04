package com.example.spring_training_person;

import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

  protected YamlJackson2HttpMessageConverter() {
    super(new YAMLMapper()
        .setSerializationInclusion(
            JsonInclude.Include.NON_NULL),
        org.springframework.http.MediaType.parseMediaType("application/x-yaml"));

  }

}
