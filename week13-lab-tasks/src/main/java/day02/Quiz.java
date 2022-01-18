package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Quiz {
    private char[] goodAnswer = new char[5];
    private Map<String, char[]> answers = new TreeMap<>();

    public Quiz(String fileName) {
        readFromFile(fileName);
    }

    private void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datas = line.split(" ");
                if (datas.length == 1) {
                    for (int i = 0; i < 5; i++) {
                        goodAnswer[i] = datas[0].charAt(i);
                    }
                } else {
                    if (answers.containsKey(datas[0])) {
                        char[] actualAnswer = answers.get(datas[0]);
                        boolean end = false;
                        for (int i = 1; i < 5; i++) {
                            if (!end && actualAnswer[i] == '!') {
                                actualAnswer[i] = datas[1].charAt(0);
                                answers.put(datas[0], actualAnswer);
                                end = true;
                            }
                        }
                    } else {
                        char[] actualAnswer = new char[5];
                        for (int i = 1; i < 5; i++) {
                            actualAnswer[i] = '!';
                        }
                        actualAnswer[0] = datas[1].charAt(0);
                        answers.put(datas[0], actualAnswer);
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read file!", ioe);
        }
    }

    public boolean isCorrectAnswerById(String Id, int numberOfQuestion) {
        char[] actual = answers.get(Id);
        return actual[numberOfQuestion] == goodAnswer[numberOfQuestion];
    }

    public String whoWasTheBest() {
        int maxPoint = 0;
        String bestId = "";

        for (Map.Entry<String, char[]> e : answers.entrySet()) {
            int actualPoint = 0;
            for (int i = 0; i < 5; i++) {
                char actualValue = e.getValue()[i];
                if (actualValue != '!') {
                    if (actualValue == goodAnswer[i]) {
                        actualPoint += i + 1;
                    } else if (actualValue != 'X') {
                        actualPoint -= 2;
                    }
                }
            }
            if (actualPoint > maxPoint) {
                maxPoint = actualPoint;
                bestId = e.getKey();
            }
        }
        return bestId;
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz("src/main/resources/result.txt");
        System.out.println(quiz.isCorrectAnswerById("AB123", 0));
        System.out.println(quiz.isCorrectAnswerById("AH2", 0));
        System.out.println(quiz.whoWasTheBest());
    }
}
