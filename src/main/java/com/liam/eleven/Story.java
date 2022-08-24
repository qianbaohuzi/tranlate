package com.liam.eleven;

import lombok.Data;

import java.util.List;

@Data
public class Story implements Translate {

    private String title;

    private List<Paragraph> paragraphs;

    @Override
    public void translate() {
        title = doTranslate(this.title);
        paragraphs.forEach(Paragraph::translate);
    }
}
