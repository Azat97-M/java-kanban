package ru.yandex.practicum.model;

public class SubTask extends Task{
    private final int epicId;

    public SubTask(String title, String description, TaskStatus status, int epicId) {
        super(title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public void setId(Integer id) {
        if (id != null && id.equals(epicId)) {
            throw new IllegalArgumentException("Подзадача не может быть своим эпиком.");
        }
        super.setId(id);
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description= '" + description + '\'' +
                ", status=" + status +
                ", epicId=" + epicId +
                '}';
    }
}
