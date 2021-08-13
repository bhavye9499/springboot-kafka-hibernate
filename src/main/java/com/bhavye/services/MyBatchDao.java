package com.bhavye.services;

import com.bhavye.models.MyBatch;
import com.bhavye.repos.MyBatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyBatchDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatch.class);

    private final MyBatchRepository myBatchRepository;

    @Autowired
    public MyBatchDao(MyBatchRepository myBatchRepository) {
        this.myBatchRepository = myBatchRepository;
    }

    public void createBatch(MyBatch batch) {
        myBatchRepository.save(batch);
        LOGGER.info("Batch is created");
    }

}
