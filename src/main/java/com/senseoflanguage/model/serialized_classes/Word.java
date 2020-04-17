package com.senseoflanguage.model.serialized_classes;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Word implements Serializable {

    private String word;
    private String phonetic;
    private Map<String, List<Meaning>> meaning;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public Map<String, List<Meaning>> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<String, List<Meaning>> meaning) {
        this.meaning = meaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word) &&
                Objects.equals(meaning, word1.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, meaning);
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", meaning=" + meaning +
                '}';
    }
}
