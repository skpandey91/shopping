package com.zooplus.shoppingcart.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class OrderBalance {

    @Setter
    @Getter
    private Long orderId;
    @Setter
    @Getter
    private Double amount;

}