package com.senseoflanguage.model.serialized_classes;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainText implements Serializable {

    private String eng;
    private String ukr;
    private String translit;
    private Map<String, List<Meaning>> meaning;

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
        MainText mainText = (MainText) o;
        return Objects.equals(eng, mainText.eng) &&
                Objects.equals(ukr, mainText.ukr) &&
                Objects.equals(translit, mainText.translit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eng, ukr, translit);
    }

    @Override
    public String toString() {
        return "Word{" +
                "eng='" + eng + '\'' +
                ", ukr='" + ukr + '\'' +
                ", translit='" + translit + '\'' +
                ", meaning=" + meaning +
                "}\n";
    }

}
