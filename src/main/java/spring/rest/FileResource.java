package spring.rest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.service.AccountService;
import spring.service.FileService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class FileResource {

    @Autowired
    FileService fileService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value="/upload/image_profile", method=RequestMethod.POST)
    public ResponseEntity<?> uploadProfileImage(@RequestParam MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        File file = accountService.createUserProfileFile(multipartFile);
        if(file != null)
            return new ResponseEntity<>(file.getName(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/user/image_profile", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getUserProfileImage(/*@PathVariable Long id*/) throws IOException {
        byte[] byteImage = accountService.getUserprofileImage(1L);
        if(byteImage != null && byteImage.length > 0) {
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(byteImage, headers, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
