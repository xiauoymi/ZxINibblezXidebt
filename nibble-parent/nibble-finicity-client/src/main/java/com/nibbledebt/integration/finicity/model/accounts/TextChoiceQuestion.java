package com.nibbledebt.integration.finicity.model.accounts;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author a.salachyonok
 */
public class TextChoiceQuestion extends Question {

    @JacksonXmlProperty(localName = "choice")
    @JacksonXmlElementWrapper(useWrapping = false)
    private TextChoice[] choice;

    public TextChoice[] getChoice() {
        return choice;
    }

    public void setChoice(TextChoice[] choice) {
        this.choice = choice;
    }
}
