package com.pdf.export.data.model.dto;

import lombok.Data;

@Data
public class SearchDataDto {

    public SearchDataDto() {
        super();
    }

    public SearchDataDto(String keyword, int page, int size) {
        this.keyword = keyword;
        this.page = page;
        this.size = size;
    }

    private String keyword;
    private int page;
    private int size;
}
