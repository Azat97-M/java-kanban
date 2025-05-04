package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    @Test
    public void testSubTaskCannotBeItsOwnEpic() {

        SubTask subTask = new SubTask("SubTask Test", "Тест подзадачи", TaskStatus.NEW, 200);
        subTask.setId(200);
        assertFalse(subTask.getEpicId() == subTask.getId(),
                "SubTask не может быть своим же эпиком.");
    }
}