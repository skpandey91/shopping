package com.zooplus.shoppingcart.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CustomerBalance {
    @Setter
    @Getter
    private Long customerId;
    @Setter
    @Getter
    private Double amount;
}