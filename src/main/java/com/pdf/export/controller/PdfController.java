package com.pdf.export.controller;

import com.pdf.export.data.entity.PdfExp;
import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.dto.PdfExistingDto;
import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.data.model.vo.ResponseVo;
import com.pdf.export.service.DataService;
import com.pdf.export.service.PdfService;
import com.pdf.export.util.ResponseUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private DataService dataService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/download")
    @ResponseBody
    public ResponseVo pdfDownloadNew(@Valid @RequestBody SearchDataDto searchDto) throws URISyntaxException, IOException, InterruptedException, JSONException {
        JSONObject data = dataService.getData(searchDto);
        if (data.getInt("total_count") <= 0) {
            return ResponseUtil.setResponse(HttpStatus.NOT_FOUND.value(), "Data Not Found", null);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = dateFormat.format(new Date());
        AddExportDto expDto = new AddExportDto(
                filename,
                data.getJSONArray("items").toString()
        );
        ResponseVo saveData = dataService.saveExportHistory(expDto);
        if (saveData.getCode() != 200) {
            return saveData;
        }
        return pdfService.exportPdf(expDto);
    }

    @PostMapping("/download/existing")
    @ResponseBody
    public ResponseVo pdfDownloadExisting(@Valid @RequestBody PdfExistingDto existDto) throws JSONException, URISyntaxException, IOException, InterruptedException {
        PdfExp pdfExp = dataService.searchPdfHistory(existDto);
        if (pdfExp == null) {
            return ResponseUtil.setResponse(HttpStatus.NOT_FOUND.value(), "Data Not Found", null);
        }
        return pdfService.exportPdf(new AddExportDto(pdfExp.getFilename(), pdfExp.getPayload()));
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseVo pdfGetAll() {
        return ResponseUtil.setResponse(HttpStatus.OK.value(), "Success Get Data", dataService.getAllHistory());
    }
}
