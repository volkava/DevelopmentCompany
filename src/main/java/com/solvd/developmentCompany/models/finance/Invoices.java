package com.solvd.developmentCompany.models.finance;

import java.math.BigDecimal;
import java.util.Date;

public class Invoices {
    private int id;
    private int orderId;
    private BigDecimal amount;
    private Date dateIssued;
    private Date dueDate;
    private String status; // ENUM ('Paid', 'Pending', 'Overdue')

    public Invoices() {}
}
