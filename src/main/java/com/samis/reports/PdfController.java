package com.samis.reports;

import com.itextpdf.text.DocumentException;
import com.samis.books.BookTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports/")
@RequiredArgsConstructor
public class PdfController {
    private final PdfGenerator pdfGenerator;
    private final PdfService pdfService;

    @GetMapping("transactionReports/")
    public String getbookTransaction(@RequestHeader("Authorization") String token) throws DocumentException, FileNotFoundException {
        return pdfGenerator.generatePdfTransaction(pdfService.bookTransaction(token));

    }

    @GetMapping("allbooks/")
    public String allbooks(@RequestHeader("Authorization") String token) throws DocumentException, FileNotFoundException {
        return pdfGenerator.allbooks(pdfService.allBooks(token));
    }

    @GetMapping("writeoff/")
    public String writeOff(@RequestHeader("Authorization") String token) throws DocumentException, FileNotFoundException {
        return pdfGenerator.WriteOffBooks(pdfService.Writeoff(token));
    }
}
