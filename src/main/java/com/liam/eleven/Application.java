package com.liam.eleven;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Application {

    //todo you file path
    private static final String distPath = "C:\\Users\\liam\\Desktop\\train\\";

    public static void main(String[] args) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource("train-v2.0.json");
        if (url == null) {
            return;
        }
        File file = new File(url.getFile());
        String s = IOUtils.toString(new InputStreamReader(new FileInputStream(file)));
        Root root = JSON.parseObject(s, Root.class);
        List<Story> data = root.getData();
        for (int i = 0; i < data.size(); i++) {
            Story story = data.get(i);
            story.translate();
            byte[] bytes = JSON.toJSONString(story).getBytes();
            File distFile = new File(distPath + i + ".json");
            FileOutputStream outputStream = new FileOutputStream(distFile);
            IOUtils.copy(new ByteArrayInputStream(bytes), outputStream);
            outputStream.flush();
            outputStream.close();
            System.out.println(story.getTitle() + "success");
        }
    }
}