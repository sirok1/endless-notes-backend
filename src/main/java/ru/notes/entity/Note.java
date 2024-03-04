package ru.notes.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "note")
public class Note {
    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String text;

    @Column(nullable = false)
    @CreationTimestamp
    private Instant createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private User author;
}
