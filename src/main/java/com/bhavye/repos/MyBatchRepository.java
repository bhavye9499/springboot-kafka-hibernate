package com.bhavye.repos;

import com.bhavye.models.MyBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBatchRepository extends JpaRepository<MyBatch, Integer> {
}
