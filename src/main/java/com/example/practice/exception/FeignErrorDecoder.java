package com.example.practice.exception;

import com.example.practice.config.Translator;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {


        switch (response.status()) {
            case 400:
                log.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404: {
                log.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), Translator.toLocale("notFound"));
            }
            case 500: {
                log.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), Translator.toLocale("network"));
            }
            default:
                return new Exception(response.reason());
        }
    }
}
