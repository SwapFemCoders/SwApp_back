package com.swapp.swapp.utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.swapp.swapp.exception.FileException;

public class FileUtil {

    public static byte[] convertPicture(MultipartFile file){
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new FileException("Error to process the file: " + file.getOriginalFilename());
        }
    }
}