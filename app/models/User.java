package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.security.MessageDigest;

@Entity
public class User {

    @Id
    protected int id;

    @Column(unique = true)
    protected String username;

    protected String password;

    public boolean comparePasswords(String password) {
        return getHash(password).equals(getPassword());
    }

    public void setPasswordInCleartext (String password) {
        setPassword(getHash(password));
    }

    public static String getHash(String input) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return new String(digest.digest(input.getBytes()));
        }catch (Exception ex ) {

        }
        return input;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
