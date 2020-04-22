package com.senseoflanguage.transfer;

import com.senseoflanguage.model.Definition;
import com.senseoflanguage.model.Word;
import com.senseoflanguage.model.my_db.MainText;
import com.senseoflanguage.model.my_db.Meaning;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class WordTransfer {

    public Word addMyDB(MainText mainText) {
        if (mainText == null) {
            return null;
        }

        return new Word() {{
            setEng(mainText.getEng());
            setUkr(mainText.getUkr());
            setTranslit(mainText.getTranslit());

            Set<Map.Entry<String, List<Meaning>>> entries = mainText.getMeaning().entrySet();
            for (Map.Entry<String, List<Meaning>> entry : entries) {
                for (Meaning meaning : entry.getValue()) {
                    getResults().add(new Definition() {{
                        setDefinition(entry.getKey());
                        getExamples().add(meaning.getExample());
                        getSynonyms().addAll(meaning.getSynonyms());
                    }});
                }
            }
        }};
    }

}
