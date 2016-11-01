package spring.service.files.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import spring.domain.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@UtilityClass
public class FileUtils {

    public byte[] getFileBytes(File file) {
        byte[] byteImage = null;
        try {
            FileInputStream in = new FileInputStream(file);
            byteImage = IOUtils.toByteArray(in);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteImage;
    }

    public File convertFromMultipartFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty())
            return null;
        File file = new File(multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }

}
