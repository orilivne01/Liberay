package com.example.libary.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Auter")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Auter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String firstName;
    String lastName;
    @JsonIgnore
    @OneToMany(mappedBy = "auter")
    List<Books> booksList = new LinkedList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "auters")
    List<AuterGild>  auterGilds = new LinkedList<>();;
}
