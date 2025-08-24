package com.springboot.first_springboot_project.controller;

// import org.springframework.web.bind.annotation.*;
// import java.util.*;

// @RestController
// @RequestMapping("/tasks")
public class test {

    // private final List<Task> tasks = new ArrayList<>();
    // private Long nextId = 1L;

    // // GET /tasks - Ambil semua tugas
    // @GetMapping
    // public List<Task> getAllTasks() {
    //     return tasks;
    // }

    // // GET /tasks/{id} - Ambil satu tugas berdasarkan ID
    // @GetMapping("/{id}")
    // public Task getTaskById(@PathVariable Long id) {
    //     return tasks.stream()
    //             .filter(task -> task.getId().equals(id))
    //             .findFirst()
    //             .orElseThrow(() -> new RuntimeException("Task not found"));
    // }

    // // POST /tasks - Tambah tugas baru
    // @PostMapping
    // public Task createTask(@RequestBody Task task) {
    //     task.setId(nextId++);
    //     tasks.add(task);
    //     return task;
    // }

    // // PUT /tasks/{id} - Update tugas
    // @PutMapping("/{id}")
    // public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
    //     Task task = tasks.stream()
    //             .filter(t -> t.getId().equals(id))
    //             .findFirst()
    //             .orElseThrow(() -> new RuntimeException("Task not found"));

    //     task.setTitle(updatedTask.getTitle());
    //     task.setCompleted(updatedTask.isCompleted());
    //     return task;
    // }

    // // DELETE /tasks/{id} - Hapus tugas
    // @DeleteMapping("/{id}")
    // public String deleteTask(@PathVariable Long id) {
    //     boolean removed = tasks.removeIf(task -> task.getId().equals(id));
    //     if (!removed) {
    //         throw new RuntimeException("Task not found");
    //     }
    //     return "Task deleted successfully";
    // }
}
