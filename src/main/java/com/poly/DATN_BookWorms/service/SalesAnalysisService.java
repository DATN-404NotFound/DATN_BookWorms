package com.poly.DATN_BookWorms.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface SalesAnalysisService {
    double getMonthSales(Date startDate, Date endDate);

    double getMonthOrder(Date month);
}
