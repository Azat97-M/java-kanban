package ru.yandex.practicum.manager.task;

import ru.yandex.practicum.model.*;
import ru.yandex.practicum.manager.history.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private int taskIdCount = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();

    private final HistoryManager historyManager;

    public InMemoryTaskManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    // Методы для Task
    private int generateTaskId() {
        return taskIdCount++;
    }

    private void assignId(Task task) {
        if (task.getId() == null || tasks.containsKey(task.getId())) {
            task.setId(generateTaskId());
        }
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    @Override
    public void createTask(Task task) {
        assignId(task);
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateTasks(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    //Методы для Epic
    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public void createEpic(Epic epic) {
        assignId(epic);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void updateEpic(Epic epic) {
        calculateEpicStatus(epic);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            for (Integer subTaskId : new ArrayList<>(epic.getSubTaskIds())) {
                subTasks.remove(subTaskId);
            }
        }
        epics.remove(id);
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    //Методы для SubTask
    @Override
    public List<SubTask> getAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public SubTask getSubTaskById(int id) {
        SubTask subTask = subTasks.get(id);
        historyManager.add(subTask);
        return subTask;
    }

    @Override
    public void createSubTask(SubTask subTask) {
        Epic epic = epics.get(subTask.getEpicId());
        if (epic == null) {
            throw new IllegalArgumentException("Эпик с id " + subTask.getEpicId() + " не существует." +
                                               " Невозможно добавить подзадачу.");
        }

        if (subTask.getId() != null) { // Если значение устанавливается вручную
            if (subTask.getEpicId() == subTask.getId()) {
                throw new IllegalArgumentException("Подзадача не может быть своим же эпиком.");
            }
        }

        assignId(subTask);
        subTasks.put(subTask.getId(), subTask);
        epic.addSubTaskId(subTask.getId());
        calculateEpicStatus(epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        calculateEpicStatus(epic);
    }

    @Override
    public void deleteSubTaskById(int id) {
        for (SubTask subTask : new ArrayList<>(subTasks.values())) {
            Epic epic = epics.get(subTask.getEpicId());
            epic.removeSubTaskId(id);
            calculateEpicStatus(epic);
        }
        subTasks.remove(id);
    }

    @Override
    public void deleteAllSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTaskIds().clear();
            calculateEpicStatus(epic);
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private void calculateEpicStatus(Epic epic) {
        List<Integer> subTaskIds = epic.getSubTaskIds();
        if (subTaskIds.isEmpty()) {
            epic.setTaskStatus(TaskStatus.NEW);
            return;
        }
        boolean allNew = true;
        boolean allDone = true;
        for (Integer subTaskId : subTaskIds) {
            SubTask subTask = subTasks.get(subTaskId);
            if (subTask != null) {
                if (subTask.getTaskStatus() != TaskStatus.NEW) {
                    allNew = false;
                }
                if (subTask.getTaskStatus() != TaskStatus.DONE) {
                    allDone = false;
                }
            }
        }
        if (allNew) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (allDone) {
            epic.setTaskStatus(TaskStatus.DONE);
        } else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
