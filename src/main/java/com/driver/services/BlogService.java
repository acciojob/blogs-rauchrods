package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
          return blogRepository1.findAll();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog(title,content);
        User user = userRepository1.findById(userId).get();
        blog.setUser(user);
        List<Blog> currbloglist = user.getBlogList();
        currbloglist.add(blog);
        user.setBlogList(currbloglist);
        userRepository1.save(user);
        //updating the blog details

        //Updating the userInformation and changing its blogs

    }

    public Blog findBlogById(int blogId){
       return  blogRepository1.findById(blogId).get();
        //find a blog
    }

    public void addImage(Integer blogId, String description, String dimensions){

        Blog blog = blogRepository1.findById(blogId).get();
        Image image = imageService1.createAndReturn(blog,description,dimensions);
//        image.setBlog(blog);
//        List<Image> currimagelist = blog.getImageList();
//        currimagelist.add(image);
//        blog.setImageList(currimagelist);
//        blogRepository1.save(blog);
        //add an image to the blog after creating it
    }

    public void deleteBlog(int blogId){
        if(findBlogById(blogId)!=null){
            blogRepository1.deleteById(blogId);
        }

        //delete blog and corresponding images
    }
}
