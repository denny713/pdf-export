package com.pdf.export.data.model.dto;

import lombok.Data;

@Data
public class AddExportDto {

    public AddExportDto() {
        super();
    }

    public AddExportDto(String filename, String payload) {
        this.filename = filename;
        this.payload = payload;
    }

    private String filename;
    private String payload;
}
