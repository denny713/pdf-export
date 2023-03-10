package com.pdf.export.data.model.dto;

import lombok.Data;

@Data
public class AddExportHistoryDto {

    public AddExportHistoryDto() {
        super();
    }

    public AddExportHistoryDto(String filename, String payload) {
        this.filename = filename;
        this.payload = payload;
    }

    private String filename;
    private String payload;
}
