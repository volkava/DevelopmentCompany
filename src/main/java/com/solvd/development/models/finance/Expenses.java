package com.solvd.development.models.finance;

import java.math.BigDecimal;
import java.util.Date;

public class Expenses {
    private int id;
    private int projectId;
    private String category; // ENUM like 'Labor', 'Materials', 'Permits'
    private BigDecimal amount;
    private Date expenseDate;

    public Expenses() {}
}