package com.project.juntas.model;

import com.project.juntas.model.enums.Provider;
import com.project.juntas.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String name;

    private String lastName;

    private String password;

    @Column(unique = true)
    private String email;

    private int dni;

    private String profileImage;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @Column(name = "register_date")
    private LocalDate registerDate;



    @PrePersist
    public void prePersist(){
        this.registerDate = LocalDate.now();
    }


}