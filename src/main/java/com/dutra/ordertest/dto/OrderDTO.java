package com.dutra.ordertest.dto;

import java.util.List;

public class OrderDTO {

    private String externalOrderId;
    private List<OrderItemDTO> items;

    public String getExternalOrderId() { return externalOrderId; }
    public void setExternalOrderId(String externalOrderId) { this.externalOrderId = externalOrderId; }

    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
}
