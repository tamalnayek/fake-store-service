package com.mystore.spring.boot.fakestore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {

    private HttpStatus httpStatus;
    private String errorMsg;

}
