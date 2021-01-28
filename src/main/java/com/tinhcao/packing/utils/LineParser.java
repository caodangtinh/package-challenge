package com.tinhcao.packing.utils;

import com.tinhcao.packing.exception.PackingException;
import com.tinhcao.packing.model.Item;
import com.tinhcao.packing.model.PackageLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tinhcao
 * 1/28/21
 * 12:09 AM
 **/
public class LineParser {
    public static PackageLine parse(String line) {
        PackageLine packageLine = null;
        String[] lineParts = line.split(Constants.COLON_SPACE);
        try {
            if (lineParts.length == 2) {
                Integer capacity = Integer.parseInt(lineParts[0]);
                String[] itemsParts = lineParts[1].split(Constants.SPACE_REGEX);
                if (itemsParts.length > 0) {
                    List<Item> itemsList = new ArrayList<>();
                    for (String item : itemsParts) {
                        String[] itemParts = item.replace("(", "").replace(")", "").split(",");
                        if (itemParts.length == 3) {
                            itemsList.add(new Item(Integer.parseInt(itemParts[0])
                                    , Double.parseDouble(itemParts[1])
                                    , Integer.parseInt(itemParts[2].replace("€", ""))
                            ));
                        } else {
                            itemsList.clear();
                            break;
                        }
                    }

                    if (!itemsList.isEmpty()) {
                        packageLine = new PackageLine(capacity, itemsList);
                    }

                } else {
                    throw new PackingException("Line has no item data.");
                }
            } else {
                throw new PackingException("Line data structure is invalid.");
            }
        } catch (NumberFormatException e) {
            packageLine = null;
        }

        return packageLine;
    }
}
