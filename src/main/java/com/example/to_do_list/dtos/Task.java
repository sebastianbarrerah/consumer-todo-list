package com.example.to_do_list.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed from title to id
    private String title;
    private String description;
    private LocalDateTime createdAt;
    @ManyToMany
    @JoinTable(
      name = "task_collaborators",
      joinColumns = @JoinColumn(name = "task_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> collaborators;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}