package ru.yandex.practicum.manager.task;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.manager.history.*;
import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    public void testDefaultManagersAreInitialized() {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(taskManager, "TaskManager не должен быть null");
        assertNotNull(historyManager, "HistoryManager не должен быть null");
    }
}