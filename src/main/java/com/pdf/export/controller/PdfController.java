package com.pdf.export.controller;

import com.pdf.export.data.model.dto.AddExportHistoryDto;
import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.data.model.vo.ResponseVo;
import com.pdf.export.service.DataService;
import com.pdf.export.service.PdfService;
import com.pdf.export.util.ResponseUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseVo pdfDownload(@Valid @RequestBody SearchDataDto searchDto) {
        try {
            JSONObject data = dataService.getData(searchDto);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String filename = dateFormat.format(new Date());
            ResponseVo saveData = dataService.saveExportHistory(new AddExportHistoryDto(
                    filename,
                    data.getJSONArray("items").toString()
            ));

            return ResponseUtil.setResponse(HttpStatus.OK.value(), "Success Export Pdf with filename " + filename, null);
        } catch (Exception e) {
            return ResponseUtil.setResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }
}
