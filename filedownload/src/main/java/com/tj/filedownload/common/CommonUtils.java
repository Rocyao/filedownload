package com.tj.filedownload.common;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author peng
 * @createDate 2022/7/29 9:50
 */
public class CommonUtils {
    public static void downloadFile(String targetFileName, String srcPath, HttpServletResponse response) throws IOException {
        for(Path path: getPaths(srcPath)){
            File file = path.toFile();
            if(!file.exists()){
                return;
            }
            ZipFile zf = new ZipFile(file);
            Enumeration entries = zf.entries();
            ZipEntry entry;
            while(entries.hasMoreElements()){
                entry = (ZipEntry)entries.nextElement();
                String name = entry.getName();
                if(!name.endsWith(targetFileName)){
                    continue;
                }
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName=" + targetFileName);
                try (InputStream is = zf.getInputStream(entry); OutputStream fos = response.getOutputStream()) {
                    int count;
                    byte[] buffer = new byte[1024 * 8];
                    while(true){
                        count = is.read(buffer);
                        if(count == -1){
                            break;
                        }

                        fos.write(buffer, 0, count);
                    }
                }
            }
        }
    }

    static List<Path> getPaths(String srcPath){
        try(Stream<Path> paths = Files.walk(Paths.get(srcPath))){
            return paths.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
