package com.springboot.blogapp.controller;

import com.springboot.blogapp.payload.PostDto;
import com.springboot.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
//        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto){
        System.out.println("inside create post");
        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/byName")
    public ResponseEntity<List<PostDto>> getPostByName(@RequestParam(defaultValue = "test") String username){
        System.out.println(username);
        List<PostDto> posts = postService.findByName(username);
        return ResponseEntity.ok(posts);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){

        PostDto postResponse = postService.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}
