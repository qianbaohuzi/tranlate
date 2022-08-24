package com.liam.eleven;

import lombok.Data;

import java.util.List;

@Data
public class Paragraph implements Translate {

    private String context;

    private List<QA> qas;

    @Override
    public void translate() {
        context = doTranslate(this.context);
        qas.forEach(QA::translate);
    }
}
