package com.nibbledebt.integration.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author a.salachyonok
 */
@JsonRootName("question")
public abstract class Question {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
