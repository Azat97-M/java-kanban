package ru.yandex.practicum.manager.task;

import ru.yandex.practicum.manager.history.*;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
