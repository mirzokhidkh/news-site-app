package uz.mk.newssiteapp.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDto {
    @NotBlank(message = "text mustn't be empty")
    private String text;

    @NotBlank(message = "Post ID mustn't be empty")
    private Long postId;
}
