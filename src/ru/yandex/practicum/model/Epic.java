package ru.yandex.practicum.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private final List<Integer> subTaskIds;

    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription, TaskStatus.NEW);
        this.subTaskIds = new ArrayList<>();
    }

    public List<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void addSubTaskId(int subTaskId) {
        subTaskIds.add(subTaskId);
    }

    public void removeSubTaskId(int subTaskId) {
        subTaskIds.remove((Integer) subTaskId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", description= '" + taskDescription + '\'' +
                ", status=" + taskStatus +
                ", subTaskIds=" + subTaskIds +
                '}';
    }
}
