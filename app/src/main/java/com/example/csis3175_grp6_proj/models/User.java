package com.example.csis3175_grp6_proj.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="userid")
    private int UserId;

    @NonNull
    @ColumnInfo(name="firstname")
    private String FirstName;

    @NonNull
    @ColumnInfo(name="lastname")
    private String LastName;

    @NonNull
    @ColumnInfo(name="email")
    private String Email;

    @NonNull
    @ColumnInfo(name="password")
    private String Password;

    @NonNull
    @ColumnInfo(name="birthday")
    private String Birthday;

    @NonNull
    @ColumnInfo(name="beactivepassholder")
    private boolean BeActivePassHolder;

    @NonNull
    @ColumnInfo(name="beactivepassnum")
    private String BeActivePassNum;

    @NonNull
    public int getUserId() {
        return UserId;
    }

    public void setUserId(@NonNull int userId) {
        UserId = userId;
    }

    @NonNull
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(@NonNull String firstName) {
        FirstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return LastName;
    }

    public void setLastName(@NonNull String lastName) {
        LastName = lastName;
    }

    @NonNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NonNull String email) {
        Email = email;
    }

    @NonNull
    public String getPassword() {
        return Password;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }

    @NonNull
    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(@NonNull String birthday) {
        Birthday = birthday;
    }

    public boolean isBeActivePassHolder() {
        return BeActivePassHolder;
    }

    public void setBeActivePassHolder(boolean beActivePassHolder) {
        BeActivePassHolder = beActivePassHolder;
    }

    @NonNull
    public String getBeActivePassNum() {
        return BeActivePassNum;
    }

    public void setBeActivePassNum(@NonNull String beActivePassNum) {
        BeActivePassNum = beActivePassNum;
    }


    public User() {
    }

    public User(int userId, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, @NonNull String birthday, boolean beActivePassHolder, @NonNull String beActivePassNum) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Password = password;
        Birthday = birthday;
        BeActivePassHolder = beActivePassHolder;
        BeActivePassNum = beActivePassNum;
    }
}
