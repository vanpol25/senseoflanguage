package com.senseoflanguage.dto.request;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MeaningRequest implements Serializable {

    private String definition;
    private String example;
    private List<String> synonyms;

    public MeaningRequest() {
    }

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
        MeaningRequest that = (MeaningRequest) o;
        return Objects.equals(definition, that.definition) &&
                Objects.equals(example, that.example) &&
                Objects.equals(synonyms, that.synonyms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(definition, example, synonyms);
    }

    @Override
    public String toString() {
        return "MeaningRequest{" +
                "definition='" + definition + '\'' +
                ", example='" + example + '\'' +
                ", synonyms=" + synonyms +
                '}';
    }

}
