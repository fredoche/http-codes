package devoxx.serverless.model.common;

import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Data
public class ValidationErrorResponse {

    public static <T> ValidationErrorResponse fromConstraintViolations(Set<ConstraintViolation<T>> violations) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.setDetails(violations.stream().map(v -> v.getPropertyPath() + " " + v.getMessage()).collect(toList()));
        return error;
    }

    private String message = "Validation error";
    private String code = ValidationErrorResponse.class.getCanonicalName();
    private List<String> details;

}
