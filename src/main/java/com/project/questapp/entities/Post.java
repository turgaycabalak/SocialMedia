package com.project.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)//bir user silindiginde, tum postlari silinecek
    @JsonIgnore
    private User user;

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String text;

    public Post(User user,
                String title,
                String text) {

        this.user = user;
        this.title = title;
        this.text = text;
    }
}
