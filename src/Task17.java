import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Task17 {

    public static final int QUEUE_SIZE = 10;

    public static void main(String[] args) {
        List<String> inputData = readData();

        List<String> outputData = new ArrayList<>();
        outputData.add(pianicaGame(inputData.get(0).split(" "), inputData.get(1).split(" ")));

        writeData(outputData);
    }

    public static String pianicaGame(String[] cardsFirstPlayer, String[] cardsSecondPlayer) {

        Queue<Integer> playerOneCards = new ArrayDeque<>();
        for (int i = 0; i < cardsFirstPlayer.length; i++)
            playerOneCards.add(Integer.parseInt(cardsFirstPlayer[i]));

        Queue<Integer> playerTwoCards = new ArrayDeque<>();
        for (int i = 0; i < cardsSecondPlayer.length; i++)
            playerTwoCards.add(Integer.parseInt(cardsSecondPlayer[i]));

        int numberOfTurns = 0;

        while (numberOfTurns < 1000000) {

            if (playerOneCards.size() == 0)
                return "second " + numberOfTurns;

            if (playerTwoCards.size() == 0)
                return "first " + numberOfTurns;

            int firstPlayerCard = playerOneCards.poll();
            int secondPlayerCard = playerTwoCards.poll();

            if ( ((firstPlayerCard == 0) || (firstPlayerCard == 9)) &&
                    ((secondPlayerCard == 0) || (secondPlayerCard == 9)) ) {
                int tmpMin = Math.min(firstPlayerCard, secondPlayerCard);
                if (tmpMin == firstPlayerCard) {
                    playerOneCards.add(firstPlayerCard);
                    playerOneCards.add(secondPlayerCard);
                } else {
                    playerTwoCards.add(firstPlayerCard);
                    playerTwoCards.add(secondPlayerCard);
                }
            } else if (firstPlayerCard > secondPlayerCard) {
                playerOneCards.add(firstPlayerCard);
                playerOneCards.add(secondPlayerCard);
            } else {
                playerTwoCards.add(firstPlayerCard);
                playerTwoCards.add(secondPlayerCard);
            }

            numberOfTurns++;
        }

        return "botva";
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