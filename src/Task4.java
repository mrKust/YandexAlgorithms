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

        int numberOfRows = (int)(Math.ceil((double)numberOfStudents / 2));

        int[][] classMap = new int[numberOfRows + 1][2];
        int currentVariant = 1;
        int studentsWorks = numberOfStudents;
        for (int i = 1; i <= numberOfRows; i++) {
            for (int k = 1; k >= 0; k--) {
                classMap[i][k] = currentVariant;
                currentVariant++;
                if (currentVariant > numberOfVariants)
                    currentVariant = 1;
                studentsWorks--;
                if (studentsWorks == 0)
                    break;
            }
        }
        int petyaVariant = 0 ;
        if (petyaSide == 1) {
            petyaVariant = classMap[petyaRow][1];
        } else  petyaVariant = classMap[petyaRow][0];

        int vasyaRow = -1;
        int vasyaSide = -1;

        for (int i = 1; i <= numberOfRows; i++) {
            for (int k = 1; k >= 0; k--) {
                if (petyaRow == i)
                    continue;
                int tmpVar = classMap[i][k];
                if ( (tmpVar == petyaVariant) && (vasyaRow != -1) ) {
                    int tmpNumberRows = Math.abs(petyaRow - i);
                    int currentMinNumberRows = Math.abs(petyaRow - vasyaRow);
                    if (tmpNumberRows <= currentMinNumberRows) {
                        vasyaRow = i;
                        if (k == 1)
                            vasyaSide = 1;
                        else vasyaSide = 2;
                    }
                } else if (tmpVar == petyaVariant) {
                    vasyaRow = i;
                    if (k == 1)
                        vasyaSide = 1;
                    else vasyaSide = 2;
                }
            }
        }

        if ( (vasyaRow == -1) && (vasyaSide == -1) )
            return "-1";
        else return vasyaRow + " " + vasyaSide;
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