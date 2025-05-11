package ru.yandex.practicum;

import ru.yandex.practicum.manager.history.InMemoryHistoryManager;
import ru.yandex.practicum.manager.task.InMemoryTaskManager;
import ru.yandex.practicum.manager.task.TaskManager;
import ru.yandex.practicum.model.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager(new InMemoryHistoryManager());

        // Создаем задачи
        Task task1 = new Task("Учеба в Яндекс Практикуме",
                             "Прохождения курса по Java",
                             TaskStatus.NEW);
        manager.createTask(task1);

        Task task2 = new Task("Полет на Бали",
                            "Планирование отпуска и бронирование билетов",
                            TaskStatus.NEW);
        manager.createTask(task2);

        //Создаем Epic с 2 поздзадачами
        Epic epic1 = new Epic("Подготовка к сдаче проекта",
                         "Подготовка к финальному заданию" );
        manager.createEpic(epic1);

        SubTask subTask1 = new SubTask("Почитать теорию",
                                        "Изучить конспекты",
                                        TaskStatus.NEW,
                                        epic1.getId());
        manager.createSubTask(subTask1);

        SubTask subTask2 = new SubTask("Решить задачи",
                                    "Практиковаться в тренажере",
                                    TaskStatus.NEW,
                                    epic1.getId());
        manager.createSubTask(subTask2);

        //Создаем Epic с одной подзадачей
        Epic epic2 = new Epic("Организация Party for everybody",
                            "Подготовка ко дню рождения" );
        manager.createEpic(epic2);

        SubTask subTask3 = new SubTask("Купить напитки",
                                        "Выбрать и заказать шампанское",
                                        TaskStatus.NEW,
                                        epic2.getId());
        manager.createSubTask(subTask3);

        //Печатаем списки все задач, эпиков и подзадач
        System.out.println("Список задач:");
        System.out.println(manager.getAllTasks());

        System.out.println("Список эпиков:");
        System.out.println(manager.getAllEpics());

        System.out.println("Список подзадач:");
        System.out.println(manager.getAllSubTask());

        //Изменяем статусы объектов
        //В epic1 обе подзадачи со статусом DONE значит epic1 - DONE
        task1.setTaskStatus(TaskStatus.DONE);
        manager.updateTasks(task1);

        subTask1.setTaskStatus(TaskStatus.DONE);
        manager.updateSubTask(subTask1);

        subTask2.setTaskStatus(TaskStatus.DONE);
        manager.updateSubTask(subTask2);

        //Проверяем обновленные объекты
        System.out.println("После обновления статусов:");
        System.out.println("Задача 1: " + manager.getTaskById(task1.getId()));
        System.out.println("Задача 2: " + manager.getTaskById(task2.getId()));
        System.out.println("Эпик 1: " + manager.getEpicById(epic1.getId()));
        System.out.println("Эпик 2: " + manager.getEpicById(epic2.getId()));
        System.out.println("Подзадачи:");
        System.out.println(manager.getAllSubTask());

        //Удаляем одну задачу
        manager.deleteTaskById(task2.getId());
        manager.deleteEpicById(epic2.getId());

        System.out.println("После удаления задач:");
        System.out.println("Список задач:");
        System.out.println(manager.getAllTasks());
        System.out.println("Список эпиков:");
        System.out.println(manager.getAllEpics());
        System.out.println("Список подзадач");
        System.out.println(manager.getAllSubTask());

    }
}