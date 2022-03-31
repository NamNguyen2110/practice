package com.example.practice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonUtils {
    @Autowired
    private ObjectMapper objectMapper;

    public <T> T convertStringToObject(String input, Class<T> tClass) {
        try {
            return this.objectMapper.readValue(input, tClass);
        } catch (Exception ex) {
            log.error("Convert string to object error", ex);
            return null;
        }
    }

    public <T> String convertObjToString(T clazz) {
        try {
            return this.objectMapper.writeValueAsString(clazz);
        } catch (Exception ex) {
            log.error("Convert obj to string error", ex);
            return null;
        }
    }
}
