package com.pdf.export.service.impl;

import com.pdf.export.data.model.dto.SearchDataDto;
import com.pdf.export.service.DataService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private Environment environment;

    @Override
    public JSONObject getData(SearchDataDto dataDto) throws URISyntaxException, IOException, InterruptedException, JSONException {
        String url = String.format("%s?q=%s&page=%s&per_page=%s", environment.getProperty("api.url"), dataDto.getKeyword(), dataDto.getPage(), dataDto.getSize());
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer " + environment.getProperty("api.token"))
                .header("X-GitHub-Api-Version", "2022-11-28")
                .uri(new URI(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}
