package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.manager.history.InMemoryHistoryManager;
import ru.yandex.practicum.manager.task.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    @Test
    public void createSubTask_subTaskCannotBeItsOwnEpic() {
        //Подготавливаем экземпляры для теста
        InMemoryTaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());
        Epic epic = new Epic("Эпик", "Описание");

        //Создаем эпик и подзадачу с одинаковым id
        manager.createEpic(epic);
        SubTask subTask = new SubTask("Подзадача", "Описание", TaskStatus.NEW, epic.getId());
        subTask.setId(epic.getId());

        //Проверяем, что подзадача не может быть свои эпиком
        assertThrows(IllegalArgumentException.class, () -> manager.createSubTask(subTask),
                "SubTask не может быть своим же эпиком.");
    }

    @Test
    public void subTask_equal_whenIdsAreEqual() {
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