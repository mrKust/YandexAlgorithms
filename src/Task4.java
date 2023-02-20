import java.io.*;
import java.util.*;

public class Task4 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfStudents = Integer.parseInt(inputData.get(0));
        int numberOfVariants = Integer.parseInt(inputData.get(1));
        int petyaRow = Integer.parseInt(inputData.get(2));
        //1 right 2 left
        int petyaSide = Integer.parseInt(inputData.get(3));

        List<String> outputData = new ArrayList<>();
        outputData.add(rightPlace(numberOfStudents, numberOfVariants, petyaRow, petyaSide));

        writeData(outputData);

    }

    public static String rightPlace(int numberOfStudents, int numberOfVariants, int petyaRow, int petyaSide) {

        int odd = numberOfVariants % 2;
        int nextRow = petyaRow + numberOfVariants / 2;
        int prevRow = petyaRow - numberOfVariants / 2;
        int nextPosition = petyaSide;
        int prevPosition = petyaSide;
        if (odd == 1) {
            if (petyaSide + 1 > 2) {
                nextRow++;
                nextPosition = 1;
            } else {
                nextPosition = 2;
            }
            if (petyaSide - 1 < 1) {
                prevRow--;
                prevPosition = 2;
            } else {
                prevPosition = 1;
            }
        }
        boolean prevValid = prevRow > 0;
        boolean nextValid = (nextRow - 1) * 2 + nextPosition - 1 < numberOfStudents;

        if (prevValid && nextValid) {
            int dPrev = petyaRow - prevRow;
            int dNext = nextRow - petyaRow;
            if (dPrev < dNext) {
                return prevRow + " " + prevPosition;
            } else {
                return nextRow + " " + nextPosition;
            }
        } else if (!prevValid && nextValid) {
            return nextRow + " " + nextPosition;
        } else if (prevValid) {
            return prevRow + " " + prevPosition;
        }
        return "-1";
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