package uz.mk.newssiteapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostDto {
    @NotBlank(message = "Title mustn't be empty")
    private String title;

    @NotBlank(message = "Text mustn't be empty")
    private String text;

    @NotBlank(message = "Url mustn't be empty")
    private String url;
}
