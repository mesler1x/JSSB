package edu.jssb.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Message {
    HELLO("""
            Приложение для отслеживания контактов пользователей.
            Список команд можно посмотреть командой: help"""),
    HELP("""
            - Добавить  - add
            - Удалить - delete
            - Просмотреть всех - get-all
            """),
    ACCESS_ADD("Пользователь добавлен"),
    ACCESS_DELETE("Пользователь удален"),
    NULL_CONTACTS("Список пуст"),
    NOT_FOUND_USER("Пользователь не найден");

    private final String text;


}