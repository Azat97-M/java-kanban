package ru.yandex.practicum.manager.task;

import ru.yandex.practicum.model.Epic;
import ru.yandex.practicum.model.SubTask;
import ru.yandex.practicum.model.Task;

import java.util.List;

public interface TaskManager {
    Task getTaskById(int id);

    List<Task> getAllTasks();

    void createTask(Task task);

    void updateTasks(Task task);

    void deleteTaskById(int id);

    void deleteAllTasks();

    //Методы для Epic
    List<Epic> getAllEpics();

    Epic getEpicById(int id);

    void createEpic(Epic epic);

    void updateEpic(Epic epic);

    void deleteEpicById(int id);

    void deleteAllEpics();

    //Методы для SubTask
    List<SubTask> getAllSubTask();

    SubTask getSubTaskById(int id);

    void createSubTask(SubTask subTask);

    void updateSubTask(SubTask subTask);

    void deleteSubTaskById(int id);

    void deleteAllSubTasks();

    List<Task> getHistory();
}
