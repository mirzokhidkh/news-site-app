package uz.mk.newssiteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.mk.newssiteapp.aop.CheckPermission;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.CommentDto;
import uz.mk.newssiteapp.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse response = commentService.getOneById(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody CommentDto commentDto) {
        ApiResponse response = commentService.add(commentDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }


    //    @PreAuthorize(value = "hasAuthority('EDIT_Comment')")
    @CheckPermission(permission = "EDIT_COMMENT")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id,
                              @Valid @RequestBody CommentDto commentDto) {
        ApiResponse response = commentService.edit(id, commentDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT','DELETE_MY_COMMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = commentService.deleteById(id);
        return ResponseEntity.status(response.isStatus() ? 204 : 409).body(response);
    }

}
