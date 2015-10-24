package com.nibbledebt.domain.model.account;

/**
 * @author a.salachyonok
 */
public abstract class Question {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
