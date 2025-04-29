package com.dutra.ordertest.dto;

import java.util.List;

public class OrderDTO {

    private Long externalOrderId;
    private List<OrderItemDTO> items;

    public Long getExternalOrderId() { return externalOrderId; }
    public void setExternalOrderId(Long externalOrderId) { this.externalOrderId = externalOrderId; }

    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
}
