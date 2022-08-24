package com.liam.eleven;

import lombok.Data;

import java.util.List;

@Data
public class QA implements Translate {

    private String question;

    private String id;

    private boolean is_impossible;

    private List<Answer> answers;


    @Override
    public void translate() {
        question = doTranslate(question);
        answers.forEach(Answer::translate);
    }
}
