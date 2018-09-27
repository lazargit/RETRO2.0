package com.shamildev.retro.domain.models;

import com.shamildev.retro.domain.core.DomainObject;
import com.shamildev.retro.domain.util.Pair;


/**
 * Created by Shamil Lazar
 */
public class AppUser implements DomainObject {

    private User user;
    private String name;
    private String language;
    private String user_id;
    private String tmdb_guest_session;
    private Long tmdb_expires_at;
    private String email;
    private String uid;
    private Boolean isLoggedIn = false;
    private String fbtoken;
    private Pair<String, String> twtoken;
    private String photoUrl;
    private byte[] profilePic;
    private String sigin_provider;
    private SignInType signintype;

    public enum SignInType
    {
        email, facebook, twitter
    }

    public AppUser(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return this.name;
    }

    public String getLanguage() {
        return language;
    }

    public void setName(String name) {
        System.out.println("TAG>> "+name);
        this.name = name;
        System.out.println("TAG< "+this.name);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTmdb_guest_session() {
        return tmdb_guest_session;
    }

    public void setTmdb_guest_session(String tmdb_guest_session) {
        this.tmdb_guest_session = tmdb_guest_session;
    }

    public Long getTmdb_expires_at() {
        return tmdb_expires_at;
    }

    public void setTmdb_expires_at(Long tmdb_expires_at) {
        this.tmdb_expires_at = tmdb_expires_at;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUser(User user) {
        this.user = user;
        System.out.println("setUser> ");
        setUser_id(user.user_id());
        setName(user.name());
        setLanguage(user.language());
        setTmdb_guest_session(user.tmdb_guest_session());
        setTmdb_expires_at(user.tmdb_expires_at());
    }

    public User getUser() {
        return user;
    }

    public void setFirebaseUser(String uid, String email, String fName, String providerId, String photoUrl) {
        System.out.println("TAG> "+fName);
        setUid(uid);
        setEmail(email);
        setName(fName);
        setSigin_provider(providerId);
        setPhotoUrl(photoUrl);
        setLoggedIn(true);
    }

    public void removetFirebaseUser() {
        setUid(null);
        setEmail(null);
        setName(null);
        setSigin_provider(null);
        setLoggedIn(false);
    }

    private Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public Boolean isLoggedIn() {
        return getLoggedIn();
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getSigin_provider() {
        return sigin_provider;
    }

    public void setSigin_provider(String sigin_provider) {
        this.sigin_provider = sigin_provider;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "name=='" + this.name + '\'' +
                ", language='" + language + '\'' +
                ", user_id='" + user_id + '\'' +
                ", tmdb_guest_session='" + tmdb_guest_session + '\'' +
                ", tmdb_expires_at=" + tmdb_expires_at +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", photoUrl='" + photoUrl + '\'' +
                ", sigin_provider='" + sigin_provider + '\'' +
                ", fbtoken='" + fbtoken + '\'' +
                '}';
    }

    public void setFBToken(String token) {
        fbtoken = token;
        signintype = SignInType.facebook;
    }

    public String getFbtoken() {
        return fbtoken;
    }

    public void setTwtoken(String token, String secret) {
        twtoken = new Pair<>(token, secret);
        signintype = SignInType.twitter;
    }

    public Pair<String, String> getTwtoken() {
        return twtoken;
    }

    public SignInType getSignintype() {
        return signintype;
    }
}
