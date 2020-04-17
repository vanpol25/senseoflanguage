package com.senseoflanguage.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WordRequest implements Serializable {

    @Null(groups = OnCreate.class)
    @NotNull(groups = {OnUpdate.class, OnDelete.class})
    private String id;
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String eng;
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String ukr;
    private String translit;
    private Map<String, List<MeaningRequest>> meaning;

    public WordRequest() {
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

    public Map<String, List<MeaningRequest>> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<String, List<MeaningRequest>> meaning) {
        this.meaning = meaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordRequest that = (WordRequest) o;
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
        return "WordRequest{" +
                "id='" + id + '\'' +
                ", eng='" + eng + '\'' +
                ", ukr='" + ukr + '\'' +
                ", translit='" + translit + '\'' +
                ", meaning=" + meaning +
                '}';
    }

    public interface OnCreate {
    }

    public interface OnUpdate {
    }

    public interface OnDelete {
    }

}
