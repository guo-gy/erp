package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.module;
import com.erp.repository.ModuleRepository;
import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    // 增
    public void addmodule(String moduleName) {
        module module = new module();
        module.setName(moduleName);
        moduleRepository.save(module);
    }

    // 删
    public void delmodule(Integer moduleId) {
        moduleRepository.deleteById(moduleId);
    }

    // 改
    public void updmodule(Integer moduleId, String moduleName) {
        module module = moduleRepository.findById(moduleId).orElse(null);
        if (module != null) {
            module.setName(moduleName);
            moduleRepository.save(module);
        }
    }

    // 查
    public module getmoduleById(Integer id) {
        return moduleRepository.findById(id).orElse(null);
    }

    public module getmoduleByName(String name) {
        return moduleRepository.findByName(name);
    }

    public List<module> getAllmodules() {
        return moduleRepository.findAll();
    }
}