package ru.yandex.practicum.manager.history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.practicum.model.Task;
import ru.yandex.practicum.model.TaskStatus;
import java.util.List;

class InMemoryHistoryManagerTest {

    @Test
    public void testHistorySizeLimit() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        // Добавляем 12 задач, чтобы проверить ограничение истории
        for (int i = 1; i <= 12; i++) {
            Task task = new Task("Task" + i, "Desc" + i, TaskStatus.NEW);
            task.setId(i);
            historyManager.add(task);
        }
        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "История должна содержать не более 10 задач");
        // При добавлении 12 задач, первые 2 должны быть удалены.
        assertEquals(3, history.get(0).getId().intValue(), "Самая старая задача должна иметь id 3");
    }

    @Test
    public void testHistoryRetainsTaskSnapshot() {
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        Task task = new Task("SnapshotTask", "InitialDesc", TaskStatus.NEW);
        task.setId(1);
        historyManager.add(task);

        task.setTaskStatus(TaskStatus.DONE);

        List<Task> history = historyManager.getHistory();
        assertEquals(TaskStatus.NEW, history.get(0).getTaskStatus(),
                "История должна сохранять состояние задачи на момент добавления");
    }
}