package dev.wellyngton.portfolioapi.services;

import dev.wellyngton.portfolioapi.models.BlogPost;
import dev.wellyngton.portfolioapi.repositories.BlogPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));
    }

    public BlogPost createPost(BlogPost post) {
        return blogPostRepository.save(post);
    }

    public BlogPost updatePost(Long id, BlogPost postDetails) {
        BlogPost post = blogPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + id));

        post.setTitle(postDetails.getTitle());
        post.setSummary(postDetails.getSummary());
        post.setContent(postDetails.getContent());

        return blogPostRepository.save(post);
    }

    public void deletePost(Long id) {
       if (!blogPostRepository.existsById(id)) {
              throw new EntityNotFoundException("Post not found with id: " + id);
       }
       blogPostRepository.deleteById(id);
    }
}
