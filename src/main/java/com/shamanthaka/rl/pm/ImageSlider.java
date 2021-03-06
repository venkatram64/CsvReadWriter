package com.shamanthaka.rl.pm;

import java.util.ArrayList;
import java.util.List;

public class ImageSlider {

    private List<String> imageList = new ArrayList<>();

    public void setImageList(String image){
        this.imageList.add(image);
    }

    public List<String> getImageList(){
        return this.imageList;
    }

    public static void main(String[] args) {
        ImageSlider imageSlider = new ImageSlider();
        imageSlider.setImageList("https://www.google.com/someImage");
        System.out.println(imageSlider.getImageList().get(0));
    }
}
