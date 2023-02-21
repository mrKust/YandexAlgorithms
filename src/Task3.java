import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfDiegoStickers = Integer.parseInt(inputData.get(0));
        String[] diegosTmp = inputData.get(1).split(" ");
        int[] diegosStickers = new int[numberOfDiegoStickers];
        for (int i = 0; i < numberOfDiegoStickers; i++)
            diegosStickers[i] = Integer.parseInt(diegosTmp[i]);

        diegosStickers = Arrays.stream(diegosStickers).distinct().sorted().toArray();

        int numberOfCollectors = Integer.parseInt(inputData.get(2));
        String[] collectorsTmp = inputData.get(3).split(" ");
        List<Integer> collectorsNeededStickers = new ArrayList<>();
        for (int i = 0; i < numberOfCollectors; i++)
            collectorsNeededStickers.add(Integer.parseInt(collectorsTmp[i]));

        List<String> outputData = new ArrayList<>();
        outputData.add(findNedeed(diegosStickers, collectorsNeededStickers));

        writeData(outputData);

    }

    private static String findNedeed(int[] diegosStickers, List<Integer> collectorsNeededStickers) {

        StringBuilder result = new StringBuilder();

        for (Integer tmp : collectorsNeededStickers) {
            int indexLeft = 0;
            int indexRight = diegosStickers.length - 1;
            while (indexLeft <= indexRight) {
                int middleIndex = (indexRight + indexLeft) / 2;
                if (tmp > diegosStickers[middleIndex]) {
                    indexLeft = middleIndex + 1;
                } else {
                    indexRight = middleIndex - 1;
                }
            }
            result.append(indexLeft);
            result.append("\n");

        }

        return result.toString();
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