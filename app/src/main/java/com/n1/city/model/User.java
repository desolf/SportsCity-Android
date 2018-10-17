package com.n1.city.model;

import android.net.Uri;

public class User {
    private String uid = "";
    private String profileId = "";
    private String nickname = "";
    private Uri avatarUri;

     public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarStoragePath() {
        if (getUid() != null)
            return "profile/" + getUid() + ".jpg";

        return "";
    }

    public Uri getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(Uri avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}
