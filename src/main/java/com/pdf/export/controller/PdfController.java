package com.pdf.export.controller;

import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.data.model.vo.ResponseVo;
import com.pdf.export.service.DataService;
import com.pdf.export.service.PdfService;
import com.pdf.export.util.ResponseUtil;
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
    public ResponseVo pdfDownload(@Valid @RequestBody SearchDataDto searchDto) throws URISyntaxException, IOException, InterruptedException, JSONException {
        JSONObject data = dataService.getData(searchDto);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = dateFormat.format(new Date());
        ResponseVo saveData = dataService.saveExportHistory(new AddExportDto(
                filename,
                data.getJSONArray("items").toString()
        ));
        if (saveData.getCode() != 200) {
            return saveData;
        }

        return ResponseUtil.setResponse(HttpStatus.OK.value(), "Success Export Pdf with filename " + filename, null);
    }
}
