package com.example.security.service;

import com.example.security.BaseDo;

public class BookDo extends BaseDo {
    private String id;
    private String reader;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public BookDo getOne(){
        BookDo book = (BookDo)getObject("BookDo.find", this);
        System.out.println(book);
        return book;
    }
}
