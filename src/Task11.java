import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Task11 {

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        outputData.add(stackWorks(inputData));

        writeData(outputData);
    }

    public static String stackWorks(List<String> commands) {
        StringBuilder result = new StringBuilder();
        List<String> stackSimulator = new ArrayList<>();

        for (String currentCommand : commands) {
            String[] data = currentCommand.split(" ");
            switch (data[0]) {
                case "push":
                    stackSimulator.add(data[1]);
                    result.append("ok\n");
                    break;
                case "pop":
                    if (stackSimulator.size() != 0) {
                        result.append(stackSimulator.get(stackSimulator.size() - 1));
                        result.append("\n");
                        stackSimulator.remove(stackSimulator.size() - 1);
                    } else result.append("error\n");
                    break;
                case "back":
                    if (stackSimulator.size() != 0) {
                        result.append(stackSimulator.get(stackSimulator.size() - 1));
                        result.append("\n");
                    } else result.append("error\n");
                    break;
                case "size":
                    result.append(stackSimulator.size());
                    result.append("\n");
                    break;
                case "clear":
                    stackSimulator.clear();
                    result.append("ok\n");
                    break;
                case "exit":
                    result.append("bye");
                    return result.toString();
            }
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
