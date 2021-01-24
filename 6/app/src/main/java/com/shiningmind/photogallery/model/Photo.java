
package com.shiningmind.photogallery.model;

import com.google.gson.annotations.SerializedName;

public class Photo {
    private String id;
    private String owner;
    private String secret;
    private String server;
    private Integer farm;
    private String title;
    @SerializedName("ispublic")
    private Integer isPublic;
    @SerializedName("isfriend")
    private Integer isFriend;
    @SerializedName("isfamily")
    private Integer isFamily;

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

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

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
