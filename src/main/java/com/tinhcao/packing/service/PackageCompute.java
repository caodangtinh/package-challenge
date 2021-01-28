package com.tinhcao.packing.service;

import com.tinhcao.packing.model.Item;
import com.tinhcao.packing.model.Result;

import java.util.List;

/**
 * @author tinhcao
 * 1/28/21
 * 12:37 AM
 **/
public interface PackageCompute {
    /**
     * @param capacity capacity the maximum weight a package can hold
     * @param items    a list of items, each having index, weight and value
     * @return {@link Result}
     */
    Result computeResult(int capacity, List<Item> items);
}
