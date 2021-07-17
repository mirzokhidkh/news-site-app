package uz.mk.newssiteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.newssiteapp.entity.Comment;
import uz.mk.newssiteapp.entity.Post;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.CommentDto;
import uz.mk.newssiteapp.repository.CommentRepository;
import uz.mk.newssiteapp.repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public ApiResponse getOneById(Long id) {
        if (!commentRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        Comment comment = commentRepository.findById(id).get();
        return new ApiResponse("Comment with " + id, true, comment);
    }

    public ApiResponse add(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).get();

        Comment comment = new Comment(
                commentDto.getText(),
                post
        );
        commentRepository.save(comment);

        return new ApiResponse("Comment saved", true);
    }

    public ApiResponse edit(Long id, CommentDto commentDto) {
        Comment editingComment = commentRepository.findById(id).get();
        editingComment.setText(commentDto.getText());
        Post post = postRepository.findById(commentDto.getPostId()).get();
        editingComment.setPost(post);
        commentRepository.save(editingComment);

        return new ApiResponse("Comment edited", true);
    }

    public ApiResponse deleteById(Long id) {
        if (!commentRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        commentRepository.deleteById(id);
        return new ApiResponse("Comment edited", true);
    }
}
