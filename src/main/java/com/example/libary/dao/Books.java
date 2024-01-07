package com.example.libary.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import javax.persistence.*;

@Entity
@Table(name="Books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Books {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String bookName;
    @JsonIgnore
    @ManyToOne
    Auter auter;

    public Books(String bookName) {
        this.bookName = bookName;
    }
}
