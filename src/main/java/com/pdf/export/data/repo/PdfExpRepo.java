package com.pdf.export.data.repo;

import com.pdf.export.data.entity.PdfExp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfExpRepo extends JpaRepository<PdfExp, Long> {

    public PdfExp findFirstByOrderByIdDesc();

    public PdfExp findFirstByFilename(String filename);

    public List<PdfExp> findAllByOrderById();
}
