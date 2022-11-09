package com.ejerciciosjava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilesExercise {
    public static Scanner scan = new Scanner(System.in);
    public static List<String> validInputs = new ArrayList<>(Arrays.asList("CREATE","WRITE","READ","DELETE","EXIT"));
    public static Path path;
    public static void main(String[] args)  {

        //DONE Read input from user
        //DONE Validate input is not null
        //DONE Ask to write a not null input
        //DONE Try catch writing to a file
        //TODO Recursion / Recursive methods
        //DONE Validate fileName is not null
        //DONE Try catch creating a file
        //TODO Log and error if something happens

        String answer = showOptions();

        while(validInputs.contains(answer)) {
            if (answer.equals(validInputs.get(0)))  {
                //CREATE
                String fileName = getValidatedFileName();
                createFile(fileName);

            } else if (answer.equals(validInputs.get(1))) {
                //WRITE
                //Ask each file
                if(fileExists()) {
                    writeFile();
                }
                else{
                    System.out.println("File not found. Returning to home.");
                }
            } else if (answer.equals(validInputs.get(2))) {
                //READ
                if (fileExists()){
                    readFile();
                }
                else{
                    System.out.println("File not found. Returning to home.");
                }

            } else if (answer.equals(validInputs.get(3))) {
                //DELETE
                if (fileExists()){
                    deleteFile();
                }
                else {
                    System.out.println("File not found. Returning to home.");
                }

            } else if (answer.equals(validInputs.get(4))) {
                //EXIT
                System.out.println("Closing app. Bye.");
                break;
            }
            answer = showOptions();
        }
    }
    public static String showOptions(){
        System.out.println("Choose CREATE, WRITE, READ, DELETE, EXIT.");
        String answer = scan.nextLine();
        while(!validInputs.contains(answer)){
            System.out.println("Not valid. Retry.");
            answer = scan.nextLine();
        }
        return answer;
    }
    public static String getValidatedFileName(){
        String SUFFIX = ".txt";
        System.out.println("Name your file.");
        String fileName = scan.nextLine();
        while (fileName.isEmpty()) {
            System.out.println("File name cannot be empty. Retry.");
            //Validate for special characters
            fileName = scan.nextLine();
        }
        if (!fileName.endsWith(SUFFIX)){
            //List of valid suffix == valid file types
            fileName = fileName+SUFFIX;
        }
        return fileName;
    }
    public static void createFile(String fileName){
        path = Paths.get(fileName);
        try{
            while ((Files.exists(path))){
                //If file exists: ask to choose a diff name
                System.out.println("File already exists.");
                fileName = getValidatedFileName();
                //Change path to new file name
                path = Paths.get(fileName);
            }
                //This happens only when file does not exist == create a new file
            System.out.println("Creating file " + fileName);
            Files.createFile(path);
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("File " + fileName + " created successfully.");
    }
    public static void writeFile(){
        List<String> inputLines = new ArrayList<String>();
        System.out.println("Write the input for your file. Type exit when you finish.");
        String input = scan.nextLine();
        while(!input.equals("exit")){
            if (input != null && input.length() > 0){
                inputLines.add(input);
            }
            else {
                System.out.println("Write a not null input.");
            }
            input = scan.nextLine();
        }
        System.out.println("writing to file " + path.getFileName());
        try{
            Files.write(path,inputLines);
        }catch (IOException e){
            System.out.println(e);
        }
        System.out.println("Input written successfully");
    }
    public static void readFile(){
            try {
                List<String> lines = Files.readAllLines(path);
                if (lines.isEmpty()){
                    System.out.println("File is empty.");
                }
                else {
                    for (String line : lines){
                        System.out.println(line);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
    }
    public static void deleteFile(){
        try {
            Files.delete(path);
            System.out.println("File " + path.getFileName() + " deleted successfully.");
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public static boolean fileExists(){
        if (path == null){
            return false;
        }
        return Files.exists(path);
    }
}
