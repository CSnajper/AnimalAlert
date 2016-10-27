package spring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("/api")
public class FileResource {

    @RequestMapping(value="/upload/image_profile", method=RequestMethod.POST)
    public ResponseEntity<?> uploadProfileImage(@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(Base64.getEncoder().encode(file.getBytes()), HttpStatus.OK);
    }
}
