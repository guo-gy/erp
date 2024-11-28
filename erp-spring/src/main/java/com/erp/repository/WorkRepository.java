package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.Work;
import java.util.List;
@Repository
public interface WorkRepository extends JpaRepository<Work, Integer>  {
    List<Work> findByOriginId(Integer originId);
    List<Work> findByTargetId(Integer targetId);
}
