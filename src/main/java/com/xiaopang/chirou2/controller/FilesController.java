package com.xiaopang.chirou2.controller;

import com.xiaopang.chirou2.model.DocumentTree;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {

    @Value("${filePath}")
    private String filePath;

    @GetMapping
    public DocumentTree list() {

        DocumentTree documentTree = new DocumentTree();
        List<DocumentTree.Document> tmsList = new ArrayList<>();
        List<DocumentTree.Document> wmsList = new ArrayList<>();

        documentTree.setTmsList(tmsList);
        documentTree.setWmsList(wmsList);
        try {


            Files.list(Paths.get(filePath + "/tms")).forEach(path -> {
                try {
                    Long now = Files.getLastModifiedTime(path).toMillis();

                    Date date = new Date(now);
                    LocalDateTime localTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

                    String updateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localTime);

                    DocumentTree.Document document = new DocumentTree.Document();
                    document.setUpdateTime(updateTime);
                    document.setName(path.getFileName().toString());
                    tmsList.add(document);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Files.list(Paths.get(filePath + "/wms")).forEach(path -> {
                try {
                    Long now = Files.getLastModifiedTime(path).toMillis();

                    Date date = new Date(now);
                    LocalDateTime localTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

                    String updateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localTime);

                    DocumentTree.Document document = new DocumentTree.Document();
                    document.setUpdateTime(updateTime);
                    document.setName(path.getFileName().toString());
                    wmsList.add(document);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        tmsList.sort(Comparator.comparing(DocumentTree.Document::getUpdateTime).reversed());
        wmsList.sort(Comparator.comparing(DocumentTree.Document::getUpdateTime).reversed());

        return documentTree;
    }

    @GetMapping("/{catalog}")
    public List<DocumentTree.Document> jpList(@PathVariable String catalog) {

        List<DocumentTree.Document> catalogList = new ArrayList<>();

        try {
            Files.list(Paths.get(filePath + "/" + catalog)).forEach(path -> {
                try {
                    Long now = Files.getLastModifiedTime(path).toMillis();

                    Date date = new Date(now);
                    LocalDateTime localTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

                    String updateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localTime);

                    DocumentTree.Document document = new DocumentTree.Document();
                    document.setUpdateTime(updateTime);
                    document.setName(path.getFileName().toString());
                    catalogList.add(document);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        catalogList.sort(Comparator.comparing(DocumentTree.Document::getUpdateTime).reversed());

        return catalogList;
    }

}
