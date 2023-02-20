import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Task13 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        outputData.add(countSymbols(inputData.get(0)));

        writeData(outputData);
    }

    public static String countSymbols(String text) {
        text = text.replace(" ", "");
        Stack<String> helper = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            char tmp = text.charAt(i);
            if ( (tmp == '+') || (tmp == '-') || (tmp == '*') ) {
                int operandTwo = Integer.parseInt(helper.pop());
                int operandOne = Integer.parseInt(helper.pop());
                if (tmp == '+') {
                    int val = operandOne + operandTwo;
                    helper.push(String.valueOf(val));
                } else if (tmp == '-') {
                    int val = operandOne - operandTwo;
                    helper.push(String.valueOf(val));
                } else {
                    int val = operandOne * operandTwo;
                    helper.push(String.valueOf(val));
                }
            } else {
                helper.push(String.valueOf(tmp));
            }
        }

        if (helper.size() == 1)
            return String.valueOf(helper.pop());
        else return "null";
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
