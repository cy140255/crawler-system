package com.crawler.Entity;

import java.io.Serializable;

/**
 * Created by 14025 on 2017/9/18.
 */
public class FileBean implements Serializable{
    private static  final long serialVersionUID =  -5452801884470115159L;
    private Integer fileId ;
    private String filePath ;
    private String fileName;


    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
