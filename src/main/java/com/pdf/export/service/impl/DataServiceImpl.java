package com.pdf.export.service.impl;

import com.pdf.export.data.entity.PdfExp;
import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.dto.PdfExistingDto;
import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.data.model.vo.ResponseVo;
import com.pdf.export.data.repo.PdfExpRepo;
import com.pdf.export.service.DataService;
import com.pdf.export.util.ResponseUtil;
import com.pdf.export.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Autowired
    private Environment environment;

    @Autowired
    private PdfExpRepo pdfExpRepo;

    @Override
    public JSONObject getData(SearchDataDto dataDto) throws URISyntaxException, IOException, InterruptedException, JSONException {
        String url = String.format("%s?q=%s&page=%s&per_page=%s", environment.getProperty("api.url"), dataDto.getKeyword(), dataDto.getPage(), dataDto.getSize());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer github_pat_" + environment.getProperty("api.token"))
                .header("X-GitHub-Api-Version", "2022-11-28")
                .uri(new URI(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }

    @Override
    public ResponseVo saveExportHistory(AddExportDto historyDto) {
        try {
            PdfExp pdfExp = new PdfExp();
            pdfExp.setId(getId());
            pdfExp.setExportDate(new Date());
            pdfExp.setFilename(historyDto.getFilename());
            pdfExp.setPayload(historyDto.getPayload());
            pdfExpRepo.save(pdfExp);
            return ResponseUtil.setResponse(HttpStatus.OK.value(), "Success Save Data", pdfExp);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtil.setResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }

    @Override
    public PdfExp searchPdfHistory(PdfExistingDto existDto) {
        PdfExp pdfExp = new PdfExp();
        if (existDto.getId() != null) {
            Optional<PdfExp> pdf = pdfExpRepo.findById(existDto.getId());
            if (pdf.isPresent()) {
                pdfExp = pdf.get();
            }
        } else if (!StringUtil.isNullOrEmpty(existDto.getFilename())) {
            pdfExp = pdfExpRepo.findFirstByFilename(existDto.getFilename());
        }
        if (pdfExp.getId() == null) {
            return null;
        }
        return pdfExp;
    }

    @Override
    public List<PdfExp> getAllHistory() {
        return pdfExpRepo.findAllByOrderById();
    }

    public Long getId() {
        Long id;
        try {
            id = pdfExpRepo.findFirstByOrderByIdDesc().getId();
        } catch (Exception e) {
            id = 0L;
        }
        id++;
        return id;
    }
}
