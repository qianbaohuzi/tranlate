package com.liam.eleven;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class HttpClient {

    private HttpClient() {
    }

    private static final OkHttpClient client = new OkHttpClient();

    private static final String url = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    private static final String appid = "20220816001308495";
    private static final String key = "PRo6KbiRm9IQ4ENVyJ8O";

    private static final String salt = "32";

    private static String sign(String q) {
        String s = appid + q + salt + key;
        return MD5.create().digestHex(s);
    }

    public static String translate(String s) throws IOException, InterruptedException {
        Thread.sleep(1000);
        Request request = new Request.Builder()
                .url(getUrl(s))
                .get()
                .build();
        Response response = client.newCall(request).execute();
        String s1 = response.body().string();
        s1 = UnicodeUtil.toString(s1);
        Result result = JSON.parseObject(s1, Result.class);
        System.out.println(s1);
        return result.trans_result.get(0).getDst();
    }

    private static String getUrl(String q) {
        return MessageFormat.format("{0}?q={1}&from=en&to=zh&appid={2}&salt={3}&sign={4}", url, q, appid, salt, sign(q));
    }

    @Data
    private static class Result{
        private String from;
        private String to;
        private List<TransResult> trans_result;
    }

    @Data
    private static class TransResult{
        private String src;
        private String dst;
    }
}