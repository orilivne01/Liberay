package com.example.libary.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "AUTERGILD")
@Getter
@Setter
public class AuterGild {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "gildaId_auterId",joinColumns = @JoinColumn(name = "gild_id"),inverseJoinColumns = @JoinColumn(name = "auter_id"))
    List<Auter> auters =  new LinkedList<>();;
}
