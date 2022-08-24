package com.liam.eleven;

public interface Translate {

    void translate();

    default String doTranslate(String s) {
        try {
            return HttpClient.translate(s);
        } catch (Exception e) {
            e.printStackTrace();
            return s;
        }
    }
}