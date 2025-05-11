package ru.yandex.practicum.manager.task;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.manager.history.InMemoryHistoryManager;
import ru.yandex.practicum.model.Epic;
import ru.yandex.practicum.model.SubTask;
import ru.yandex.practicum.model.Task;
import ru.yandex.practicum.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    @Test
    public void createTask_addNewTaskAndGetTaskById() {
        //Создаем экземпляр для теста
        InMemoryTaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());

        // Создаем задачу
        Task task = new Task("Task1", "Описание 1", TaskStatus.NEW);
        manager.createTask(task);

        // Получаем ее по id
        Task foundTask = manager.getTaskById(task.getId());
        assertEquals(task, foundTask, "Задачу можно найти по её id");
    }


    @Test
    public void createEpic_addNewEpicAndGetEpicById() {
        //Создаем экземпляр для теста
        InMemoryTaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());

        //Создаем Эпик
        Epic epic = new Epic("Epic1", "Описание эпика");
        manager.createEpic(epic);

        //Проверяем найден ли Эпик
        assertEquals(epic, manager.getEpicById(epic.getId()), "Эпик найден по id");
    }
    @Test
    public void createSubTask_addNewSubTaskAndGetSubtaskById() {
        //Создаем экземпляр для теста и эпик для подзадачи
        InMemoryTaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());
        Epic epic = new Epic("Epic1", "Описание эпика");
        manager.createEpic(epic);

        //Создаем подзадачу
        SubTask subTask = new SubTask("SubTask1", "Описание подзадачи", TaskStatus.NEW, epic.getId());
        manager.createSubTask(subTask);

        //Проверяем найдена ли подзадача
        assertEquals(subTask, manager.getSubTaskById(subTask.getId()), "Подзадача найдена по id");
   }

    @Test
    public void createTask_checkForIdConflict() {
        //Создаем экземпляр и подготавливаем задачи для теста
        InMemoryTaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());
        Task task1 = new Task("Task1", "Описание1", TaskStatus.NEW);
        Task task2 = new Task("Task2", "Описание2", TaskStatus.NEW);

        // Явно устанавливаем id и создаем задачи
        task2.setId(1);
        manager.createTask(task1);
        manager.createTask(task2);

        //Проверяем id на конфликтность
        assertNotEquals(task1.getId(), task2.getId(), "Сгенерированный id и id заданный явно должны отличаться");
    }

    @Test
    public void  createTask_checkTaskImmutability() {
        //Создаем экземпляр и подготавливаем задачу для теста
        InMemoryTaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());
        Task task = new Task("Задача1", "Описание", TaskStatus.NEW);

        //Создаем задачу и находим по id
        manager.createTask(task);
        Task foundTask = manager.getTaskById(task.getId());

        //Проверям
        assertEquals(task.getTitle(),foundTask.getTitle(), "Название задачи должно оставаться неизменным");
        assertEquals(task.getDescription(),foundTask.getDescription(), "Описание задачи должно оставаться неизменным");
        assertEquals(task.getTaskStatus(), foundTask.getTaskStatus(), "Статус задачи должен оставаться неизменным");
    }
}