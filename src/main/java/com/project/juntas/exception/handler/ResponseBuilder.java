package com.project.juntas.exception.handler;

import com.project.juntas.exception.handler.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

    public static ResponseEntity<?> responseBuilder(HttpStatus httpStatus, String path, Object object){
        Map<String, Object> response = new HashMap<>();
        response.put("status", httpStatus);
        response.put("path", path);
        response.put((object instanceof ErrorDetail) ? "error" : "response", object);

        return ResponseEntity.status(httpStatus).body(response);
    };

    public static ResponseEntity<?> responseBuilder(HttpStatus httpStatus, Object object){
        Map<String, Object> response = new HashMap<>();
        response.put("status", httpStatus);
        response.put((object instanceof ErrorDetail) ? "error" : "response", object);

        return ResponseEntity.status(httpStatus).body(response);
    };
}