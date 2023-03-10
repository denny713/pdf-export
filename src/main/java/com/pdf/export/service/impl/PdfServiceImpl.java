package com.pdf.export.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.export.data.model.dto.AddExportDto;
import com.pdf.export.data.model.vo.ResponseVo;
import com.pdf.export.service.PdfService;
import com.pdf.export.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService {

    @Override
    public ResponseVo exportPdf(AddExportDto expDto) throws JSONException {
        JSONArray jsonArr = new JSONArray(expDto.getPayload());
        Document doc = new Document(PageSize.A4.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\" + expDto.getFilename() + ".pdf"));
            doc.open();
            Font headerFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
            headerFont.setSize(18);
            headerFont.setColor(BaseColor.BLACK);
            Font subHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            subHeaderFont.setSize(18);
            subHeaderFont.setColor(BaseColor.BLACK);
            Font subFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            subFont.setSize(14);
            subFont.setColor(BaseColor.BLACK);
            PdfPTable table;
            Paragraph title = new Paragraph("Github User List", headerFont);
            title.setAlignment(1);
            title.setSpacingAfter(15f);
            doc.add(title);
            doc.add(Chunk.NEWLINE);
            table = new PdfPTable(20);
            generateTableTitle(table);
            generateTableContent(table, jsonArr);
            table.setHorizontalAlignment(3);
            table.setWidthPercentage(100f);
            doc.add(table);
            doc.addCreationDate();
            doc.addAuthor("System");
            doc.addTitle("Github User List");
            doc.addCreator("System");
            doc.close();
            writer.close();
            return ResponseUtil.setResponse(HttpStatus.OK.value(), "Success Export PDF File", null);
        } catch (Exception e) {
            return ResponseUtil.setResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
        }
    }

    public void generateTableTitle(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.RED);
        cell.setHorizontalAlignment(1);
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
        font.setSize(12);
        font.setColor(BaseColor.BLACK);
        for (String str : getTitles()) {
            cell.setPhrase(new Phrase(str, font));
            table.addCell(cell);
        }
    }

    public void generateTableContent(PdfPTable table, JSONArray jsonArr) throws JSONException {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(1);
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setSize(12);
        font.setColor(BaseColor.BLACK);
        int no = 0;
        for (int x = 0; x < jsonArr.length(); x++) {
            no++;
            JSONObject obj = jsonArr.getJSONObject(x);
            cell.setPhrase(new Phrase(String.valueOf(no), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("login"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(obj.getInt("id")), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("node_id"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("avatar_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("gravatar_id"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("html_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("followers_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("following_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("gists_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("starred_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("subscriptions_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("organizations_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("repos_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("events_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("received_events_url"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(obj.getString("type"), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(obj.getBoolean("site_admin")), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.valueOf(obj.getInt("score")), font));
            table.addCell(cell);
        }
    }

    private List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        titles.add("No");
        titles.add("Login");
        titles.add("ID");
        titles.add("Node ID");
        titles.add("Avatar URL");
        titles.add("Gravatar ID");
        titles.add("URL");
        titles.add("HTML URL");
        titles.add("Followers URL");
        titles.add("Following URL");
        titles.add("Gist URL");
        titles.add("Starred URL");
        titles.add("Subscriptions URL");
        titles.add("Organizations URL");
        titles.add("Repos URL");
        titles.add("Events URL");
        titles.add("Received Events URL");
        titles.add("Type");
        titles.add("Site Admin");
        titles.add("Score");
        return titles;
    }
}
