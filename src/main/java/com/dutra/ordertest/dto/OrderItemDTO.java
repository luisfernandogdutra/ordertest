package com.dutra.ordertest.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Integer productCode;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemDTO(Integer id, Integer qtd, BigDecimal price) {
        this.productCode = id;
        this.quantity = qtd;
        this.price = price;
    }

    public OrderItemDTO() {
    }

    public Integer getProductCode() { return productCode; }
    public void setProductCode(Integer productCode) { this.productCode = productCode; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
