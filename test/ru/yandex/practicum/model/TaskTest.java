package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TaskTest {

    //Проверяем Task и его наследников(экземпляры равны, если равен их id)
    @Test
    public void tasksWithSameIdAreEqual() {
        Task task1 = new Task("Task 1", "Описание 1", TaskStatus.NEW);
        Task task2 = new Task("Task 2", "Описание 2", TaskStatus.DONE);
        task1.setId(1);
        task2.setId(1);
        assertEquals(task1, task2, "Задачи с одинаковым id равны");
    }

    @Test
    public void epicsWithSameIdAreEqual() {
        Epic epic1 = new Epic("Epic 1", "Описание Epic 1");
        Epic epic2 = new Epic("Epic 2", "Описание Epic 2");
        epic1.setId(2);
        epic2.setId(2);
        assertEquals(epic1, epic2, "Эпики с одинаковым id  равны");
    }

    @Test
    public void subTasksWithSameIdAreEqual() {
        SubTask subTask1 = new SubTask("SubTask 1", "Описание SubTask 1", TaskStatus.NEW, 5);
        SubTask subTask2 = new SubTask("SubTask 1", "Описание SubTask 2", TaskStatus.DONE, 5);
        subTask1.setId(3);
        subTask2.setId(3);
        assertEquals(subTask1, subTask2, "Подзадачи с одинаковым id должны быть равны");
    }
}