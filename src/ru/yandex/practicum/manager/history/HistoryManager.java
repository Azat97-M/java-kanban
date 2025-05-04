package ru.yandex.practicum.manager.history;

import ru.yandex.practicum.model.Task;
import java.util.List;

public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
}
