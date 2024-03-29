package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.DATN_BookWorms.repositories.BookingsRepo;
import com.poly.DATN_BookWorms.repositories.BooksRepo;
import com.poly.DATN_BookWorms.repositories.DetailbookingsRepo;
import com.poly.DATN_BookWorms.repositories.ShoponlinesRepo;
import com.poly.DATN_BookWorms.services.AccountService;
import com.poly.DATN_BookWorms.services.BookingService;
import com.poly.DATN_BookWorms.services.MailService;
import com.poly.DATN_BookWorms.services.PaymentService;
import com.poly.DATN_BookWorms.utils.CRC32_SHA256;
import com.poly.DATN_BookWorms.utils.MailBody;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class IBooking implements BookingService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    BookingsRepo bookingRepo;
    @Autowired
    DetailbookingsRepo detailRepo;

    @Autowired
    HttpServletRequest request;

    @Autowired
    BooksRepo booksRepo;

    @Autowired
    ShoponlinesRepo shoponlinesRepo;
    @Autowired
    PaymentService paymentService;

    @Autowired
    CRC32_SHA256 crc32_SHA256;

    @Autowired
    AccountService accountService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    MailBody mailBody;

    @Autowired
    MailService mailService;


    String email = "";

    long bookId = 0;
    int countDetal = 0;

    @Override
    public Booking create(JsonNode bookingData) {

        logger.info("create bookings start.....");
        logger.info("input JsonNode with bookingData : {}", bookingData.toString());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            Booking booking = mapper.convertValue(bookingData, Booking.class);
            int a = ThreadLocalRandom.current().nextInt(1000, 9999);

            logger.info("have booking for create : {}", booking.toString());
            String userid = crc32_SHA256.getCodeCRC32C(request.getRemoteUser());
            logger.info("userid for booking : {}", userid);
            booking.setUserid(userid);
            booking.setBookingid(crc32_SHA256.getCodeCRC32C(booking.getUserid() + booking.getCreateat() + booking.getBookingid() + a));
            booking.getAccount().setUserid(userid);

            if (booking.getCostvoucher() == null || (booking.getCostvoucher() <= 0)) {
                booking.setCostvoucher(0.00);
            }
            booking.setCostvoucher(booking.getCostvoucher() * booking.getCost());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 3);
            booking.setTimefinish(c.getTime());

            bookingRepo.save(booking);
            logger.info("Create booking is successful with booking : {}", booking);
            TypeReference<List<DetailBooking>> type = new TypeReference<>() {
            };
            TypeReference<List<Payment>> type1 = new TypeReference<>() {
            };
            List<DetailBooking> details = mapper.convertValue(bookingData.get("listOfDetailBooking"), type);
            logger.info("list detalbooking in booking have size : {}", details.size());
            logger.info("list detalbooking start.... ");
            details.stream().peek(d -> {
                if (d != null)  {
                    logger.info("Detailbooking : {}", d.toString());
                    Book book = booksRepo.findById(d.bookid).get();

                    book.setQuantitysold(book.getQuantitysold() + d.getQuantity());
                    book.setQuantity(book.getQuantity() - d.getQuantity());
                    if (book.getQuantity() == 0) {
                        book.setStatues("Hết hàng");
                        book.setIsactive(false);
                    }
                    d.setBookingid(booking.getBookingid());
                    d.setBooking(booking);
                    d.setDbid(crc32_SHA256.getCodeCRC32C(userid + d.bookingid + d.bookid));
                    bookId = d.bookid;
                    logger.info("Detailbooking for create : {}", d.toString());
                    detailRepo.save(d);
                    email = d.getDbid();

                    booksRepo.save(book);
                    logger.info("Detailbooking for create is success full : {}", d.toString());
                }
            }).collect(Collectors.toList());


            List<Payment> payment = mapper.convertValue(bookingData.get("listOfPayments"), type1);
            logger.info("list Payment in booking have size : {}", payment.size());
            logger.info("list Payment start.... ");
            payment.stream().peek(d ->
            {
                logger.info("Payment : {}", d.toString());
                d.setBookingid(booking.getBookingid());
                d.setBooking(booking);
                d.setPaymentid(crc32_SHA256.getCodeCRC32C(userid + d.getBookingid()));
                if (d.getType()) {
                    ShopOnline shopon = booksRepo.s(bookId);

                    shopon.setTotal(shopon.getTotal() + booking.getCost());
                    d.setStatus("Đã thanh toán");
                    booking.getOrderStatus().setOrderstatusid(4);
                    booking.setOrderstatusid(4);
                    bookingRepo.save(booking);
                } else {
                    d.setPaid(null);
                    System.out.println("payment now");
                    d.setStatus("Chưa thanh toán");
                    booking.getOrderStatus().setOrderstatusid(3);
                    booking.setOrderstatusid(3);
                    bookingRepo.save(booking);
                    System.out.println("payment nows");
                }

            }).collect(Collectors.toList());
            logger.info("list Payment in booking have size : {}", payment.size());
            paymentService.saveAll(payment);
            System.out.println("now detailkflds;");

            sendMailSuccess(booking, countDetal, email);
            logger.info("Create bookings, detailbookings, payments is successfully");
            return booking;
        } catch (Exception e) {
            logger.error("Create bookings, detailbookings, payments is failed with error : {}", e);
            return null;
            // TODO: handle exception
        }
    }


    public void sendMailSuccess(Booking bookingReq, int count, String email) throws MessagingException {
        Booking booking = bookingRepo.findById(bookingReq.bookingid).get();
        System.out.println("email");
        DetailBooking detailbookings = detailRepo.findById(email).get();
        System.out.println("in detlkdd" + detailbookings.toString());
        String subject = "THÔNG BÁO ĐẶT ĐƠN THÀNH CÔNG";
        String personCancle = booking.account.fullname;

        String buyer = mailBody.mailSuccess(personCancle, personCancle, booking, "Đặt hàng thành công", count);
        String shoper = mailBody.mailSuccess(detailbookings.getBook().getShopOnline().getShopname(), personCancle, booking, "Đặt hàng thành công", count);
        mailService.send(booking.account.getEmail(), subject, buyer);
        mailService.send(detailbookings.getBook().getShopOnline().getAccount().getEmail(), subject, shoper);
        System.out.println("thành công ");
    }

    @Override
    public Optional<Booking> findById(String id) {
        // TODO Auto-generated method stub
        return bookingRepo.findById(id);
    }


    @Override
    public List<Booking> findByUserId(String userId) {
        logger.info("get booking by userid have userId : {}", userId);
        return bookingRepo.findBookingByUser(userId);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    @Override

    public List<Booking> findByStatusId(String orderStatusId) {
        logger.info("get booking by orderStatusId have orderStatusId : {}", orderStatusId);
        return bookingRepo.ListBookings_Status(orderStatusId);
    }

    @Override
    public Booking findByBookingId(String bookingId) {
        return bookingRepo.findBybookingid(bookingId);
    }

    @Override
    public List<Booking> findBookingsByShopIdAndOrderStatusID(Integer shopId, Integer orderStatusId) {
        List<Booking> allBookings = bookingRepo.findBookingsByShopId(shopId);

        return allBookings.stream()
                .filter(booking -> booking.getOrderstatusid().equals(orderStatusId))
                .collect(Collectors.toList());
    }


    @Override
    public List<Booking> unpaid() {
        // TODO Auto-generated method stub
        return bookingRepo.unpaid();
    }

    @Override
    public List<Booking> paid() {
        // TODO Auto-generated method stub
        return bookingRepo.paid();
    }

    @Override
    public List<Booking> confirm() {
        // TODO Auto-generated method stub
        return bookingRepo.confirm();
    }

    @Override
    public List<Booking> delivering() {
        // TODO Auto-generated method stub
        return bookingRepo.delivering();
    }

    @Override
    public List<Booking> processed() {
        // TODO Auto-generated method stub
        return bookingRepo.processed();
    }

    @Override
    public List<Booking> cancel() {
        // TODO Auto-generated method stub
        return bookingRepo.cancel();
    }

    @Override
    public List<Booking> refund() {
        // TODO Auto-generated method stub
        return bookingRepo.refund();
    }

    public long countUnpaid() {
        return bookingRepo.countUnpaid();
    }

    public long countPaid() {
        return bookingRepo.countPaid();
    }

    public long countConfirm() {
        return bookingRepo.countConfirm();
    }

    public long countDelivering() {
        return bookingRepo.countDelivering();
    }

    public long countProcessed() {
        return bookingRepo.countProcessed();
    }

    public long countCancel() {
        return bookingRepo.countCancel();
    }

    public long countRefund() {
        return bookingRepo.countRefund();
    }

    @Override
    public Booking update(Booking bookingId) {
        // TODO Auto-generated method stub
        return bookingRepo.save(bookingId);
    }

    @Override
    public Booking byBookingUserId(String bookingUserId) {
        // TODO Auto-generated method stub
        return bookingRepo.findById(bookingUserId).get();
    }

    @Override
    public List<Booking> findBookingsByShopId(Integer shopId) {
        return bookingRepo.findBookingsByShopId(shopId);
    }

    @Override
    @Transactional
    public void updateOrderStatus(String bookingId, Integer status) {
        Booking booking = bookingRepo.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setOrderstatusid(status);
            bookingRepo.save(booking);
        }
    }

    @Override
    public List<Booking> findByUserIdAndOrderStatus(String userId, Integer orderstatusid) {
        return bookingRepo.findByUserIdAndOrderStaturs(userId, orderstatusid);
    }

    public long countBooking() {
        // TODO Auto-generated method stub
        return bookingRepo.count();
    }
}
