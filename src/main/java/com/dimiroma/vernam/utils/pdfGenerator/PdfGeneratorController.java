package com.dimiroma.vernam.utils.pdfGenerator;

import com.dimiroma.vernam.areas.payment.dto.PaymentCreateViewModel;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class PdfGeneratorController {
    private final PdfGeneratorService pdfGeneratorService;

    @Autowired
    public PdfGeneratorController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping("/get-pdf")
    public ResponseEntity<InputStreamResource> getPdf(final @RequestBody PaymentCreateViewModel data) throws IOException, DocumentException {
        byte[] pdf = this.pdfGeneratorService.generatePdf(data);
        InputStream bis = new ByteArrayInputStream(pdf);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(bis));
    }
}
