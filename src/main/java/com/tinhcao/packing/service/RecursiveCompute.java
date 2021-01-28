package com.tinhcao.packing.service;

import com.tinhcao.packing.model.Item;
import com.tinhcao.packing.model.Result;
import com.tinhcao.packing.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author tinhcao
 * 1/28/21
 * 12:40 AM
 **/
public class RecursiveCompute implements PackageCompute {
    @Override
    public Result computeResult(int capacity, List<Item> items) {
        /*
         * In case the total cost the of the packaged items is the same for two sets of items,
         * you should prefer the combination of items which has a lower total weight.
         */
        items.sort(Comparator.comparing(Item::getWeight));

        // Initialize arrays: weights, values and visited items

        Double[] wt = items.stream().map(Item::getWeight).toArray(Double[]::new);
        Integer[] val = items.stream().map(Item::getCost).toArray(Integer[]::new);
        Boolean[] visited = new Boolean[items.size()];
        Arrays.fill(visited, Boolean.FALSE);


        // Compute the recursion tree, keeping track of visited items
        Integer maxValue = maximizeValueFor(capacity, wt, val, items.size(), visited);

        List<Item> resultItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            // There may be up to 15 items you can to choose from.
            if (visited[i] && resultItems.size() < Constants.MAX_ITEM) {
                resultItems.add(items.get(i));
            }
        }

        // Items included in the solution should be listed following index natural order
        resultItems.sort(Comparator.comparing(Item::getIndex));
        // return result
        return new Result(resultItems, maxValue);
    }

    private Integer maximizeValueFor(Integer capacity, Double[] wt, Integer[] val, Integer N, Boolean[] visited) {

        if (N == 0 || capacity == 0) {
            return 0; // base case
        }

        if (wt[N - 1] > capacity) {
            // overflow weight, ignore item
            return maximizeValueFor(capacity, wt, val, N - 1, visited);

        } else {

            // pick max between two cases:
            // (1) nth item included
            // (2) nth item not included

            Boolean[] v1 = new Boolean[visited.length];
            Boolean[] v2 = new Boolean[visited.length];

            System.arraycopy(visited, 0, v1, 0, v1.length);
            System.arraycopy(visited, 0, v2, 0, v2.length);

            v1[N - 1] = true;

            Integer s1 = val[N - 1] + maximizeValueFor(capacity - wt[N - 1].intValue(), wt, val, N - 1, v1);
            Integer s2 = maximizeValueFor(capacity, wt, val, N - 1, v2);

            if (s1 > s2) {
                System.arraycopy(v1, 0, visited, 0, v1.length);
                return s1;
            } else {
                System.arraycopy(v2, 0, visited, 0, v2.length);
                return s2;
            }
        }
    }
}
