package com.development.models.inventory;

import java.math.BigDecimal;

public class Materials {
    private int id;
    private String materialName;
    private int quantity; // Total stock
    private String unit;  // e.g., "kg", "tons", "pallets"
    private BigDecimal pricePerUnit;

    public Materials() {}
}