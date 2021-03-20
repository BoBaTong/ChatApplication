package com.student.file;

public abstract class File {
    private int id=0;
    private String fileName;
    private String filePath;
    private FileType fileType;
    private static int count = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public File(int id, String fileName, String filePath) {
        id = count++;
        this.fileName = fileName;
        this.filePath = filePath;
        String [] path = filePath.split(".");
        if(path[path.length-1].equals("mp4") || path[path.length-1].equals("avi"))
        {
            this.fileType = FileType.Video;
        }
        else if(path[path.length-1].equals("mp3"))
        {
            this.fileType = FileType.Audio;
        }
        else if(path[path.length-1].equals("png")||path[path.length-1].equals("jpg"))
        {
            this.fileType = FileType.Image;
        }
    }
}
