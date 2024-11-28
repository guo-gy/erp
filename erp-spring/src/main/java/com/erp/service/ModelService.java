package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Model;
import com.erp.repository.ModelRepository;
import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    // 增
    public void addmodel(String modelName) {
        Model model = new Model();
        model.setName(modelName);
        modelRepository.save(model);
    }

    // 删
    public void delmodel(Integer modelId) {
        modelRepository.deleteById(modelId);
    }

    // 改
    public void updmodel(Integer modelId, String modelName) {
        Model model = modelRepository.findById(modelId).orElse(null);
        if (model != null) {
            model.setName(modelName);
            modelRepository.save(model);
        }
    }

    // 查
    public Model getModelById(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }

    public List<Model> getAllmodels() {
        return modelRepository.findAll();
    }
}