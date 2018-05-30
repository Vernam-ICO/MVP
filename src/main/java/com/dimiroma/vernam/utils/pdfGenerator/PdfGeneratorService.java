package com.dimiroma.vernam.utils.pdfGenerator;

import com.dimiroma.vernam.areas.payment.dto.PaymentCreateViewModel;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface PdfGeneratorService {

    byte[] generatePdf(final PaymentCreateViewModel data) throws IOException, DocumentException;
}
