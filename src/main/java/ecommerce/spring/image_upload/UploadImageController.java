package ecommerce.spring.image_upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ecommerce.spring.dtos.ImageResponseDto;

@RestController
@RequestMapping("/api/image")
public class UploadImageController {

    @Autowired
    private ImageService imageService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> displayUploadForm(@PathVariable String imageName) {
        System.out.println(imageName);
        try {
            byte[] imageBytes = imageService.getImageByName(imageName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image type

            return new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<List<ImageResponseDto>> uploadImage(Model model, @RequestParam("image") MultipartFile[] files)
            throws IOException {
        List<ImageResponseDto> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);

            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, originalFilename);
            fileNames.append(originalFilename);
            Files.write(fileNameAndPath, file.getBytes());

            model.addAttribute("msg", "Uploaded image: " + fileNames.toString());
            ImageResponseDto response = ImageResponseDto.builder().name(fileNames.toString()).build();
            responses.add(response);
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
