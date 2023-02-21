import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task14 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        int numberOfWagons = Integer.parseInt(inputData.get(0));
        List<Integer> orderOfWagons = new ArrayList<>();
        String[] tmp = inputData.get(1).split(" ");
        for (int i = 0; i < tmp.length; i++) {
            orderOfWagons.add(Integer.parseInt(tmp[i]));
        }

        outputData.add(controlTrain(numberOfWagons, orderOfWagons));
        writeData(outputData);
    }

    public static String controlTrain(int numberOfWagons, List<Integer> orderOfWagons) {

        Stack<Integer> helper = new Stack<>();
        List<Integer> sortedWagons = new ArrayList<>();

        while (sortedWagons.size() != numberOfWagons) {

            if (orderOfWagons.size() == 0) {
                while (helper.size() != 0)
                    sortedWagons.add(helper.pop());
                continue;
            }

            int currentWagonFromOne = orderOfWagons.get(0);
            orderOfWagons.remove(0);

            if (helper.size() == 0) {
                helper.push(currentWagonFromOne);
                continue;
            }

            int checkValue = helper.peek();
            while (checkValue < currentWagonFromOne) {
                sortedWagons.add(helper.pop());
                if (helper.size() == 0)
                    break;
                checkValue = helper.peek();
            }
            helper.add(currentWagonFromOne);
        }

        boolean flag = true;
        for (int i = 0; i < sortedWagons.size() - 1; i++) {
            int val1 = sortedWagons.get(i);
            int val2 = sortedWagons.get(i + 1);
            if ( (val2 - val1) != 1)
                flag = false;
        }

        if (flag)
            return "YES";
        else return "NO";
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
