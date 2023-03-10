package com.pdf.export.service;

import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.vo.ResponseVo;
import org.json.JSONException;

import javax.transaction.Transactional;

@Transactional
public interface PdfService {

    public ResponseVo exportPdf(AddExportDto expDto) throws JSONException;
}
