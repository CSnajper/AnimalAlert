package spring.rest.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.security.AuthoritiesConstants;
import spring.service.files.ProfileImageService;
import spring.service.files.util.FileUtils;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class ProfileImageResource {

    @Autowired
    ProfileImageService fileService;

    @RequestMapping(value="/upload/image_profile", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('" + AuthoritiesConstants.USER + "')")
    public ResponseEntity<?> uploadProfileImage(@RequestParam MultipartFile multipartFile) throws IOException {
        if(multipartFile == null || multipartFile.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        File file = fileService.save(multipartFile);
        if(file != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/user/image_profile", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @PreAuthorize("hasRole('" + AuthoritiesConstants.USER + "')")
    public ResponseEntity<?> getUserProfileImage() throws IOException {
        File file = fileService.load();
        byte[] fileBytes = FileUtils.getFileBytes(file);
        if(fileBytes != null && fileBytes.length > 0) {
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
