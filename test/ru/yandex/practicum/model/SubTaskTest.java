package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    @Test
    public void setId_throwIllegalArgumentException_idSameWithEpicId() {
        // создаём подзадачу с epicId = 10
        SubTask subTask = new SubTask("Подзадача", "Описание", TaskStatus.NEW, 10);

        // Проверяем, что подзадача не может быть свои эпиком
         assertThrows(IllegalArgumentException.class, () -> subTask.setId(10),
                "SubTask не может иметь id, равное epicId");
    }

    @Test
    public void equals_returnTrue_idAreSame() {
        //Подготавливаем экземпляры подзадч
        SubTask subTask1 = new SubTask("SubTask 1", "Описание SubTask 1", TaskStatus.NEW, 5);
        SubTask subTask2 = new SubTask("SubTask 1", "Описание SubTask 2", TaskStatus.DONE, 5);

        //Устанавливаем id
        subTask1.setId(3);
        subTask2.setId(3);

        //Проверяем что объекты равны
        assertEquals(subTask1, subTask2, "Подзадачи с одинаковым id должны быть равны");
    }
}