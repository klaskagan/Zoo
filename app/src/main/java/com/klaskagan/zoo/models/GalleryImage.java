package com.klaskagan.zoo.models;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/26.
 */
public class GalleryImage {

    private String thumbnail;
    private String image;
    private String caption;

    public GalleryImage() {
    }

    public GalleryImage(String thumbnail, String image, String caption) {
        this.thumbnail = thumbnail;
        this.image = image;
        this.caption = caption;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
