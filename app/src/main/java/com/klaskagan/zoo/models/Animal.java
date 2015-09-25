package com.klaskagan.zoo.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Viktoras Baracevicius
 * @since 2015/09/25.
 */
public class Animal implements Parcelable {
    private String name;
    private String species;
    private String description;
    private String thumbnail;
    private String image;

    public Animal() {
    }

    public Animal(String name, String species, String description, String thumbnail, String image) {
        this.name = name;
        this.species = species;
        this.description = description;
        this.thumbnail = thumbnail;
        this.image = image;
    }

    public Animal(Parcel source) {
        name = source.readString();
        species = source.readString();
        description = source.readString();
        thumbnail = source.readString();
        image = source.readString();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        applyDefaultValues();
        dest.writeString(name);
        dest.writeString(species);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeString(image);
    }

    private void applyDefaultValues() {
        if (name == null) {
            name = StringUtils.EMPTY;
        }
        if (species == null) {
            species = StringUtils.EMPTY;
        }
        if (description == null) {
            description = StringUtils.EMPTY;
        }
        if (thumbnail == null) {
            thumbnail = StringUtils.EMPTY;
        }
        if (image == null) {
            image = StringUtils.EMPTY;
        }
    }
}
