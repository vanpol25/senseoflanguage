package com.senseoflanguage.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WordResponse implements Serializable {

    private String id;
    private String eng;
    private String ukr;
    private String translit;
    private Map<String, List<MeaningResponse>> meaning;

    public WordResponse() {
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

    public Map<String, List<MeaningResponse>> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<String, List<MeaningResponse>> meaning) {
        this.meaning = meaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordResponse that = (WordResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(eng, that.eng) &&
                Objects.equals(ukr, that.ukr) &&
                Objects.equals(translit, that.translit) &&
                Objects.equals(meaning, that.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eng, ukr, translit, meaning);
    }

    @Override
    public String toString() {
        return "WordResponse{" +
                "id='" + id + '\'' +
                ", eng='" + eng + '\'' +
                ", ukr='" + ukr + '\'' +
                ", translit='" + translit + '\'' +
                ", meaning=" + meaning +
                '}';
    }

}
