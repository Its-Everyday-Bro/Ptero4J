package com.stanjg.ptero4j.entities;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class PteroUser {

    private int id;
    private String firstName;
    private String lastName;
    private String updated;
    private String created;
    private String language;
    private boolean isRootAdmin;
    private String uuid;
    private String email;
    private String username;

    public PteroUser(int id, String firstName, String lastName, String updated, String created, String language, boolean isRootAdmin, String uuid, String email, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.updated = updated;
        this.created = created;
        this.language = language;
        this.isRootAdmin = isRootAdmin;
        this.uuid = uuid;
        this.email = email;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUpdated() {
        return updated;
    }

    public String getCreated() {
        return created;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isRootAdmin() {
        return isRootAdmin;
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() { return firstName + " " + lastName; }

}
