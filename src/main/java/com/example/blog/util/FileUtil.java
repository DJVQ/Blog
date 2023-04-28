package com.example.blog.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtil {
    public static String readByLines(String path) {
        String content = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp+"\n");
            }
            content = sb.toString();
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
