package com.example.testopttax.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_incomes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "income_category_id", "income_date"})
})
public class UserIncome extends Model {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_income_user"))
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "income_category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_income_category"))
    private IncomeCategory incomeCategory;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate incomeDate;
}
