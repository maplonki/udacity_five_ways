package com.fiveways.cook.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hugo on 4/2/16.
 * <p/>
 * This class represents a single recipe coming
 * from the API.
 */
public class RecipeModel implements Parcelable {

    /**
     * This is the name of the recipe
     */
    private String label;

    /**
     * This is the URL of the image
     * from the recipe
     */
    private String image;

    /**
     * This is the source URL from the
     * recipe.
     */
    private String url;

    protected RecipeModel(Parcel in) {
        label = in.readString();
        image = in.readString();
        url = in.readString();
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeString(image);
        dest.writeString(url);
    }
}
