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
        if (task.getTaskId() == 0) {
            task.setTaskId(generateTaskId());
        }
    }

    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }
    public void createTask(Task task) {
        assignId(task);
        tasks.put(task.getTaskId(), task);
    }

    public void updateTasks(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    public void deleteTaskById(int taskId) {
        tasks.remove(taskId);
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    //Методы для Epic
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getEpicById(int taskId) {
        return epics.get(taskId);
    }

    public void createEpic(Epic epic) {
        assignId(epic);
        epics.put(epic.getTaskId(), epic);
    }

    public void updateEpic(Epic epic) {
        calculateEpicStatus(epic);
        epics.put(epic.getTaskId(), epic);
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
        assignId(subTask);
        subTasks.put(subTask.getTaskId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        if (epic != null) {
            epic.addSubTaskId(subTask.getTaskId());
            calculateEpicStatus(epic);
        }
    }

    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.getTaskId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        if (epic != null) {
            calculateEpicStatus(epic);
        }
    }

    public void deleteSubTaskById(int id) {
        for (SubTask subTask : new ArrayList<>(subTasks.values())) {
            Epic epic = epics.get(subTask.getEpicId());
            if (epic != null) {
                epic.removeSubTaskId(id);
                calculateEpicStatus(epic);
            }
        }
        subTasks.remove(id);
    }

    public void deleteAllSubTasks() {
        for (SubTask subTask : new ArrayList<>(subTasks.values())){
            Epic epic = epics.get(subTask.getEpicId());
            if (epic != null) {
                epic.removeSubTaskId(subTask.getTaskId());
                calculateEpicStatus(epic);
            }
        }
        subTasks.clear();
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
