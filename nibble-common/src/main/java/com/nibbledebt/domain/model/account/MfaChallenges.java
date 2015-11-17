package com.nibbledebt.domain.model.account;

/**
 * @author a.salachyonok
 */
abstract public class MfaChallenges<T extends Question> {

    private T[] question;

    public T[] getQuestion() {
        return question;
    }

    public void setQuestion(T[] question) {
        this.question = question;
    }
}
