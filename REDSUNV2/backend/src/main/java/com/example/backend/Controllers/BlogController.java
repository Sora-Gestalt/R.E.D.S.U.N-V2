package com.example.backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Entities.Blogs;
import com.example.backend.Services.BlogsService;

@RestController
@RequestMapping("/api/v1/blogs")
@CrossOrigin("*")
public class BlogController {
    private final BlogsService blogsService;

    public BlogController(BlogsService blogsService){
        this.blogsService = blogsService;
    }

    @PostMapping
    public ResponseEntity<Blogs> createBlog(@RequestBody Blogs blog){
        Blogs savedBlog = blogsService.saveBlog(blog);
        return new ResponseEntity<>(savedBlog,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Blogs>> getAllBlogs(){
        return ResponseEntity.ok(blogsService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blogs> getBlogById(@PathVariable UUID id){
        return ResponseEntity.ok(blogsService.getBlogById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable UUID id){
        blogsService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Blogs>> searchBlogByTitle(@RequestParam String keyword){
        return ResponseEntity.ok(blogsService.searchBlogByTitle(keyword));
    }

    @GetMapping("/search/publish_date/after")
    public ResponseEntity<List<Blogs>> searchBlogByPublishDateAfter(@RequestParam LocalDateTime date){
        return ResponseEntity.ok(blogsService.searchBlogByPublishDate(date, true));
    }

    @GetMapping("/search/publish_date/before")
    public ResponseEntity<List<Blogs>> searchBlogByPublishDateBefore(@RequestParam LocalDateTime date){
        return ResponseEntity.ok(blogsService.searchBlogByPublishDate(date, false));
    }
}
