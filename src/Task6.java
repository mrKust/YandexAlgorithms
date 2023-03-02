import java.io.*;
import java.util.*;

public class Task6 {
    public static void main(String[] args) {

        List<String> inputData = readData();
        int numberOfSectors = Integer.parseInt(inputData.get(0));
        int numberOfSystems = Integer.parseInt(inputData.get(1));
        List<String> outputData = new ArrayList<>();
        if (numberOfSystems == 0) {
            outputData.add("0");
        } else {
            outputData.add(String.valueOf(countNumberOfOS(numberOfSectors, numberOfSystems, inputData.subList(2, inputData.size()))));
        }

        writeData(outputData);

    }

    private static int countNumberOfOS(int numberOfSectors, int numberOfSystems, List<String> sectorsData) {
        class Segment {
            private int start;
            private int finish;

            Segment(int start, int finish) {
                this.start = start;
                this.finish = finish;
            }
        }

        List<Segment> aliveSystems = new ArrayList<>();

        for (String tmp : sectorsData) {
            String[] tmpArray = tmp.split(" ");
            int startSegment = Integer.parseInt(tmpArray[0]);
            int endSegment = Integer.parseInt(tmpArray[1]);
            Segment currentSegment = new Segment(startSegment, endSegment);
            aliveSystems.removeIf(checkSegment -> (checkSegment.start <= currentSegment.finish) && (currentSegment.start <= checkSegment.finish));
            aliveSystems.add(currentSegment);
        }


        return aliveSystems.size();
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