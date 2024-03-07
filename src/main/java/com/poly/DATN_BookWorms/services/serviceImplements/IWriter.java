package com.poly.DATN_BookWorms.services.serviceImplements;

import java.util.List;

import com.poly.DATN_BookWorms.entities.Writer;
import com.poly.DATN_BookWorms.entities.WritingMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.poly.DATN_BookWorms.repositories.WrittingmastersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.DATN_BookWorms.repositories.WritersRepo;
import com.poly.DATN_BookWorms.services.WriterService;

@Service
public class IWriter implements WriterService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    WrittingmastersRepo writtingmastersRepo;

    @Autowired
    WritersRepo writersRepo;

    @Override
    public List<WritingMaster> findAll() {
        // TODO Auto-generated method stub
        return writtingmastersRepo.findAll();
    }

    @Override
    public List<WritingMaster> getWrittingWithSHop(Integer shopid) {
        // TODO Auto-generated method stub
        logger.info("get list writtingmaster with shopid : {}", shopid);
        return writersRepo.listWrittingByType(shopid);
    }

    @Override
    public Writer save(Writer writer) {
        return writersRepo.save(writer);
    }

    @Override
    public List<Writer> findByBookId(Integer bookId) {
        return writersRepo.findBybookid(bookId);
    }
}
