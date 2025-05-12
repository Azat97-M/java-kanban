package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    public void addSubtaskId_doNotAddEpicToSubTask() {
        //Подготавливаем Эпик для теста
        Epic epic = new Epic("Epic Test", "Тест эпика");
        epic.setId(1);

        //Проверяем, что объект Epic нельзя добавить в самого себя в виде подзадачи
        assertThrows(IllegalArgumentException.class, () -> epic.addSubTaskId(epic.getId()),
                "Epic не должен добавлять сам себя в виде подзадачи.");
    }

    @Test
    public void equals_returnTrue_idAreSame() {
        //Подготавливаем экземпляры эпиков
        Epic epic1 = new Epic("Epic1", "Description1");
        Epic epic2 = new Epic("Epic2", "Description2");

        //Устанавливаем id
        epic1.setId(1);
        epic2.setId(1);

        //Проверяем что объекты равны
        assertEquals(epic1, epic2, "Эпики с одинаковым id считаются одинаковыми");
    }
}