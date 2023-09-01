package com.clone.wanted.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class BaseException extends RuntimeException {
    private BaseResponseStatus status;  //BaseResoinseStatus 객체에 매핑

}

