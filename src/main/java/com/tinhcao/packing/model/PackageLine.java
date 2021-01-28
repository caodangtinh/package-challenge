package com.tinhcao.packing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author tinhcao
 * 1/28/21
 * 12:12 AM
 * Class which hold information about each line
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageLine {
    private int capacity;
    private List<Item> items;
}
