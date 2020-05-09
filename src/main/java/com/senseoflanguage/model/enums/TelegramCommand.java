package com.senseoflanguage.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum TelegramCommand {

    SHOW("Show", "/show"),
    IS_NO_LAST("Next word ->", "/next"),
    IS_LAST("Congratulations!", "/last"),
    EASY("Easy", "/easy"),
    MEDIUM("Medium", "/medium"),
    HARD("Hard", "/hard"),
    EXAMPLES("Examples", "examples"),
    DETAILS("Details", "/details"),
    ALL_INFO("All info", "/all"),
    MENU("Menu", "/menu"),
    STATISTIC("Statistic", "/statistic");

    private final static Map<String, TelegramCommand> byText = new HashMap<>(TelegramCommand.values().length, 1);
    private final static Map<String, TelegramCommand> byCommand = new HashMap<>(TelegramCommand.values().length, 1);

    private final String text;
    private final String command;

    static {
        for (TelegramCommand telegramCommand : TelegramCommand.values()) {
            byText.put(telegramCommand.text, telegramCommand);
            byCommand.put(telegramCommand.command, telegramCommand);
        }
    }

    TelegramCommand(String text, String command) {
        this.text = text;
        this.command = command;
    }

    public String text() {
        return text;
    }

    public String command() {
        return command;
    }

    public static boolean hasText(String text) {
        return byText.containsKey(text);
    }

    public static boolean hasCommand(String command) {
        return byCommand.containsKey(command);
    }

    public static TelegramCommand getByText(String text) {
        TelegramCommand telegramCommand = byText.get(text);
        if (telegramCommand == null) {
            throw new IllegalArgumentException("Invalid text: " + text);
        }
        return telegramCommand;
    }

    public static TelegramCommand getByCommand(String command) {
        TelegramCommand telegramCommand = byCommand.get(command);
        if (telegramCommand == null) {
            throw new IllegalArgumentException("Invalid text: " + command);
        }
        return telegramCommand;
    }

}
