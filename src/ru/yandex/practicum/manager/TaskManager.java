package ru.yandex.practicum.manager;
import ru.yandex.practicum.model.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private int taskIdCount = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();

    // Методы для Task
    private int generateTaskId() {
        return taskIdCount++;
    }

    private void assignId(Task task) {
        if (task.getId() == null) {
            task.setId(generateTaskId());
        }
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    public void createTask(Task task) {
        assignId(task);
        tasks.put(task.getId(), task);
    }

    public void updateTasks(Task task) {
        tasks.put(task.getId(), task);
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    //Методы для Epic
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void createEpic(Epic epic) {
        assignId(epic);
        epics.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic) {
        calculateEpicStatus(epic);
        epics.put(epic.getId(), epic);
    }

    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);
        if (epic != null) {
            for (Integer subTaskId : new ArrayList<>(epic.getSubTaskIds())) {
                subTasks.remove(subTaskId);
            }
        }
        epics.remove(id);
    }

    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    //Методы для SubTask
    public List<SubTask> getAllSubTask() {
        return new ArrayList<>(subTasks.values());
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public void createSubTask(SubTask subTask) {
        Epic epic = epics.get(subTask.getEpicId());
        if (epic == null) {
            throw new IllegalArgumentException("Epic with id " + subTask.getEpicId() + " does not exist. Cannot add SubTask.");
        }
        assignId(subTask);
        subTasks.put(subTask.getId(), subTask);
        epic.addSubTaskId(subTask.getId());
        calculateEpicStatus(epic);
    }

    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        calculateEpicStatus(epic);
    }

    public void deleteSubTaskById(int id) {
        for (SubTask subTask : new ArrayList<>(subTasks.values())) {
            Epic epic = epics.get(subTask.getEpicId());
            epic.removeSubTaskId(id);
            calculateEpicStatus(epic);
        }
        subTasks.remove(id);
    }

    public void deleteAllSubTasks() {
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTaskIds().clear();
            calculateEpicStatus(epic);
        }
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
