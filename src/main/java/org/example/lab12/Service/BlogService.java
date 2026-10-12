package org.example.lab12.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab12.API.ApiException;
import org.example.lab12.Model.Blog;
import org.example.lab12.Model.User;
import org.example.lab12.Repository.AuthRepository;
import org.example.lab12.Repository.BlogRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlog(){
        List<Blog> blogs = blogRepository.findAll();
        if(blogs.isEmpty()){
            throw new ApiException("there is no blogs in the system");
        }
        return blogs;
    }

    public List<Blog> getMyBlog(Integer user_id){
        User user = authRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        return blogRepository.findBlogByUser(user);
    }

    public void addBlog(Integer user_id,Blog blog){
        User user = authRepository.findUserById(user_id);

        if(user==null){
            throw new ApiException("user not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer user_id,Integer blog_id,Blog blog) {
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("user not found");
        }

        Blog oldBlog = blogRepository.findBlogById(blog_id);
        if (oldBlog == null) {
            throw new ApiException("blog not found");
        }

        if(oldBlog.getUser().getId()!=blog_id){
            throw new ApiException("can not update");
        }

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer user_id,Integer blog_id){
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("user not found");
        }

        Blog blog = blogRepository.findBlogById(blog_id);
        if (blog == null) {
            throw new ApiException("blog not found");
        }

        if(blog.getUser().getId()!=blog_id){
            throw new ApiException("can not delete");
        }

        blogRepository.delete(blog);
    }

    public Blog getBlogById(Integer user_id,Integer blog_id){
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        Blog blog = blogRepository.findBlogByIdAndUser(blog_id,user);
        if(blog ==null){
            throw new ApiException("blog not found");
        }

        if(!blog.getUser().getId().equals(user_id)){
            throw new ApiException("can not access this blog");
        }
        return blog;
    }

    public Blog getBlogByTitle(Integer user_id,String title){
        User user = authRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        Blog blog = blogRepository.findBlogByTitleAndUser(title,user);
        if(blog ==null){
            throw new ApiException("blog not found");
        }

        if(!blog.getUser().getId().equals(user_id)){
            throw new ApiException("can not access this blog");
        }
        return blog;
    }
}
