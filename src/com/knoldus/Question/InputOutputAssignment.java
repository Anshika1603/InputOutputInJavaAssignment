package com.knoldus.Question;

import java.io.*;
import java.util.Scanner;

public class InputOutputAssignment {
    static String InputFile,OutputFile;
    static Integer numberOfPerson;
    static Scanner Input = new Scanner(System.in);

    public static void main(String[] args) {
        //Taking User Input for files name and number of persons
        System.out.println("Enter Input file name: ");
        InputFile = Input.nextLine();

        System.out.println("Enter Output file name: ");
        OutputFile = Input.nextLine();

        System.out.println("Enter the number of Persons: ");
        numberOfPerson=Input.nextInt();

        //Creating input and output files
        File inputfile = new File(InputFile);
        File outputfile = new File(OutputFile);
        try {
            inputfile.createNewFile();

            //Checking if output file already exist and asking user for overriding permission if it does exist.
            if(outputfile.exists())
            {
                System.out.println("Output File already exists. Do you want to Override? (y/n)");
                Character permission=Input.next().charAt(0);
                if(permission=='y' || permission=='Y')
                {
                    outputfile.createNewFile();
                } else if (permission=='n' || permission=='N')
                {
                    // Terminating the program with a message if user denies to override
                    System.out.println("Not Overriding the Output File. The program will Terminate!");
                    System.exit(0);
                }
                else
                {
                    System.out.println("Wrong Input! Please try again.");
                    System.exit(0);
                }
            }
            else
            {
                outputfile.createNewFile();
            }

        }
        catch(IOException exception)
        {
            System.out.println("An error occurred. Try Again!");
        }
        //Calling methods to perform operations on Input and Output files
        InputFileWrite();
        OutputFileWrite();
    }

    private static void InputFileWrite(){
        try
        {
            //Writing the Name and Age of persons in Input File using BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(InputFile));
            for (int indexOfPerson = 1; indexOfPerson <= numberOfPerson; indexOfPerson++)
            {
                System.out.println("Enter the name of Person "+indexOfPerson);
                Input.nextLine();
                String Name = Input.nextLine();
                System.out.println("Enter the age of Person "+indexOfPerson);
                Integer age = Input.nextInt();
                bufferedWriter.write(Name + ", " + age + "\n");
            }
            bufferedWriter.close();
        }
        catch(IOException exception)
        {
            System.out.println("IO Exception occurred");
        }
    }

    private static void OutputFileWrite(){
        try {
            FileReader inputFileReader=new FileReader(InputFile);
            BufferedReader bufferedReader= new BufferedReader(inputFileReader);
            FileWriter outputFileWriter=new FileWriter(OutputFile);
            String line;
            int sumOfAge = 0;
            //Reading Name and Age from Input File and writing it into Output File
            while ((line=bufferedReader.readLine()) != null)
            {
                String parts[] = line.split(","); //Splitting Input File line text to get Name and Age
                String name = parts[0].trim();
                Integer age = Integer.parseInt(parts[1].trim());
                sumOfAge += age;
                // Writing the Name and Age in Output File
                outputFileWriter.write(name + "(" + age + ")\n");
            }
            //Calculating Average Age and displaying it on Console as well as the Output File
            Double averageAge= (double) (sumOfAge/numberOfPerson);
            System.out.println("Average age of Persons is: "+averageAge);
            outputFileWriter.write("Average age of Persons is: "+averageAge);
            outputFileWriter.close();
            inputFileReader.close();
        }
        catch (IOException exception)
        {
            System.out.println("IO Exception Occurred");
        }
    }
}
