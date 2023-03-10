package com.pdf.export.service;

import com.pdf.export.data.entity.PdfExp;
import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.dto.PdfExistingDto;
import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.data.model.vo.ResponseVo;
import org.json.JSONException;
import org.json.JSONObject;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;

@Transactional
public interface DataService {

    public JSONObject getData(SearchDataDto dataDto) throws URISyntaxException, IOException, InterruptedException, JSONException;

    public ResponseVo saveExportHistory(AddExportDto historyDto);

    public PdfExp searchPdfHistory(PdfExistingDto existDto);
}
