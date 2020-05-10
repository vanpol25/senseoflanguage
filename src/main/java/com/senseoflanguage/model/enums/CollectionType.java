package com.senseoflanguage.model.enums;

public enum CollectionType {

    BASE1("Basic part 1"),
    BASE2("Basic part 2"),
    BASE3("Basic part 3"),
    BASE4("Basic part 4"),
    BASE5("Basic part 5"),
    BASE6("Basic part 6");

    private final String text;

    CollectionType(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

}
