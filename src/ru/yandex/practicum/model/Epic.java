package ru.yandex.practicum.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private final List<Integer> subTaskId;

    public Epic(String title, String description) {
        super(title, description, TaskStatus.NEW);
        this.subTaskId = new ArrayList<>();
    }

    public List<Integer> getSubTaskIds() {
        return subTaskId;
    }

    public void addSubTaskId(int subId) {
        if (this.id == subId) {
            throw new IllegalArgumentException("Нельзя добавить эпик в подзадачу");
        }
        subTaskId.add(subId);
    }

    public void removeSubTaskId(int subId) {
        subTaskId.remove((Integer) subId);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description= '" + description + '\'' +
                ", status=" + status +
                ", subTaskId=" + subTaskId +
                '}';
    }
}
