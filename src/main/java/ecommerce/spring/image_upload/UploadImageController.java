package ecommerce.spring.image_upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ecommerce.spring.dtos.ImageResponseDto;

@RestController
@RequestMapping("/api/image")
public class UploadImageController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/uploadimage")
    public String displayUploadForm() {
        return "imageupload/index";
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageResponseDto> uploadImage(Model model, @RequestParam("image") MultipartFile file)
            throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
        System.out.println(fileNames.toString());
        ImageResponseDto response = ImageResponseDto.builder().name(fileNames.toString()).build();
        return new ResponseEntity<ImageResponseDto>(response, HttpStatus.OK);
    }
}
