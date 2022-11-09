package com.ejerciciosjava;

/*
    Develop a program that takes a list of N number of names from user input,
    save them and give the user three different options to process the list:
    a) Order the names by alphabetical order
    b) Print only the names that start with a given letter
    c) Order the names based on the number of letters (from the longest to the shortest or vice versa)
    d) ?
*/

/*
TODO
DONE Names list -> user input
DONE Where to save the names?
DONE User should be able to choose one option
DONE See how to order names -> function(? --> Collections
DONE How to look for a name based start letter --> .startsWith()
DONE How to count letters per name and order them --> Collections
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class NamesExerciseJavierWithMethods {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> names = introducirNombres();

        while (true) {
            //options
            System.out.println("Your options are: \n a: order names by first letter \n b: search names by its initial \n c: order names by length");

            char optionChosen = scan.next().charAt(0);

            System.out.println("You chose option " + optionChosen);

            elegirOpciones(names, optionChosen);

            System.out.println("Would you like to exit? y/n");
            char exit = scan.next().charAt(0);
            if (exit == 'y'){
                break;
            }
        }
    }
    public static ArrayList<String> introducirNombres(){
        ArrayList<String> names = new ArrayList<>();
        //input names
        System.out.println("Write 5 names:");
        for (int i = 0; i < 5; i++) {
            //Con un loop podemos escanear mas nombres
            System.out.println("Name " + (i + 1));
            String name = scan.nextLine();
            names.add(name);
        }
        // Names in arraylist are printed
        System.out.println(names);
        return names;
    }
    public static void filtrarInicial(ArrayList<String> names){
        //case b
        System.out.println("Type a letter: ");
        String initial = scan.next();
        //char initialC = scan.next().charAt(0);
        //n.startsWith(String.valueOf(initialC)))
        //String n = "empty";
        boolean wordExist = false;
        for (int i = 0; i < names.size(); i++) {
            String n = names.get(i);
            if (n.startsWith(initial)){
                System.out.println(n);
            }
        }
    }

    public static void elegirOpciones(ArrayList<String> names, char optionChosen){
        switch (optionChosen) {
            case 'a':
                //case a
                Collections.sort(names);
                System.out.println(names);
                break;
            case 'b':

                filtrarInicial(names);

                break;
            case 'c':
                Collections.sort(names, Comparator.comparing(String::length));
                //Podemos utilizar arraylist.forEach() para aplicar el () a cada valor por separado, es decir, se muestra fuera del arraylist.
                names.forEach(System.out::println);
                break;
            default:
                System.out.println("Option not valid");
        }
    }
}

