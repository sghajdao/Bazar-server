package ecommerce.spring.image_upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public byte[] getImageByName(String imageName) throws IOException {
        // Logic to fetch the image from your data source
        // You may read the image from a file, a database, or any other source
        // Here, we assume you read the image from a file for simplicity
        Path imagePath = Paths.get(System.getProperty("user.dir") + "/uploads/" + imageName);
        return Files.readAllBytes(imagePath);
    }
}
