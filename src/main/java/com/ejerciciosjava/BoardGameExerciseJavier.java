package com.ejerciciosjava;
/*
This is a simple one player board game that allows the player to move box by box along a path.
To move the user needs to answer correctly a set of questions.
For each correct answer the player will be able to move from one box to another and,
depending on the difficulty of the game, if the player answers wrong they will be moved a step back.\

There are three play modes: easy, intermediate and hard.

On the easy level the player will move one step forward for each correct answer and will stay in place in case of wrong answers.
Player wins if reach the end of the path before the questions run out.

On the intermediate level the player will move one step forward for each correct answer and will take one step back for each wrong answer.
Player wins if reach the end of the path before the questions run out.

On the hard level the player will move one step forward for each correct answer and will take one step back for each wrong answer.
For each correct answer player will sum an X amount of points depending on the question.
Player wins if reach the end of the path before the questions run out or if they score sums up Z numbers of points.

The path will have 10 boxes.
There should be at least 20 questions available for the player.
Each question should have a score from 1 to 10.

*/

/*
DONE create 20 questions
DONE where to save the questions
DONE where to save answer or condition to each Q
DONE player selects a difficulty --> switch?
DONE how to track player's progress --> arraylist/counter?
DONE point system?
DONE when/how will the player win
DONE apply methods
 */

import java.util.*;

public class BoardGameExerciseJavier {
    public static Scanner scan = new Scanner(System.in);
    public static String q = "";
    public static String a = "";
    public static String answer = "";
    public static int countAnswers = 0;
    public static StringBuilder boardPath = new StringBuilder(" _________");

    public static void main(String[] args) {

        ArrayList<String> questions = askQuestions();
        ArrayList<String> answers = correctAnswers();
        ArrayList<Integer> points = answerScore();
        List<Integer> randomIndex = indexArray();

        System.out.println("Choose a game difficulty: \n a: Easy \n b: Intermediate \n c: Hard");
        char difficulty = scan.nextLine().charAt(0);
        System.out.println("You chose option " + difficulty);

        selectDifficulty(difficulty, questions, answers, points, randomIndex);

        if (countAnswers < 10) {
            System.out.println("YOU LOST!!");
        }

    }

    /*    StringBuilder boardPath = new StringBuilder("_ _ _ _ _ _ _ _ _ _");
        //Immutable String
        //String boardPath = "_ _ _ _ _ _ _ _ _ _";
        boardPath.setCharAt(4,'P');
        System.out.println(boardPath);
        boardPath.setCharAt(4, '_');
        boardPath.setCharAt(6, 'P');
        System.out.println(boardPath);
     */

    public static void selectDifficulty(char difficulty, ArrayList<String> questions, ArrayList<String> answers, ArrayList<Integer> points, List<Integer> randomIndex) {
        switch (difficulty) {
            case 'a':
                easyGame(questions, answers, randomIndex);
                break;
            case 'b':
                intermediateGame(questions, answers, randomIndex);
                break;
            case 'c':
                hardGame(questions, answers, points, randomIndex);
                break;
            default:
                System.out.println("Option not valid.");
        }
    }

    public static void easyGame(ArrayList<String> questions, ArrayList<String> answers, List<Integer> randomIndex) {
        System.out.println("Answer 10 questions correctly to win the game.");
        for (int i = 0; i < questions.size(); i++) {
            q = questions.get(randomIndex.get(i));
            a = answers.get(randomIndex.get(i));
            System.out.println(q);
            answer = scan.nextLine();

            if (a.equals(answer)) {
                System.out.println("Correct!!");
                countAnswers++;
            } else {
                System.out.println("u dumb");
            }
            if (countAnswers != 0) {
                boardPath.setCharAt(countAnswers - 1, 'P');
                System.out.println(boardPath);
                boardPath.setCharAt(countAnswers - 1, '_');
            } else {
                System.out.println("__________");
            }
            if (countAnswers == 10) {
                System.out.println("CONGRATULATIONS, YOU WON!!");
                break;
            }
        }
    }

    public static void intermediateGame(ArrayList<String> questions, ArrayList<String> answers, List<Integer> randomIndex) {
        System.out.println("Reach the end of the board to win the game.");
        for (int i = 0; i < questions.size(); i++) {
            q = questions.get(randomIndex.get(i));
            a = answers.get(randomIndex.get(i));

            System.out.println(q);
            answer = scan.nextLine();

            if (a.equals(answer)) {
                System.out.println("Correct!!");
                countAnswers++;
            } else {
                System.out.println("u dumb");
                countAnswers--;
            }
            if (countAnswers < 0) {
                countAnswers++;
            }
            if (countAnswers != 0) {
                boardPath.setCharAt(countAnswers - 1, 'P');
                System.out.println(boardPath);
                boardPath.setCharAt(countAnswers - 1, '_');
            } else {
                System.out.println("__________");
            }
            if (countAnswers == 10) {
                System.out.println("CONGRATULATIONS, YOU WON!!");
                break;
            }
        }
    }

