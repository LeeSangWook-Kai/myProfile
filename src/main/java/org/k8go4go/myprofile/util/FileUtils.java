package org.k8go4go.myprofile.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.FileSystemException;

@Component
@Getter
@Slf4j
public class FileUtils {
    @Value("${uploadfile.dir}")
    private String uploadRoot;

    @Value("${movingfile.from}")
    private String from;

    @Value("${movedfile.to}")
    private String to;

    public final void createUploadDirectory(File upload, File temp) {
        log.info("createUploadDirectory : " + upload);
        log.info("createUploadDirectory : " + temp);
        if(!upload.exists()) {
            upload.mkdir();
        }

        if(!temp.exists()) {
            temp.mkdir();
        }
    }

    public void moveFileToRealFromTemp(String fname) throws FileSystemException {
        String FROM_LOACTION = uploadRoot + from + fname;
        String TO_LOCATION = uploadRoot + to + fname;

        File fileToMove = new File(FROM_LOACTION);
        boolean isMoved = fileToMove.renameTo(new File(TO_LOCATION));

        if (!isMoved) {
            throw new FileSystemException(TO_LOCATION);
        }
    }

    public String getFileFullPath(String fname) {
        return uploadRoot + to + fname;
    }
}
