package com.dutra.ordertest.dto;

public class OrderItemDTO {
    private Integer productCode;
    private int quantity;
    private java.math.BigDecimal price;

    public Integer getProductCode() { return productCode; }
    public void setProductCode(Integer productCode) { this.productCode = productCode; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public java.math.BigDecimal getPrice() { return price; }
    public void setPrice(java.math.BigDecimal price) { this.price = price; }
}
