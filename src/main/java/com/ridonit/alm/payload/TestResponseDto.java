package com.ridonit.alm.payload;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Data
public class TestResponseDto {

    private HttpStatus status;
    private Integer statusCode;
    private HttpHeaders header;
    private String body;


}
