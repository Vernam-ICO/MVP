package com.dimiroma.vernam.utils.pdfGenerator;


import com.dimiroma.vernam.areas.payment.dto.PaymentCreateViewModel;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {
    private static final String PDF_SOURCE = "E:\\mvp\\source.pdf";
    //private static final String PDF_SOURCE = "source.pdf";
    private static final String PDF_OUTPUT = "result.pdf";

    @Override
    public byte[] generatePdf(final PaymentCreateViewModel data) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(PDF_SOURCE);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(PDF_OUTPUT));
        AcroFields form = stamper.getAcroFields();

        //User Information
        if (data.getUser() != null) {
            form.setField("firstName", data.getUser().getFirstName());
            form.setField("lastName", data.getUser().getLastName());
            form.setField("email", data.getUser().getEmail());
            form.setField("phone", data.getUser().getPhone());
            form.setField("address", data.getUser().getAddress());
        }

        //Insurance Information
        if (data.getInsuranceAmount() != null) {
            form.setField("insuranceAmount", String.valueOf(data.getInsuranceAmount()));
        }

        if (data.getTokens() != null) {
            form.setField("tokens", String.valueOf(data.getTokens()));
        }

        if (data.getBroker() != null) {
            form.setField("broker", data.getBroker());
        }

        if (data.getTripType() != null) {
            form.setField("tripType", data.getTripType().name());
            if (data.getTripData().getFrom() != null && data.getTripData().getTo() != null) {
                String tripData = "From: " + data.getTripData().getFrom() + " To: " + data.getTripData().getTo();
                form.setField("tripData", tripData);
            }

            if (data.getTripData().getPeriod() != null && data.getTripData().getStartDate() != null) {
                String tripData = "Trip Period: " + data.getTripData().getPeriod() + " Start Date: " + data.getTripData().getTo();
                form.setField("tripData", tripData);
            }
        }

        if (data.getPurpose() != null) {
            form.setField("purpose", data.getPurpose().name());
        }

        if (data.getDestination() != null) {
            form.setField("destination", data.getDestination());
        }

        if (data.getGroupType() != null) {
            form.setField("groupType", data.getGroupType().name());
            if (data.getGroupData().getAge() != null) {
                String groupData = "Age: " + data.getGroupData().getAge();
                form.setField("groupData", groupData);
            } else {
                StringBuilder sb = new StringBuilder();
                if (data.getGroupData().getChildren() != null) {
                    sb.append("Number of Children(0 - 12 years) - " + data.getGroupData().getChildren());
                    sb.append("\n");
                }
                if (data.getGroupData().getAdults() != null) {
                    sb.append("Number of Adults(13 - 64 years) - " + data.getGroupData().getAdults());
                    sb.append("\n");
                }
                if (data.getGroupData().getYoungSeniors() != null) {
                    sb.append("Number of Adults(64 - 67 years) - " + data.getGroupData().getYoungSeniors());
                    sb.append("\n");
                }
                if (data.getGroupData().getMiddleSeniors() != null) {
                    sb.append("Number of Adults(68 - 70 years) - " + data.getGroupData().getMiddleSeniors());
                    sb.append("\n");
                }
                if (data.getGroupData().getOldSeniors() != null) {
                    sb.append("Number of Adults(71 - 79 years) - " + data.getGroupData().getOldSeniors());
                }
                String groupData = sb.toString();
                form.setField("groupData", groupData);
            }
        }

        if (data.isChangeOfStayExpenses()) {
            form.setField("changeOfStayExpenses", "yes");
        } else {
            form.setField("changeOfStayExpenses", "no");
        }

        if (data.isLegalAidAbroad()) {
            form.setField("legalAidAbroad", "yes");
        } else {
            form.setField("legalAidAbroad", "no");
        }

        if (data.isPersonalResponsibility()) {
            form.setField("personalResponsibility", "yes");
        } else {
            form.setField("personalResponsibility", "no");
        }

        if (data.isReliableWallet()) {
            form.setField("reliableWallet", "yes");
        } else {
            form.setField("reliableWallet", "no");
        }

        stamper.setFormFlattening(true);

        stamper.close();

        reader.close();

        byte[] fileBytes = getFileBytes(PDF_OUTPUT);
        new File(PDF_OUTPUT).delete();

        return fileBytes;
    }

    private byte[] getFileBytes(final String src) throws IOException {
        Path pdfPath = Paths.get(src);
        return Files.readAllBytes(pdfPath);
    }
}
