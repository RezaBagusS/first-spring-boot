package com.springboot.first_springboot_project.controller;

import com.springboot.first_springboot_project.dto.request.TaskRequest;
import com.springboot.first_springboot_project.dto.response.ApiResponse;
import com.springboot.first_springboot_project.dto.response.TaskResponse;
import com.springboot.first_springboot_project.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAllTasks() {
        return ResponseEntity.ok(new ApiResponse<>("success", "Daftar tugas ditemukan", taskService.getAllTasks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>("success", "Task ditemukan", taskService.getTaskById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> addTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse createdTask = taskService.addTask(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Task berhasil ditambahkan", createdTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        TaskResponse updatedTask = taskService.updateTask(id, request);
        return ResponseEntity.ok(new ApiResponse<>("success", "Task berhasil diperbarui", updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Task berhasil dihapus", null));
    }
}

