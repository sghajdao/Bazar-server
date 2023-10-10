package ecommerce.spring.error;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private String message;
    private String url;
    @JsonFormat(shape = Shape.STRING, pattern = "dd-mm-yyyy hh:mm:ss")
    private Date timestamp;
}