package org.example.lab12.Repository;

import org.example.lab12.Model.Blog;
import org.example.lab12.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);

    List<Blog> findBlogByUser(User user);

    Blog findBlogByIdAndUser(Integer blog_Id,User user);

    Blog findBlogByTitleAndUser(String title,User user);
}
