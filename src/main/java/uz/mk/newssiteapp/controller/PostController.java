package uz.mk.newssiteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.mk.newssiteapp.aop.CheckPermission;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.PostDto;
import uz.mk.newssiteapp.service.PostService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse response = postService.getOneById(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody PostDto postDto) {
        ApiResponse response = postService.add(postDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }


    //    @PreAuthorize(value = "hasAuthority('EDIT_Post')")
    @CheckPermission(permission = "EDIT_POST")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id,
                              @Valid @RequestBody PostDto postDto) {
        ApiResponse response = postService.edit(id, postDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = postService.deleteById(id);
        return ResponseEntity.status(response.isStatus() ? 204 : 409).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
