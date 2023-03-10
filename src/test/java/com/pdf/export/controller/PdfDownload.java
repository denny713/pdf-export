package com.pdf.export.controller;

import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.data.model.vo.ResponseVo;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
public class PdfDownload {

    @Autowired
    PdfController pdfController;

    @Test
    public void pdfDownloadNew() throws JSONException, URISyntaxException, IOException, InterruptedException {
        ResponseVo response = pdfController.pdfDownloadNew(new SearchDataDto("Q", 1, 100));
        Assertions.assertEquals(200, response.getCode());
    }
}
