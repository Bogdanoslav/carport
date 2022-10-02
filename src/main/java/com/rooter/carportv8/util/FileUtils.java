package com.rooter.carportv8.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileUtils {

    @Autowired
    private static final ResourceLoader resourceLoader = new DefaultResourceLoader();


    public static String getFileContent(String path)  {
        Resource resource = resourceLoader.getResource(path);
        return asString(resource);
    }

    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            return null;
        }
    }
}
