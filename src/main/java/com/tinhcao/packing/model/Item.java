package com.tinhcao.packing.model;

import lombok.*;

/**
 * @author tinhcao
 * 1/27/21
 * 11:50 PM
 * Model class which hold information of each item contains index, weight and cost
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer index;
    private Double weight;
    private Integer cost;
}
