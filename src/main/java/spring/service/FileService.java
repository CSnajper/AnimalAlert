package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {
    public File convertFromMultipartFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty())
            return null;
        File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }
}
