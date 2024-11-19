package com.example.testopttax.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "income_categories")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IncomeCategory extends Model {
    @Column(nullable = false, unique = true)
    private String name;
}
