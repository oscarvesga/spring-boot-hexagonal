package com.example.demo.posts.posts.infraestructure.outbound.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", nullable = false)
    private Long idPost;

    @Column(nullable = false)
    private String title;

    private String body;

    @Column(nullable = false, updatable = false)
    private Long idUser;
}
