package com.example.BaiCK.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Data
public class Exception extends RuntimeException{

	private static final long serialVersionUID = 1L;
 	private int code;
    private String message;
}
