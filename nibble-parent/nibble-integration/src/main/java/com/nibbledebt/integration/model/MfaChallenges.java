package com.nibbledebt.integration.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author a.salachyonok
 */
@JsonRootName("mfaChallenges")
abstract public class MfaChallenges<T extends Question> {

    @JacksonXmlProperty(localName = "question")
    @JacksonXmlElementWrapper(useWrapping = true, localName = "questions")
    private T[] question;

    public T[] getQuestion() {
        return question;
    }

    public void setQuestion(T[] question) {
        this.question = question;
    }
}
