package uz.mk.newssiteapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "full name mustn't be empty")
    private String fullName;

    @NotBlank(message = "username mustn't be empty")
    private String username;

    @NotBlank(message = "password mustn't be empty")
    private String password;

    @NotBlank(message = "password mustn't be empty")
    private String prePassword;


}
