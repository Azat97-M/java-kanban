package ru.yandex.practicum.model;

public class Task {
    protected String taskName;
    protected String taskDescription;
    protected int taskId;
    protected TaskStatus taskStatus;

    public Task(String taskName, String taskDescription, TaskStatus taskStatus){
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
    }


    public int getTaskId() {
        return taskId;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return taskId == task.taskId;
    }

    @Override
    public final int hashCode() {
        return Integer.hashCode(taskId);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", description= '" + taskDescription + '\'' +
                ", status=" + taskStatus +
                '}';
    }
}
