import java.io.*;
import java.util.*;

public class Task19 {

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        List<String> inputData = readData();
        int numberOfCommands = Integer.parseInt(inputData.get(0));
        List<String> outputData = new ArrayList<>();

        outputData.add(controlCustomHeap(numberOfCommands, inputData.subList(1, inputData.size())));
        writeData(outputData);
    }

    private static String controlCustomHeap(int numberOfCommands, List<String> commands) {
        StringBuilder result = new StringBuilder();
        Node root = new Node(Integer.parseInt(commands.get(0).split(" ")[1]));
        for (int i = 1; i < numberOfCommands; i++) {
            String[] tmp = commands.get(i).split(" ");
            if (tmp[0].equals("0"))
                insert(Integer.parseInt(tmp[1]), root);
                else if (tmp[0].equals("1"))
                    result.append(extractVal(root, root.val)).append("\n");
        }

        return result.toString();
    }

    private static int extractVal(Node root, int currentMax) {

        return currentMax;
    }

    private static void insert(int inputVal, Node root) {
        if (inputVal <= root.val) {
            if (root.left == null) {
                root.left = new Node(inputVal);
            } else insert(inputVal, root.left);
        } else {
            if (root.right == null) {
                root.right = new Node(inputVal);
            } else insert(inputVal, root.right);
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
