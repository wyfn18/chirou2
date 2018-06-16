package com.xiaopang.chirou2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {

    @Value("${file-path}")
    private String filePath;

    @GetMapping
    public List<String> list(){

        System.out.println(filePath);

        List<String> fileNameList = new ArrayList<>();
        try {
            Files.list(Paths.get(filePath)).forEach(path -> fileNameList.add(path.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            Files.createDirectories(Paths.get("d:/f"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        for (int i = 0; i < 100; i++) {
//            Path p = Paths.get("d:/f/n" + i + ".txt");
//
//            try {
//                Files.createFile(p);
//                Files.write(p,"test".getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return fileNameList;
    }
}
