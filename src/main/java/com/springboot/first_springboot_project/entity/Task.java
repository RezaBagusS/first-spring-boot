package com.springboot.first_springboot_project.entity;

public class Task {
    private Long id;
    private String title;
    private boolean isCompleted;

    public Task(Long id, String title, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return isCompleted; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
