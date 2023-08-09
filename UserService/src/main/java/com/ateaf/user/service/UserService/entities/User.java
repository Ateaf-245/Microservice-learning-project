package com.ateaf.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "micro_user")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "name",length = 20)
    private String name;

    @Column(name = "email")
    private  String email;

    @Column(name = "about")
    private String about;

    @Transient
    private List<Rating> rating = new ArrayList<>();

}
