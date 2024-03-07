package com.poly.DATN_BookWorms.services;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.DATN_BookWorms.entities.Booking;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface BookingService {

    Booking create(JsonNode bookingData);

    Optional<Booking> findById(String id);


    List<Booking> findByUserId(String userId);

    List<Booking> findAll();

    List<Booking> findByStatusId(String orderStatusId);

    long countUnpaid();

    long countPaid();

    long countConfirm();

    long countDelivering();

    long countProcessed();

    long countCancel();

    long countRefund();

    long countBooking();

    List<Booking> unpaid();

    List<Booking> paid();

    List<Booking> confirm();

    List<Booking> delivering();

    List<Booking> processed();

    List<Booking> cancel();

    List<Booking> refund();

    Booking findByBookingId(String bookingId);


    List<Booking> findBookingsByShopIdAndOrderStatusID(Integer shopId, Integer orderStatusId);

    public Booking update(Booking bookingId);

    public Booking byBookingUserId(String bookingUserId);

    @Transactional
    List<Booking> findBookingsByShopId(Integer shopId);

    @Transactional
    void updateOrderStatus(String bookingId, Integer status);

    List<Booking> findByUserIdAndOrderStatus(String userId, Integer orderstatusid);
}
