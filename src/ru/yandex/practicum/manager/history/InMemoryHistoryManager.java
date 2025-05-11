package ru.yandex.practicum.manager.history;

import ru.yandex.practicum.model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int MAX_HISTORY_SIZE = 10;
    private final List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        Task copyOfTask = copyTask(task);

        if (history.size() == MAX_HISTORY_SIZE) {
            history.removeFirst();
        }

        history.add(copyOfTask);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }

    private Task copyTask(Task task) {
        Task dublicateTask = new Task(task.getTitle(), task.getDescription(), task.getTaskStatus());
        dublicateTask.setId(task.getId());
        return dublicateTask;
    }
}
