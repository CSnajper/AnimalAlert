package spring.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.domain.User;
import spring.repository.UserRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class AccountService {

    @Autowired
    UserRepository userRepository;

    @Value("${files.profileImage.location}")
    private String profileImageLocation;

    //TODO: zmienic sciezke zapisu plikow
    public File createUserProfileFile(MultipartFile multipartFile) {
        if(multipartFile.isEmpty())
            return null;
        File file;
//        User user;
//        if((user = SecurityUtils.getCurrentUserPrincipal()) != null) {
//            file = new File(user.getUsername() + "_" + user.getId());
//            try {
//                multipartFile.transferTo(file);
//                return true;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }

        file = new File("/home/konrad/image_progile");
        try {
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getUserprofileImage(Long id) {
//        User user = userRepository.findOne(id);
//        if(user == null)
//            return null;

        File file = new File("/home/konrad/image_progile");
        byte[] byteImage = convertFileToByteArray(file);
        return byteImage;
    }

    private byte[] convertFileToByteArray(File file) {
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
}
