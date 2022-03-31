package com.example.practice.domain.dto.response;

import com.example.practice.config.Translator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseData<T> {
    private String status;
    private String message;
    private T data;


    public ResponseData<T> _ok(T obj){
        ResponseData<T> data = new ResponseData<>();
        data.setStatus(Translator.toLocale("status.success"));
        data.setMessage(Translator.toLocale("success"));
        data.setData(obj);
        return data;
    }

    public ResponseData<T> _fail(String message){
        ResponseData<T> data = new ResponseData<>();
        data.setStatus(Translator.toLocale("status.fail"));
        data.setMessage(message);
        data.setData(null);
        return data;
    }

}
