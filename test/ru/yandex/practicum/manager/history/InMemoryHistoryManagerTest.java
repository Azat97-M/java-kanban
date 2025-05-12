package ru.yandex.practicum.manager.history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.practicum.model.*;
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
    public void add_copyContainAllFields_addedEpic() {
        // Создаём экземпляр и эпик для теста
        InMemoryHistoryManager manager = new InMemoryHistoryManager();
        Epic epic = new Epic("Эпик", "Описание");
        epic.setId(1);
        epic.getSubTaskIds().add(100);

        // Добавляем в историю
        manager.add(epic);

        // Проверяем все поля
        Epic copy = assertInstanceOf(Epic.class, manager.getHistory().getLast(),
                            "Копия должна быть типа Epic");

        assertEquals(epic.getId(), copy.getId());
        assertEquals(epic.getTitle(), copy.getTitle());
        assertEquals(epic.getDescription(), copy.getDescription());
        assertEquals(epic.getTaskStatus(), copy.getTaskStatus());
        assertEquals(epic.getSubTaskIds(), copy.getSubTaskIds());
    }

    @Test
    public void add_copyContainAllFields_addedSubtask() {
        // Создаем экзмепляр и подзадачу для теста
        InMemoryHistoryManager manager = new InMemoryHistoryManager();
        SubTask subTask = new SubTask("SubTask", "Описание", TaskStatus.IN_PROGRESS, 10);
        subTask.setId(2);

        // Добавляем в историю
        manager.add(subTask);

        // Проверяем поля
        SubTask copy = assertInstanceOf(SubTask.class, manager.getHistory().getLast(),
                                "Копия должна быть типа SubTask");
        assertEquals(subTask.getId(), copy.getId());
        assertEquals(subTask.getTitle(), copy.getTitle());
        assertEquals(subTask.getDescription(), copy.getDescription());
        assertEquals(subTask.getTaskStatus(), copy.getTaskStatus());
        assertEquals(subTask.getEpicId(), copy.getEpicId());
    }

    @Test
    public void add_checkImmutabilityOfFields_changeTask() {
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