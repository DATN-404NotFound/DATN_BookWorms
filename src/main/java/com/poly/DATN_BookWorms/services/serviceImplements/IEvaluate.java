package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;
import java.util.stream.Collectors;

import com.poly.DATN_BookWorms.entities.Book;
import com.poly.DATN_BookWorms.entities.DetailBooking;
import com.poly.DATN_BookWorms.repositories.BooksRepo;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.DATN_BookWorms.entities.Evaluate;
import com.poly.DATN_BookWorms.repositories.EvaluatesRepo;
import com.poly.DATN_BookWorms.services.EvaluateService;

@Service
@Transactional
public class IEvaluate implements EvaluateService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    EvaluatesRepo evaluateRepo;
    @Autowired
    BooksRepo booksRepo;

    @Override
    public Evaluate create(Evaluate evaluate) {
        // TODO Auto-generated method stub
        return evaluateRepo.save(evaluate);
    }

    @Override
    public List<Evaluate> findEvaluatesByShopId(Integer shopId) {

        List<Book> booksList = booksRepo.findByShopid(shopId);
        List<DetailBooking> detailBookingList = booksList.stream().flatMap(books -> books.getListOfDetailBooking().stream()).collect(Collectors.toList());


        List<Evaluate> evaluateList = detailBookingList.stream().flatMap(detailbookings -> detailbookings.getListOfEvaluate().stream()).collect(Collectors.toList());

        return evaluateList;
    }


    @Override
    public List<Evaluate> findAll() {
        // TODO Auto-generated method stub
        return evaluateRepo.findAll();
    }
}
