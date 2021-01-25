
package com.shiningmind.photogallery.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Photo {
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo
    @NonNull
    private String owner;
    @ColumnInfo
    @NonNull
    private String secret;
    @ColumnInfo
    @NonNull
    private String server;
    @ColumnInfo
    @NonNull
    private int farm;
    @ColumnInfo
    @NonNull
    private String title;
    @ColumnInfo
    @NonNull
    @SerializedName("ispublic")
    private int isPublic;
    @ColumnInfo
    @NonNull
    @SerializedName("isfriend")
    private int isFriend;
    @ColumnInfo
    @NonNull
    @SerializedName("isfamily")
    private int isFamily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    @NonNull
    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    @NonNull
    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    @NonNull
    public int getIsFamily() {
        return isFamily;
    }

    public void setIsFamily(int isFamily) {
        this.isFamily = isFamily;
    }

    public String getUrl(String size) {
        return String.format("https://live.staticflickr.com/%s/%s_%s_%s.jpg", getServer(), getId(), getSecret(), size);
    }

}
