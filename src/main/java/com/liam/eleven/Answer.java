package com.liam.eleven;

import lombok.Data;

@Data
public class Answer implements Translate {

    private String text;

    private Integer answer_start;

    @Override
    public void translate() {
        text = doTranslate(this.text);
    }
}
