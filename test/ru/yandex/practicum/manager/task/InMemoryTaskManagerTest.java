package ru.yandex.practicum.manager.task;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.Epic;
import ru.yandex.practicum.model.SubTask;
import ru.yandex.practicum.model.Task;
import ru.yandex.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    @Test
    public void testAddAndRetrieveTask() {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Task task = new Task("Task1", "Описание 1", TaskStatus.NEW);
        manager.createTask(task);
        Task retrieved = manager.getTaskById(task.getId());
        assertEquals(task, retrieved, "Задачу можно найти по её id");
    }


    @Test
    public void testAddAndRetrieveEpicAndSubTask() {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Epic epic = new Epic("Epic1", "Описание эпика");
        manager.createEpic(epic);
        assertEquals(epic, manager.getEpicById(epic.getId()), "Эпик найден по id");

        SubTask subTask = new SubTask("SubTask1", "Описание подзадачи", TaskStatus.NEW, epic.getId());
        manager.createSubTask(subTask);
        assertEquals(subTask, manager.getSubTaskById(subTask.getId()), "Подзадача найдена по id");
        assertTrue(epic.getSubTaskIds().contains(subTask.getId()),
                "Эпик должен содержать id добавленной подзадачи");
    }

    @Test
    public void testIdGenerationDoesNotConflict() {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Task task1 = new Task("Task1", "Описание1", TaskStatus.NEW);
        // Явно устанавливаем id
        task1.setId(100);
        manager.createTask(task1);
        Task task2 = new Task("Task2", "Описание2", TaskStatus.NEW);
        manager.createTask(task2);
        assertNotEquals(task1.getId(), task2.getId(), "Сгенерированный id не должен конфликтовать с заданным вручную");
    }

    @Test
    public void testTaskImmutabilityAfterAddition() {
        InMemoryTaskManager manager = new InMemoryTaskManager();
        Task task = new Task("ImmutableTask", "OriginalDescription", TaskStatus.NEW);
        manager.createTask(task);
        Task retrieved = manager.getTaskById(task.getId());

        String rep = retrieved.toString();
        assertTrue(rep.contains("ImmutableTask"), "Название задачи должно оставаться неизменным");
        assertTrue(rep.contains("OriginalDescription"), "Описание задачи должно оставаться неизменным");
        assertEquals(TaskStatus.NEW, retrieved.getTaskStatus(), "Статус задачи должен оставаться неизменным");
    }
}