    public static void hardGame(ArrayList<String> questions, ArrayList<String> answers, ArrayList<Integer> points, List<Integer> randomIndex) {
        System.out.println("Reach the end of the board or get a score of 50 to win the game.");

        Integer p = 0;
        Integer totalPoints = 0;

        for (int i = 0; i < questions.size(); i++) {
            q = questions.get(randomIndex.get(i));
            a = answers.get(randomIndex.get(i));
            p = points.get(randomIndex.get(i));

            System.out.println(q);
            answer = scan.nextLine();

            if (a.equals(answer)) {
                System.out.println("Correct!!");
                totalPoints = p + totalPoints;
                countAnswers++;
            } else {
                System.out.println("u dumb");
                countAnswers--;
            }
            if (countAnswers < 0) {
                countAnswers++;
            }
            if (countAnswers != 0) {
                boardPath.setCharAt(countAnswers - 1, 'P');
                System.out.println(boardPath);
                boardPath.setCharAt(countAnswers - 1, '_');
            } else {
                System.out.println("__________");
            }
            if (countAnswers == 10) {
                System.out.println("CONGRATULATIONS, YOU WON!!");
                break;
            }
            System.out.println("Your score is:");
            System.out.println(totalPoints);
            if (totalPoints > 49) {
                System.out.println("CONGRATULATIONS, YOU WON!!");
                countAnswers = 10;
                break;
            }
        }
    }

    public static ArrayList<String> askQuestions() {
        ArrayList<String> questions = new ArrayList<>();

        questions.add("What is the rarest M&M color?");
        questions.add("Which country consumes the most chocolate per capita?");
        questions.add("What was the first soft drink in space?");
        questions.add("What is the most consumed manufactured drink in the world?");
        questions.add("Which is the only edible food that never goes bad?");
        questions.add("Who has won more tennis grand slam titles, Venus Williams or Serena Williams?");
        questions.add("What sport is dubbed the king of sports?");
        questions.add("The Olympics are held every how many years?");
        questions.add("In which winter sport are the terms stale fish and mule kick used?");
        questions.add("Who was the first British player to win league titles in four countries?");
        questions.add("What is the name of the skyscraper in Die Hard?");
        questions.add("Joaquin Phoenix received his first Oscar nomination for playing Roman emperor Commodus in what 2000 Oscar-winning epic?");
        questions.add("How many suns does Luke\'s home planet of Tatooine have in Star Wars?");
        questions.add("How many points do you get when you catch a golden snitch?");
        questions.add("Who is the first and only woman of color to win the Oscar for Best Actress?");
        questions.add("What phenomena keep the planets in orbit around the sun?");
        questions.add("What is the largest organ in the human body?");
        questions.add("How many states of matter are there?");
        questions.add("True or false, stomach acid is strong enough to dissolve stainless steel.");
        questions.add("What is the bone diseases that literally translates to ‘porous bones’?");
        return questions;
    }

    public static ArrayList<String> correctAnswers() {
        ArrayList<String> answers = new ArrayList<>();

        answers.add("Brown");
        answers.add("Switzerland");
        answers.add("Coca Cola");
        answers.add("Tea");
        answers.add("Honey");
        answers.add("Serena Williams");
        answers.add("Soccer");
        answers.add("Four");
        answers.add("Snowboarding");
        answers.add("David Beckham");
        answers.add("Nakatomi Plaza");
        answers.add("Gladiator");
        answers.add("Two");
        answers.add("150");
        answers.add("Halle Berry");
        answers.add("Gravity");
        answers.add("Skin");
        answers.add("Four");
        answers.add("True");
        answers.add("Osteoporosis");

        return answers;
    }

    public static ArrayList<Integer> answerScore() {
        ArrayList<Integer> points = new ArrayList<>();

        points.add(8);
        points.add(2);
        points.add(6);
        points.add(10);
        points.add(4);
        points.add(2);
        points.add(6);
        points.add(4);
        points.add(10);
        points.add(8);
        points.add(10);
        points.add(4);
        points.add(6);
        points.add(2);
        points.add(8);
        points.add(4);
        points.add(6);
        points.add(4);
        points.add(4);
        points.add(4);
        return points;
    }

    public static List<Integer> indexArray() {
        List<Integer> indexArray = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19);
        Collections.shuffle(indexArray);
        return indexArray;
    }

}