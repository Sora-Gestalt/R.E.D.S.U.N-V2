package com.example.backend.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.Entities.Blogs;
import com.example.backend.Repositories.BlogRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class BlogServiceImpl implements BlogsService {
    
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Override
    public Blogs saveBlog(Blogs blog){
        return blogRepository.save(blog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Blogs> getAllBlogs(){
        return blogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Blogs getBlogById(UUID id){
        return blogRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Blog not found with this id: " + id)
        );
    }

    @Override
    public void deleteBlog(UUID id){
        if(!blogRepository.existsById(id)){
            throw new EntityNotFoundException("Delete Failed!, Blog not found with given id: " + id);
        }

        blogRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Blogs> searchBlogByTitle(String keyword){
        return blogRepository.findByTitleContaining(keyword);
    }

    /*
    @Override
    @Transactional(readOnly = true)
    public List<Blogs> searchBlogBySlug(String slug){
        return blogRepository.findBySlug(slug); --> Note! : findBySlug is not implemented in the repository
    }
    */

    @Override
    @Transactional(readOnly = true)
    public List<Blogs> searchBlogByPublishDate(LocalDateTime date,boolean isAfterDate){
        return isAfterDate ? blogRepository.findByDateAfterThisDate(date) : blogRepository.findByDateBeforeThisDate(date);
    }
}
