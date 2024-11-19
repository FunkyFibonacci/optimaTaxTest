package com.example.testopttax.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tax_rates")
@Getter
@Setter
@RequiredArgsConstructor
public class TaxRate extends Model {
    @ManyToOne(fetch = FetchType.EAGER)  // Используем EAGER загрузку
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)  // Используем EAGER загрузку
    @JoinColumn(name = "income_category_id", nullable = false)
    private IncomeCategory incomeCategory;

    @Column(nullable = false)
    private BigDecimal rate;

}
