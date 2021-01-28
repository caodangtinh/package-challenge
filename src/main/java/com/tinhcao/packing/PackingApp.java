package com.tinhcao.packing;

import com.tinhcao.packing.exception.PackingException;
import com.tinhcao.packing.service.PackingService;


/**
 * @author tinhcao
 * 1/28/21
 * 12:01 AM
 * Main execution class
 **/
public class PackingApp {
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                System.out.println(PackingService.pack(args[0]));
            } catch (PackingException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("Please, enter a valid absolute filepath.");
        }
    }
}
