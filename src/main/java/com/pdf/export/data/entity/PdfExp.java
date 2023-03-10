package com.pdf.export.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "pdf_exp")
public class PdfExp {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "payload")
    private String payload;

    @Column(name = "export_date")
    private Date exportDate;
}
