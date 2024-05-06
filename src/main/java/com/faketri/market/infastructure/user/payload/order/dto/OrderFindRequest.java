package com.faketri.market.infastructure.user.payload.order.dto;

import com.faketri.market.entity.user.payload.order.model.EStatusOrder;

public class OrderFindRequest {

    public String suffix;
    public EStatusOrder statusOrder;

    public OrderFindRequest() {
    }

    public OrderFindRequest(String suffix, EStatusOrder statusOrder) {
        this.suffix = suffix;
        this.statusOrder = statusOrder;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public EStatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(EStatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }
}
