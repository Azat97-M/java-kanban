package ru.yandex.practicum.manager;

import ru.yandex.practicum.manager.history.*;
import ru.yandex.practicum.manager.task.InMemoryTaskManager;
import ru.yandex.practicum.manager.task.TaskManager;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager(new InMemoryHistoryManager());
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
