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
import com.erp.entity.User;
import com.erp.service.UserService;
import com.erp.utils.JsonResponse;
import com.erp.utils.Request.addWorkRequest;
import java.util.List;
import com.erp.utils.Request.updWorkRequest;

@RestController
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private WorkService workService; // 注入WorkService
    @Autowired
    private UserService userService; // 注入UserService

    @PostMapping("/updwork")
    public JsonResponse<Integer> updWork(@RequestBody updWorkRequest request) {
        JsonResponse<Integer> response = new JsonResponse<Integer>(false, "添加失败", null);
        try {
            workService.updWork(request.id, request.status);
            response.success = true;
            response.message = "完成任务成功";
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
            User user = userService.getUserByName(request.targetName);
            if (user == null) {
                response.message = "目标用户不存在";
                return response;
            }
            Work work = workService.addWork(request.workName, request.originId, user.getId(), request.status);
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

}
