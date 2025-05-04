package ru.yandex.practicum.model;

public class Task {
    protected String title;
    protected String description;
    protected Integer id;
    protected TaskStatus status;

    public Task(String title, String description, TaskStatus status){
        this.title = title;
        this.description = description;
        this.status = status;
    }


    public Integer getId() {
        return id;
    }

    public TaskStatus getTaskStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTaskStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description= '" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
