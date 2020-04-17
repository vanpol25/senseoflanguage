package com.senseoflanguage.model;

import com.senseoflanguage.model.serialized_classes.Meaning;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Document(collection="words")
public class Word {

    @Id
    private String id;
    private String eng;
    private String ukr;
    private String translit;
    private Map<String, List<Meaning>> meaning;

    public Word() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getUkr() {
        return ukr;
    }

    public void setUkr(String ukr) {
        this.ukr = ukr;
    }

    public String getTranslit() {
        return translit;
    }

    public void setTranslit(String translit) {
        this.translit = translit;
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
        Word word = (Word) o;
        return Objects.equals(id, word.id) &&
                Objects.equals(eng, word.eng) &&
                Objects.equals(ukr, word.ukr) &&
                Objects.equals(translit, word.translit) &&
                Objects.equals(meaning, word.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eng, ukr, translit, meaning);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id='" + id + '\'' +
                ", eng='" + eng + '\'' +
                ", ukr='" + ukr + '\'' +
                ", translit='" + translit + '\'' +
                ", meaning=" + meaning +
                '}';
    }

}
