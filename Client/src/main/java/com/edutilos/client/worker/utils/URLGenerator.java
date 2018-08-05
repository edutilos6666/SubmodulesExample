package com.edutilos.client.worker.utils;

import java.util.List;

/**
 * Created by edutilos on 05.08.18.
 */
public class URLGenerator {
    public static String generateURL(String base, List<String> params) {
        StringBuilder sb = new StringBuilder(base);
        for(String param: params) {
            sb.append("/").append(param);
        }
        return sb.toString();
    }
}
