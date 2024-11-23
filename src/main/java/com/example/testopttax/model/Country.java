package com.example.testopttax.model;

import com.example.testopttax.enums.ReportFormat;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Country extends Model {
    @Column(nullable = false, unique = true)
    private String name;


    @Column(nullable = false, unique = true, length = 2)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportFormat reportFormat;
}
