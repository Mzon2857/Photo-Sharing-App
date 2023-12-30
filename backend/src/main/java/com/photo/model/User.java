package com.photo.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "given_name")
    private String givenName;

    private String nickname;

    @Column(name = "full_name")
    private String name;

    private String email;

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "locale")
    private String locale;

    @Column(name = "sub")
    private String sub;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public User() {
    }

    public User(Long id, String givenName, String profilePictureUrl) {
        this.id = id;
    }
}
