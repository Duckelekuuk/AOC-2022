package com.duckelekuuk.day7;

import lombok.Getter;

import java.util.*;

@Getter
public class Directory {

    private Directory parent;
    private Map<String, Directory> directories = new HashMap<>();
    private Map<String, Integer> files = new HashMap<>();

    public Directory() {
        this.directories = new HashMap<>();
        this.files = new HashMap<>();
    }

    public Directory(Directory parent) {
        this.parent = parent;
    }

    public void addDirectory(String name) {
        directories.put(name, new Directory(this));
    }

    public void addFile(String name, int size) {
        files.put(name, size);
    }

    public Set<Directory> getAllDirectories() {
        Set<Directory> tempDirectory = new HashSet<>(this.directories.values());
        for (Directory value : this.directories.values()) {
            tempDirectory.addAll(value.getAllDirectories());
        }
        return tempDirectory;
    }

    public int getTotalFileSize() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : files.entrySet()) {
            sum += entry.getValue();
        }
        for (Map.Entry<String, Directory> entry : directories.entrySet()) {
            sum += entry.getValue().getTotalFileSize();
        }
        return sum;
    }
}
