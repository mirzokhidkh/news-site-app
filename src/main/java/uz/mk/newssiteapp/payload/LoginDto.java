package uz.mk.newssiteapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "username mustn't be empty")
    private String username;

    @NotBlank(message = "password mustn't be empty")
    private String password;
}
