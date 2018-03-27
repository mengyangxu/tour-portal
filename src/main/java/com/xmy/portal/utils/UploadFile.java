package com.xmy.portal.utils;

import java.io.Serializable;

public class UploadFile implements Serializable {

    private static final long serialVersionUID = -5077361410541860143L;
    private String id;
    private String fileName;
    private String fileType;
    private String fileSize;
    private String filePath;

    public UploadFile() {

    }

    public UploadFile(String id, String fileName, String fileType,
                      String fileSize, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "UploadFile [id=" + id + ", fileName=" + fileName
                + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", filePath=" + filePath + "]";
    }
}