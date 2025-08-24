package com.springboot.first_springboot_project.service;

import com.springboot.first_springboot_project.dto.request.TaskRequest;
import com.springboot.first_springboot_project.dto.response.TaskResponse;
import com.springboot.first_springboot_project.entity.Task;
import com.springboot.first_springboot_project.exception.TaskNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    public List<TaskResponse> getAllTasks() {
        return tasks.stream()
                .map(task -> new TaskResponse(task.getId(), task.getTitle(), task.isCompleted()))
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task dengan ID " + id + " tidak ditemukan"));
        return new TaskResponse(task.getId(), task.getTitle(), task.isCompleted());
    }

    public TaskResponse addTask(TaskRequest request) {
        Task task = new Task(nextId++, request.title(), false);
        tasks.add(task);
        return new TaskResponse(task.getId(), task.getTitle(), task.isCompleted());
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task dengan ID " + id + " tidak ditemukan"));
        task.setTitle(request.title());
        return new TaskResponse(task.getId(), task.getTitle(), task.isCompleted());
    }

    public void deleteTask(Long id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        if (!removed) {
            throw new TaskNotFoundException("Task dengan ID " + id + " tidak ditemukan");
        }
    }
}
