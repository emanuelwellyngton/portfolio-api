package dev.wellyngton.portfolioapi.repositories;

import dev.wellyngton.portfolioapi.models.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
