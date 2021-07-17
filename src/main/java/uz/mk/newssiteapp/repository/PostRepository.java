package uz.mk.newssiteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mk.newssiteapp.entity.Comment;
import uz.mk.newssiteapp.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
