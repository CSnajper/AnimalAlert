package spring.service.files.enums;

import lombok.Getter;

@Getter
public enum FileType {
    IMAGE_PROFILE("C:\\images\\"),
    IMAGE_ORGANISATION("C:\\images\\"),
    IMAGE_PET("C:\\images\\");

    private String location;

    FileType(String location) {
        this.location = location;
    }
}
