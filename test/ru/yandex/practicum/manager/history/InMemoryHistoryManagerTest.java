package ru.yandex.practicum.manager.history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.practicum.model.Task;
import ru.yandex.practicum.model.TaskStatus;
import java.util.List;

class InMemoryHistoryManagerTest {

    @Test
    public void add_deleteFirst_addedMoreThanTen() {
        // Создаем экземпляр для теста
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        // Добавляем задачи в список просмотров
        for (int i = 1; i <= 11; i++) {
            Task task = new Task("Task" + i, "Desc" + i, TaskStatus.NEW);
            task.setId(i);
            historyManager.add(task);
        }

        // проверяем, что в истории осталось ровно 10 задач
        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "История должна содержать 10 задач");
    }

    @Test
    public void add_checkStatus_changeTask() {
        // Создаем экземпляр для теста
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        // Добавляем задачу с определленым статусом и меняем статус
        Task task = new Task("Task", "Description", TaskStatus.NEW);
        task.setId(1);
        historyManager.add(task);

        task.setTaskStatus(TaskStatus.DONE);

        //Проверяем, что задача в истории осталась  с прежним статусом
        List<Task> history = historyManager.getHistory();
        assertEquals(TaskStatus.NEW, history.getFirst().getTaskStatus(),
                "История должна сохранять прежнее значение статуса задачи");
    }
}