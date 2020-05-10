package com.senseoflanguage.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum TelegramCommand {

    START("Start", "/start"),
    SHOW_ANSWER("Show", "/show"),
    EASY("Easy", "/easy"),
    MEDIUM("Medium", "/medium"),
    HARD("Hard", "/hard"),
    EXAMPLES("Examples", "examples"),
    DETAILS("Details", "/details"),
    ALL_INFO("All info", "/all"),
    CHOOSE_COLLECTION("Choose collection", "/choose"),
    STATISTIC("Statistic", "/statistic"),
    BASE1("Basic part 1", "/collection_base1"),
    BASE2("Basic part 2", "/collection_base2"),
    BASE3("Basic part 3", "/collection_base3"),
    BASE4("Basic part 4", "/collection_base4"),
    BASE5("Basic part 5", "/collection_base5"),
    BASE6("Basic part 6", "/collection_base6");

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

    public static TelegramCommand getCommand(String command) {
        TelegramCommand telegramCommand;
        if ((telegramCommand = byText.get(command)) != null) {
            return telegramCommand;
        } else if ((telegramCommand = byCommand.get(command)) != null) {
            return telegramCommand;
        } else {
            throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

}
