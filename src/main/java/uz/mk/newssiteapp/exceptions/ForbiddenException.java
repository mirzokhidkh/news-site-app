package uz.mk.newssiteapp.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException{
    private String type;
    private String message;

    public ForbiddenException(String type, String message) {
        this.type = type;
        this.message = message;
    }
}
