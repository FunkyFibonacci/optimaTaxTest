package com.example.testopttax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role extends Model{
    private String role;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "role")
    private Set<User> users;
}
