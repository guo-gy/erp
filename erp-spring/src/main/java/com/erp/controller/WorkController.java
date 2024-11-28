package com.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.erp.entity.Work;
import com.erp.service.WorkService;

import com.erp.utils.JsonResponse;
import java.util.List;

@RestController
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private WorkService workService; // 注入CompanyService

    @PostMapping("/updwork")
    public JsonResponse<Integer> updWork(@RequestBody updWorkRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "添加失败", null);
        try {
            workService.updWork(request.id, true);
            response.success = true;
            response.message = "添加任务成功";
            response.data = request.id;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @PostMapping("/addwork")
    public JsonResponse<Integer> addWork(@RequestBody addWorkRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "添加失败", null);
        try {
            Work work = workService.addWork(request.name, request.originId, request.targetId, request.status);
            response.success = true;
            response.message = "添加任务成功";
            response.data = work.getId();
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{userId}/orgin")
    public JsonResponse<List<Work>> getOriginWork(@PathVariable("userId") Integer userId) {
        JsonResponse<List<Work>> response = new JsonResponse<List<Work>>(false, "获取失败", null);
        try {
            List<Work> work = workService.getWorkByOriginId(userId);
            response.success = true;
            response.message = "获取派出任务成功";
            response.data = work;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    @GetMapping("/{userId}/target")
    public JsonResponse<List<Work>> getTargetWork(@PathVariable("userId") Integer userId) {
        JsonResponse<List<Work>> response = new JsonResponse<List<Work>>(false, "获取失败", null);
        try {
            List<Work> work = workService.getWorkByTargetId(userId);
            response.success = true;
            response.message = "获取收到任务成功";
            response.data = work;
        } catch (Exception e) {
            response.success = false;
            response.message = e.getMessage();
        }
        return response;
    }

    public class addWorkRequest {
        public String name;
        public Integer originId;
        public Integer targetId;
        public Boolean status;
    }

    public class updWorkRequest {
        public Integer id;
    }
}
