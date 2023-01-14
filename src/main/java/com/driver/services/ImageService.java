package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
//        List<Blog> blogList = blogRepository.findAll();
//         Blog existingblog=null;
//        for(Blog blogit: blogList){
//            if(blogit.getTitle().equals(blog.getTitle())){
//                existingblog=blogit;
//                break;
//            }
//        }
         Blog existingblog= blogRepository.findById(blog.getId()).get();
        Image image = new Image(description,dimensions);
        image.setBlog(existingblog);
        List<Image> currimagelist = existingblog.getImageList();
        currimagelist.add(image);
        existingblog.setImageList(currimagelist);
          blogRepository.save(existingblog);
       return image;

    }

    public void deleteImage(Image image){
         imageRepository2.delete(image);
    }

    public Image findById(int id) {
      return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0

        int imagedim = calculatevolume(image.getDimensions());
        int screendim = calculatevolume(screenDimensions);

        return screendim/imagedim;

    }

    public int calculatevolume(String dimension){
        int i=0, num=0,ans=1;
        while(i<dimension.length()){
            if(dimension.charAt(i)=='X'){
                ans=ans*num;
                num=0;
            }
            else{
                num= num*10 + (int)(dimension.charAt(i)-'0');
            }
            i++;
        }
        ans=ans*num;
        return ans;
    }
}
