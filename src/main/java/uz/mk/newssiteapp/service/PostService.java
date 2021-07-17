package uz.mk.newssiteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.newssiteapp.entity.Post;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.PostDto;
import uz.mk.newssiteapp.repository.PostRepository;

@Service
public class PostService {


    @Autowired
    PostRepository postRepository;

    public ApiResponse getOneById(Long id) {
        if (!postRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        Post post = postRepository.findById(id).get();
        return new ApiResponse("Post with " + id, true, post);
    }

    public ApiResponse add(PostDto postDto) {
        Post post = new Post(
                postDto.getTitle(),
                postDto.getText(),
                postDto.getUrl()
        );
        postRepository.save(post);

        return new ApiResponse("Post saved", true);
    }

    public ApiResponse edit(Long id, PostDto PostDto) {
        Post editingPost = postRepository.findById(id).get();
        editingPost.setTitle(PostDto.getTitle());
        editingPost.setText(PostDto.getText());
        editingPost.setUrl(PostDto.getUrl());
        postRepository.save(editingPost);

        return new ApiResponse("Post edited", true);
    }

    public ApiResponse deleteById(Long id) {
        if (!postRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        postRepository.deleteById(id);
        return new ApiResponse("Post edited", true);
    }
}
