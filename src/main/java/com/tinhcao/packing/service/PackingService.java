package com.tinhcao.packing.service;

import com.tinhcao.packing.exception.PackingException;
import com.tinhcao.packing.model.PackageLine;
import com.tinhcao.packing.model.Result;
import com.tinhcao.packing.utils.Constants;
import com.tinhcao.packing.utils.LineParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tinhcao
 * 1/27/21
 * 11:55 PM
 * Packing Service class which contains all service methods for application
 **/
public class PackingService {

    // manual initialization instance since we don't use any DI mechanism
    private static final PackageCompute RECURSIVE_COMPUTE = new RecursiveCompute();

    /**
     * @param inputFilePath Absolute file path which contains line with proper data format
     * @return String containing the results for all test line
     * @throws PackingException
     */
    public static String pack(String inputFilePath) throws PackingException {
        Path path = Paths.get(inputFilePath);
        try {
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            StringBuilder result = new StringBuilder();
            int lineCount = 1;
            for (String line : lines) {
                result.append(compute(line, lineCount)).append(Constants.NEW_LINE_FEED);
                lineCount++;
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param line contains test data for each case, line format is [total weight] : ([index],[weight],[€value])
     * @return a comma-separated list of item index, or '-' if there's no result
     * @throws PackingException
     */
    public static String compute(String line, int lineCount) throws PackingException {
        PackageLine packageLine = LineParser.parse(line);
        if (packageLine == null) {
            throw new PackingException("Line " + lineCount + " contains invalid input.");
        }
        // The maximum weight that a package can hold must be <= 100.
        if (packageLine.getCapacity() > 100) {
            throw new PackingException("Line " + lineCount + " maximum weight that a package can hold must be <= 100.");
        }

        Result result = RECURSIVE_COMPUTE.computeResult(packageLine.getCapacity(), packageLine.getItems());

        String solutionItemsList;

        if (result.getItems() != null && !result.getItems().isEmpty()) {
            solutionItemsList = result.getItems().stream().map(i -> i.getIndex().toString()).collect(Collectors.joining(","));
        } else {
            solutionItemsList = "-";
        }

        return solutionItemsList;
    }
}
