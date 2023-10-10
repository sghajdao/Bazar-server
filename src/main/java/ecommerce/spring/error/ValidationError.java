package ecommerce.spring.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
    private List<String> errors;
    private String uri;
    @JsonFormat(shape = Shape.STRING, pattern = "dd-mm-yyyy hh:mm:ss")
    private Date timestamp;

    public ValidationError() {
        timestamp = new Date();
        errors = new ArrayList<>();
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}