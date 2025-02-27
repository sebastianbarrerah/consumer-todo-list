package com.example.to_do_list.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tasks", "tasksAsCollaborator"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String role;

    @OneToMany(mappedBy = "author")
    private List<Task> tasks;

    @ManyToMany(mappedBy = "collaborators")
    private List<Task> tasksAsCollaborator;
}