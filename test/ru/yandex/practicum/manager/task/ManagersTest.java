package ru.yandex.practicum.manager.task;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.manager.Managers;
import ru.yandex.practicum.manager.history.*;
import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    public void getDefault_returnInitializedInstance() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "TaskManager не должен быть null");
    }

    @Test
    public void getDefaultHistory_returnInitializedInstance() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "HistoryManager не должен быть null");
    }
}