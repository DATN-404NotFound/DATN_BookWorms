package com.poly.DATN_BookWorms.rest.controller;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.poly.DATN_BookWorms.entities.Detailbookings;
import com.poly.DATN_BookWorms.service.BookingService;
import com.poly.DATN_BookWorms.service.DetailBookingService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Account;
import com.poly.DATN_BookWorms.entities.Bookings;
import com.poly.DATN_BookWorms.service.AccountService;
import com.poly.DATN_BookWorms.service.BookingService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/bookings")
public class BookingRestController {
    @Autowired
    BookingService bookingService;

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    DetailBookingService detailBookingService;
    @Autowired
    CRC32_SHA256 crc;

    @PostMapping()
    public Bookings create(@RequestBody JsonNode bookingData, HttpServletRequest request) {
        return bookingService.create(bookingData);

    }

    @GetMapping("")
    public List<Bookings> getAll() {
        return bookingService.findAll();
    }
    // @GetMapping
    // public List<Bookings> getAll(){

    // 	return bookingService.findAll();
    // }
    @GetMapping("/user")
    public List<Bookings> booking() {

        return bookingService.findByUserId(crc.getCodeCRC32C(httpServletRequest.getRemoteUser()));
    }

    @GetMapping("/findByBookingId/{bookingId}")
    public List<Detailbookings> findByBookingId(@PathVariable String bookingId) {
        return detailBookingService.findByBookingId(bookingId);
    }

    @GetMapping("/findByBookingIdDetail/{bookingId}")
    public Bookings findByBookingIdDetail(@PathVariable String bookingId) {
        return bookingService.findByBookingId(bookingId);
    }

    @GetMapping("/generate/{bookingid}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable String bookingid) {
        try {
            Rectangle pageSize = new Rectangle(PageSize.A6);

            float marginLeft = 0.5f;
            float marginRight = 0.5f;
            float marginTop = 0.5f;
            float marginBottom = 0.5f;

            Document document = new Document(pageSize, marginLeft, marginRight, marginTop, marginBottom);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();
            BaseFont unicodeFont = BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(unicodeFont, 18, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
            com.itextpdf.text.Font contentFont = new com.itextpdf.text.Font(unicodeFont, 12, BaseColor.BLACK.getRGB());
            Bookings bookings = bookingService.findByBookingId(bookingid);
            List<Detailbookings> detailbookings = detailBookingService.findByBookingId(bookingid);

            // Create a centered Paragraph for the title
            Paragraph titleParagraph = new Paragraph(detailbookings.get(0).getBooks().getShoponlines().getShopname().toUpperCase() + " STORE", titleFont);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);

            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));
            String inputString = "    ------------------------------------------------------------------    ";

            System.out.println("Số lượng dấu gạch ngang: " + inputString.length());
            document.add(new Paragraph("    Ngày: " + new Date(), contentFont));
            document.add(new Paragraph("    Mã hóa đơn: " + bookings.getBookingid(), contentFont));
            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));
            document.add(new Paragraph("    Địa chỉ: Phường Nhà Mát, Thành phố Bạc Liêu", contentFont));
            document.add(new Paragraph("    Bạc Liêu", contentFont));
            document.add(new Paragraph("    Số điện thoại: 0378977287", contentFont));
            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));
            document.add(new Paragraph("    Địa chỉ: Phường Nhà Mát, Thành phố Bạc Liêu", contentFont));
            document.add(new Paragraph("    Bạc Liêu", contentFont));
            document.add(new Paragraph("    Số điện thoại: 0378977287", contentFont));
            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));

            // Center-align the product details table
            document.add(new Paragraph("            Số lượng                                    Giá", contentFont));
            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));
            for (Detailbookings detailbooking : detailbookings) {
                Paragraph productDetails = new Paragraph("    " + detailbooking.getBooks().getBookname(), contentFont);
                productDetails.add(new Paragraph("                " + detailbooking.getQuantity() + "                                         " + detailbooking.getBooks().getPrice()));
                productDetails.setAlignment(Element.ALIGN_LEFT);
                document.add(productDetails);
            }


            // Center-align the total cost and thank you message
            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));
            Paragraph totalCostParagraph = new Paragraph("Tổng cộng:                         " + bookings.getCost(), contentFont);
            totalCostParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(totalCostParagraph);
            document.add(new Paragraph("    ------------------------------------------------------------------    ", contentFont));
            Paragraph thankYouParagraph = new Paragraph("Cảm ơn quý khách đã mua hàng!", contentFont);
            thankYouParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYouParagraph);

            document.close();
            byte[] pdfContents = baos.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("inline", "example.pdf");
            return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
