package com.liam.eleven;

import lombok.Data;

import java.util.List;


@Data
public class Root {

    private String version;

    private List<Story> data;

}