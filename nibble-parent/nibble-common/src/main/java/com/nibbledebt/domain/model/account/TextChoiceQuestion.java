package com.nibbledebt.domain.model.account;

/**
 * @author a.salachyonok
 */
public class TextChoiceQuestion extends Question {

    private TextChoice[] choice;

    public TextChoice[] getChoice() {
        return choice;
    }

    public void setChoice(TextChoice[] choice) {
        this.choice = choice;
    }
}
