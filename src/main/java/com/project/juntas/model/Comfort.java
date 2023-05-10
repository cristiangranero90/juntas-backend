package com.project.juntas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comforts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comfort implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comfortId;
    private boolean acceptChild;
    private boolean petFriendly;
    private boolean acceptSmokers;
    private boolean luggage;


}