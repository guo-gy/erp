package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Work;
import com.erp.repository.WorkRepository;
import java.util.List;
@Service
public class WorkService {

    @Autowired
    WorkRepository workRepository;

    // 增
    public Work addWork(String name, Integer originId, Integer targetId, boolean status) {
        Work work = new Work();
        work.setName(name);
        work.setOriginId(originId);
        work.setTargetId(targetId);
        work.setStatus(status);
        workRepository.save(work);
        return work;
    }

    // 删
    public void delWork(Integer id) {
        workRepository.deleteById(id);
    }

    // 改
    public void updWork(Integer id, boolean status) {
        Work work = workRepository.findById(id).orElse(null);
        if (work != null) {
            work.setStatus(status);
            workRepository.save(work);
        }
    }

    // 查
    public Work getWork(Integer id) {
        return workRepository.findById(id).orElse(null);
    }
    public List<Work> getWorkByOriginId(Integer originId) {
        return workRepository.findByOriginId(originId);
    }
    public List<Work> getWorkByTargetId(Integer targetId) {
        return workRepository.findByTargetId(targetId);
    }

}
