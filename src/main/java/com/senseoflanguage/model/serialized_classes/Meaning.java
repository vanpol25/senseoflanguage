package com.senseoflanguage.model.serialized_classes;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Meaning implements Serializable {

    private String definition;
    private String example;
    private List<String> synonyms;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meaning that = (Meaning) o;
        return Objects.equals(definition, that.definition) &&
                Objects.equals(example, that.example);
    }

    @Override
    public int hashCode() {
        return Objects.hash(definition, example);
    }

    @Override
    public String toString() {
        return "Meaning{" +
                "definition='" + definition + '\'' +
                ", example='" + example + '\'' +
                ", synonyms=" + synonyms +
                '}';
    }
}
