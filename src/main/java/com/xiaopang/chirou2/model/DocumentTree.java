package com.xiaopang.chirou2.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DocumentTree implements Serializable{

    private List<Document> tmsList;

    private List<Document> wmsList;

    @Data
    public static class Document implements Serializable{

        private String name;

        private String updateTime;

    }
}
