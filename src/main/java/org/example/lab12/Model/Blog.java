package org.example.lab12.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty(message = "title can not be empty")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "body can not be empty")
    private String body;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
