package com.tinhcao.packing.model;

import lombok.*;

import java.util.List;

/**
 * @author tinhcao
 * 1/28/21
 * 12:31 AM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result {
    public List<Item> items;
    public int totalCost;
}
