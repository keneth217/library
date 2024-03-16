package com.samis.reports;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.samis.books.Book;
import com.samis.books.BookTransaction;
import com.samis.books.BookWriteOff;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PdfGenerator {
    private final String documentUrl = "/var/www/resources.leonteqsecurity.com/docs/";

    private String generateUniqueFileName() {
        String randomString = UUID.randomUUID().toString().replace("-", "");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "report_" + randomString + "_" + timestamp;
    }

    public String generatePdfTransaction(List<BookTransaction> content) throws DocumentException, FileNotFoundException {
        String docName = generateUniqueFileName() + ".pdf";
        String filePath = documentUrl + docName;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.NORMAL, BaseColor.BLACK);
            Font subtitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.ITALIC, BaseColor.BLUE);
            Paragraph title = new Paragraph("Borrowed Books & Returned Books Report", titleFont);
            Paragraph subtitle = new Paragraph("date: " + LocalDate.now(), subtitleFont);
            title.setSpacingAfter(10);
            subtitle.setSpacingAfter(10);
            title.setAlignment(Element.ALIGN_CENTER);
            subtitle.setAlignment(Element.ALIGN_RIGHT);
            document.add(title);
            document.add(subtitle);
            PdfPTable table = new PdfPTable(7); // 7 columns for the BookTransaction fields
            table.setWidthPercentage(100);
            addTableHeader(table);
            addTableContent(table, content);

            document.add(table);
        } finally {
            document.close();
        }
        return docName;
    }

    private void addTableHeader(PdfPTable table) {
        PdfPCell cell;

        // Define the header cells and set their properties
        cell = new PdfPCell(new Phrase("ID"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Student ID"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("School Name"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Book Reference No"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Date Borrowed"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Date to Return"));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Returned"));
        table.addCell(cell);
    }

    private void addTableContent(PdfPTable table, List<BookTransaction> content) {
        for (BookTransaction transaction : content) {
            table.addCell(String.valueOf(transaction.getId()));
            table.addCell(transaction.getStudentId());
            table.addCell(transaction.getSchoolName());
            table.addCell(transaction.getBookRefNo());
            table.addCell(transaction.getDateBorrowed());
            table.addCell(transaction.getDateToReturn());
            table.addCell(String.valueOf(transaction.isBookIsReturned()));
        }
    }


    public String allbooks(List<Book> allBooks) throws DocumentException, FileNotFoundException {
        String docName = generateUniqueFileName() + ".pdf";
        String filePath = documentUrl + docName;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD, BaseColor.RED);
            Paragraph title = new Paragraph("All Books Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            PdfPTable table = new PdfPTable(4); // Specify the number of columns for the table

            // Add table headers
            table.addCell(createTableCell("Title", tableHeaderFont));
            table.addCell(createTableCell("Subject Name", tableHeaderFont));
            table.addCell(createTableCell("Form", tableHeaderFont));
            table.addCell(createTableCell("Date Received", tableHeaderFont));

            // Add book information to the table
            for (Book book : allBooks) {
                table.addCell(createTableCell(book.getBookName(), contentFont));
                table.addCell(createTableCell(book.getSubjectName(), contentFont));
                table.addCell(createTableCell(book.getClassForm(), contentFont));
                table.addCell(createTableCell(book.getDateReceive(), contentFont));
            }

            document.add(table);
        } finally {
            document.close();
        }
        return docName;
    }

    private PdfPCell createTableCell(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }


    public String WriteOffBooks(List<BookWriteOff> writeOffs) throws DocumentException, FileNotFoundException {
        String docName = generateUniqueFileName() + ".pdf";
        String filePath = documentUrl + docName;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.BOLD, BaseColor.RED);
            Paragraph title = new Paragraph("Write off Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            PdfPTable table = new PdfPTable(3); // Specify the number of columns for the table

            // Add table headers

            table.addCell(createTableCellWriteoff("Book Ref", tableHeaderFont));
            table.addCell(createTableCellWriteoff("date wroteoff", tableHeaderFont));
            table.addCell(createTableCellWriteoff("Reason", tableHeaderFont));

            // Add book information to the table
            for (BookWriteOff book : writeOffs) {
                table.addCell(createTableCell(book.getBookRef(), contentFont));
                table.addCell(createTableCell(book.getDateWriteOff(), contentFont));
                table.addCell(createTableCell(book.getWhyWriteOff(), contentFont));
            }

            document.add(table);
        } finally {
            document.close();
        }
        return docName;
    }

    private PdfPCell createTableCellWriteoff(String content, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }

}
