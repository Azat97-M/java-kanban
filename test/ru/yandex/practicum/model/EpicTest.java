package ru.yandex.practicum.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    public void testEpicCannotAddItselfAsSubtask() {
        Epic epic = new Epic("Epic Test", "Тест эпика");
        epic.setId(100);

        boolean exceptionThrown = false;

        try {
            epic.addSubTaskId(100);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue(exceptionThrown, "Epic не должен добавлять сам себя в качестве подзадачи.");
    }
}