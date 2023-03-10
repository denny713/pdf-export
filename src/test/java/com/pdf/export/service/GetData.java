package com.pdf.export.service;

import com.pdf.export.data.model.dto.SearchDataDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootTest
public class GetData {

    @Autowired
    DataService dataService;

    @Test
    public void getData() throws URISyntaxException, IOException, InterruptedException, JSONException {
        JSONObject result = dataService.getData(new SearchDataDto("Q", 1, 100));
        Assertions.assertNotNull(result);
    }
}
