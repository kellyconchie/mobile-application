package com.example.testingproject;


// This class is declaring the objects that will be seen in Main Activity these being a name and image
public class Section {

    int Image;
    String Name;

    public Section(int image, String name) {
        Image = image;
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

