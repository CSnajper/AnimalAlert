package spring.service.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.domain.User;
import spring.repository.UserRepository;
import spring.security.util.SecurityUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProfileImageService implements IFileService{
    private static final Logger log = LoggerFactory.getLogger(ProfileImageService.class);

    @Autowired
    UserRepository userRepository;

    @Value("${files.location.IMAGE_PROFILE}")
    private String location;

    @Override
    public File save(MultipartFile multipartFile) {
        User user = SecurityUtils.getCurrentUserPrincipal();

        File file = saveImage(user, multipartFile);

        return file;
    }

    @Override
    public File save(MultipartFile multipartFile, Long id) {
        User user = userRepository.findOne(id);
        if(user == null)
            return null;

        File file = saveImage(user, multipartFile);
        return file;
    }

    @Override
    public File saveFiles(MultipartFile[] multipartFiles) {
        throw new UnsupportedOperationException("Save multiple files is not allowed for user profile image");
    }

    @Override
    public File load() {
        User user = SecurityUtils.getCurrentUserPrincipal();

        File file = new File(location, generateUserProfileImageName(user));
        if(file.exists() && file.isFile())
            return file;
        else return getUserDefaultProfileImage();
    }

    @Override
    public File load(Long id) {
        User user = userRepository.findOne(id);
        if(user == null)
            return null;

        File file = new File(location, generateUserProfileImageName(user));
        if(file.exists() && file.isFile())
            return file;
        else return getUserDefaultProfileImage();
    }

    @Override
    public List<File> loadFiles(Long id) {
        throw new UnsupportedOperationException("Load multiple files is not allowed for user profile image");
    }

    private File saveImage(User user, MultipartFile multipartFile) {
        File file = new File(location, generateUserProfileImageName(user));
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.warn("Error saving user profile image");
            e.printStackTrace();
            file = null;
        }

        return file;
    }

    private File getUserDefaultProfileImage() {
        File file = new File(location, "default_image.bmp");
        return file;
    }

    public static String generateUserProfileImageName(User user) {
        return user.getUsername()+"_"+user.getId();
    }
}
