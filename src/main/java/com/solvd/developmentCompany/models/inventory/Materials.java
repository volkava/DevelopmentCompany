package com.solvd.developmentCompany.models.inventory;

import java.math.BigDecimal;

public class Materials {
    private Long id;
    private String materialName;
    private int quantity;
    private String unit;
    private BigDecimal pricePerUnit;

    public Materials() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}