package com.zooplus.shoppingcart.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Orders {

    @Id
    @Getter
    @Setter
    @NotNull
    private Long orderId;
    @Getter
    @Setter
    @NotNull
    private Long customerId;
    @Getter
    @Setter
    @NotNull
    private Double amount;
}