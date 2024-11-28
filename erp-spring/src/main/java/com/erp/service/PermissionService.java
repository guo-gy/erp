package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.Permission;
import com.erp.repository.PermissionRepository;
import java.util.List;

@Service
public class PermissionService {
 
    @Autowired
    private PermissionRepository permissionRepository;

    //增

    public void addPermission(Integer userId, Integer permissionId, Integer permissionLevel) {
        Permission permission =new Permission();
        permission.setUserId(userId);
        permission.setPermissionId(permissionId);
        permission.setPermissionLevel(permissionLevel);
    }

    //删
    public void delPermission(Integer id)
    {
        permissionRepository.deleteById(id);
    }

    //改
    public void updPermission(Integer id,Integer permissionLevel)
    {
        Permission permission = permissionRepository.findById(id).get();
        if(permission != null)
        {
            permission.setPermissionLevel(permissionLevel);
            permissionRepository.save(permission);
        }
        
    }
    //查
    public Permission getPermissionById(Integer id)
    {
        return permissionRepository.findById(id).orElse(null);
    }
    public List<Permission> getPermissionByUserId(Integer userId)
    {
        return permissionRepository.findByUserId(userId);
    }
    public Permission getPermissionByUserIdAndPermissionId(Integer userId,Integer permissionId)
    {
        for (Permission permission : permissionRepository.findByUserId(userId)) {
            if (permission.getPermissionId().equals(permissionId)) {
                return permission;
            }
        }
        return null;
    }

}
