package ru.yandex.practicum.model;

public class SubTask extends Task{
    private final int epicId;

    public SubTask(String taskName, String taskDescription, TaskStatus taskStatus, int epicId) {
        super(taskName, taskDescription, taskStatus);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", description= '" + taskDescription + '\'' +
                ", status=" + taskStatus +
                ", epicId=" + epicId +
                '}';
    }
}
