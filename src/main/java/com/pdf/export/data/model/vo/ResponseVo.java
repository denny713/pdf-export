package com.pdf.export.data.model.vo;

import lombok.Data;

@Data
public class ResponseVo<T> {

    public ResponseVo() {
        super();
    }

    public ResponseVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private int code;
    private String message;
    private T data;
}
