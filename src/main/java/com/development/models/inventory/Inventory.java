package com.development.models.inventory;

import java.math.BigDecimal;

public class Inventory {
    private int id;
    private int projectId;
    private int materialId;
    private BigDecimal quantity; // Amount assigned to this specific project

    public Inventory() {}
}
