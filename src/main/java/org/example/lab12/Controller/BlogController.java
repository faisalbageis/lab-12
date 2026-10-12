package org.example.lab12.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab12.API.ApiResponse;
import org.example.lab12.Model.Blog;
import org.example.lab12.Model.User;
import org.example.lab12.Service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllBlog(){
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }

    @GetMapping("/get/my/blog")
    public ResponseEntity<?> getMyBlog(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added successfully"));
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity<?> updateBlog(@AuthenticationPrincipal User user,@PathVariable Integer blog_id,@RequestBody @Valid Blog blog){
        blogService.updateBlog(user.getId(),blog_id,blog);

        return ResponseEntity.status(200).body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity<?> deleteBlog(@AuthenticationPrincipal User user,@PathVariable Integer blog_id){
        blogService.deleteBlog(user.getId(),blog_id);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully"));
    }

    @GetMapping("/get/blog/{blog_id}")
    public ResponseEntity<?> getBlogById(@AuthenticationPrincipal User user,@PathVariable Integer blog_id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),blog_id));
    }

    @GetMapping("/get/title/{title}")
    public ResponseEntity<?> getBlogByTitle(@AuthenticationPrincipal User user,@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(),title));
    }
}
