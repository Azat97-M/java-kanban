package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TaskTest {

    @Test
    public void task_equal_whenIdsAreEqual() {
        //Подготавливаем экземпляры для теста
        Task task1 = new Task("Task 1", "Описание 1", TaskStatus.NEW);
        Task task2 = new Task("Task 2", "Описание 2", TaskStatus.DONE);

        //Устанавливаем id
        task1.setId(1);
        task2.setId(1);

        //Проверяем, что объекты равны
        assertEquals(task1, task2, "Задачи с одинаковым id равны");
    }
}