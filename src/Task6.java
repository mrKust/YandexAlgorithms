import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfSystems = Integer.parseInt(inputData.get(1));
        List<String> outputData = new ArrayList<>();
        if (numberOfSystems == 0) {
            outputData.add("0");
        } else {
        int numberOfSectors = countMaxValOfSector(numberOfSystems, inputData.subList(2, inputData.size()));
        int[] hardDrive = new int[numberOfSectors + 1];
        outputData.add(Integer.toString(getNumberOfSystems(hardDrive, numberOfSystems, inputData.subList(2, inputData.size()))));
        }

        writeData(outputData);

    }

    private static int countMaxValOfSector(int numberOfOS, List<String> inputs) {

        int maxDiscSpaceVal = 0;

        for (int i = 0; i < numberOfOS; i++) {
            String[] memoryVals = inputs.get(i).split(" ");
            int val = Integer.parseInt(memoryVals[1]);
            if (val > maxDiscSpaceVal)
                maxDiscSpaceVal = val;
        }

        return maxDiscSpaceVal;
    }

    private static int getNumberOfSystems(int[] hardDrive, int numberOfOS, List<String> inputs) {
        int installedOS = 1;

        for (int i = 0; i < numberOfOS; i++) {
            String[] memoryVals = inputs.get(i).split(" ");
            int start = Integer.parseInt(memoryVals[0]);
            if (hardDrive[start] != 0) {
                replaceAll(hardDrive, hardDrive[start]);
            }
            int end = Integer.parseInt(memoryVals[1]);
            if (hardDrive[end] != 0) {
                replaceAll(hardDrive, hardDrive[end]);
            }
            for (int k = start; k <= end; k++) {
                hardDrive[k] = installedOS;
            }
            installedOS++;
        }

        Set<Integer> countSymbols = new HashSet<>();
        for (int i = 0; i < hardDrive.length; i++) {
            int tmp = hardDrive[i];
            countSymbols.add(tmp);
        }
        countSymbols.remove(0);

        return countSymbols.size();
    }

    private static void replaceAll(int[] input, int oldVal) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == oldVal)
                input[i] = 0;
        }
    }

    private static List<String> readData() {
        List<String> text = new ArrayList<>();
        try (BufferedReader inputFile = new BufferedReader(new FileReader("input.txt"))) {
            String currentLine = inputFile.readLine();

            while (currentLine != null) {
                text.add(currentLine);
                currentLine = inputFile.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

    private static void writeData(List<String> writeData) {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("output.txt"))) {

            for (String currentLine : writeData) {
                outputFile.write(currentLine);
            }

            outputFile.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}