package com.shjz.zp95sky.shjz.server.temp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private ObjectMapper ob = new ObjectMapper();

    @Test
    public void test() throws IOException {
        String str = readFile();
        JsonNode root = ob.readTree(str);

        Map<String, Integer> map = new HashMap<>();

        JsonNode hits = root.get("hits");
        ArrayNode hits2 = (ArrayNode) hits.get("hits");

        String path2 = "E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp6.txt";

        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(path2))) {
            hits2.forEach(h -> {
                JsonNode source = h.get("_source");
                String log = source.get("log").asText();
                String userId = log.substring(17, 30);
                String timestamp = source.get("timestamp").asText();
                String containerId = source.get("container_id").asText();
                String containerName = source.get("container_name").asText();

                map.put(userId, map.getOrDefault(userId, 0) + 1);

                if ("3939910746187".equals(userId)) {
                    try {
                        writer2.write(timestamp + " " + containerId + " " + containerName);
                        writer2.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        String path = "E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp5.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("账号数：" + map.size());
            writer.newLine();
            map.forEach((k, v) -> {
                try {
                    writer.write(k + " : " + v);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }

    private String readFile() {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    @Test
    public void test2() throws IOException {
        String filePath = "E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp2.txt";
        String path = "E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp4.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String line;
            LocalDateTime preTime = null;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            int times = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("\t");
                String userId = arr[1];
                String timestamp = arr[0];

                if (!"3939910746187".equals(userId)) {
                    continue;
                }

                writer.write(timestamp + " " + userId);
                writer.newLine();

                if (preTime == null) {
                    preTime = LocalDateTime.parse(timestamp, formatter);
                    continue;
                }

                LocalDateTime curTime = LocalDateTime.parse(timestamp, formatter);
                Duration duration = Duration.between(curTime, preTime);

                if (duration.getSeconds() > 15) {
                    System.out.println("pre: " + preTime);
                    System.out.println("cur: " + curTime);
                    times++;
                }

                preTime = curTime;
            }

            System.out.println("times: " + times);
        }
    }

    @Test
    public void test3() throws IOException {
        String oldPath = "E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp2.txt";
        String newPath = "E:\\Workspace\\MyProjectWorkspace\\shjz\\shjz-server\\src\\test\\java\\com\\shjz\\zp95sky\\shjz\\server\\temp\\temp3.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(oldPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String timestamp = line.substring(25, 44);
                writer.write(timestamp);
                writer.newLine();
            }
        }
    }

}
