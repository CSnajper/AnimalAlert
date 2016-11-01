package spring.service.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IFileService {
    File save(MultipartFile multipartFile);
    File save(MultipartFile multipartFile, Long id);
    File saveFiles(MultipartFile[] multipartFiles);
    File load(Long id);
    File load();
    List<File> loadFiles(Long id);
}
