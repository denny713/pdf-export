package com.pdf.export.data.model.dto;

import lombok.Data;

@Data
public class PdfExistingDto {

    public PdfExistingDto() {
        super();
    }

    public PdfExistingDto(Long id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    private Long id;
    private String filename;
}
