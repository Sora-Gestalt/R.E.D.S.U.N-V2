package com.example.backend.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.example.backend.Entities.Blogs;

public interface BlogsService {
    Blogs saveBlog(Blogs blog);

    List<Blogs> getAllBlogs();

    Blogs getBlogById(UUID id);

    void deleteBlog(UUID id);

    List<Blogs> searchBlogByTitle(String keyword);

    // List<Blogs> searchBlogBySlug(String keyword);

    List<Blogs> searchBlogByPublishDate(LocalDateTime date,boolean isAfterDate);

    
}
