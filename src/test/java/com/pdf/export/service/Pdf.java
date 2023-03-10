package com.pdf.export.service;

import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.vo.ResponseVo;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class Pdf {

    @Autowired
    PdfService pdfService;

    @Test
    public void pdfExport() throws JSONException {
        String payload = "[{\"login\":\"q\",\"id\":65956,\"node_id\":\"MDQ6VXNlcjY1OTU2\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/65956?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/q\",\"html_url\":\"https://github.com/q\",\"followers_url\":\"https://api.github.com/users/q/followers\",\"following_url\":\"https://api.github.com/users/q/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/q/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/q/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/q/subscriptions\",\"organizations_url\":\"https://api.github.com/users/q/orgs\",\"repos_url\":\"https://api.github.com/users/q/repos\",\"events_url\":\"https://api.github.com/users/q/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/q/received_events\",\"type\":\"User\",\"site_admin\":false,\"score\":1}]";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        ResponseVo dataSave = pdfService.exportPdf(new AddExportDto(dateFormat.format(new Date()), payload));
        Assertions.assertEquals(200, dataSave.getCode());
    }
}
