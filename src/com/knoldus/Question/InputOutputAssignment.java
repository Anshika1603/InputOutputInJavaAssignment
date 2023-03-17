package com.knoldus.Question;

import java.io.*;
import java.util.Scanner;

public class InputOutputAssignment {
    static String InputFile,OutputFile;
    static Integer numberOfPerson;
    static Scanner Input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter Input file name: ");
        InputFile = Input.nextLine();

        System.out.println("Enter Output file name: ");
        OutputFile = Input.nextLine();

        System.out.println("Enter the number of Persons: ");
        numberOfPerson=Input.nextInt();

        File inputfile = new File(InputFile);
        File outputfile = new File(OutputFile);
        try {
            inputfile.createNewFile();

            if(outputfile.exists())
            {
                System.out.println("Output File already exists. Do you want to Override? (y/n)");
                String permission=Input.next();
                if(permission=="y" || permission=="Y")
                {
                    outputfile.createNewFile();
                }
                else if(permission=="n" || permission=="N")
                {
                    System.out.println("Not Overriding the File");
                }
            }
            else
            {
                outputfile.createNewFile();
            }

        }
        catch(IOException exception){
            System.out.println("An error occurred. Try Again!");
        }
        InputFileWrite();
        OutputFileWrite();
    }

    private static void InputFileWrite(){
        try
        {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(InputFile));
            for (int indexOfPerson = 0; indexOfPerson < numberOfPerson; indexOfPerson++) {
                System.out.println("Enter the name of Person ");
                Input.nextLine();
                String Name = Input.nextLine();
                System.out.println("Enter the age of Person ");
                Integer age = Input.nextInt();
                bufferedWriter.write(Name + ", " + age + "\n");

            }
            bufferedWriter.close();
        }catch(IOException exception)
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
            while ((line=bufferedReader.readLine()) != null)
            {

                String parts[] = line.split(",");
                String name = parts[0].trim();
                Integer age = Integer.parseInt(parts[1].trim());
                sumOfAge += age;
                outputFileWriter.write(name + "(" + age + ")\n");
            }
            Double averageAge= (double) (sumOfAge/numberOfPerson);
            System.out.println("Average age of Persons is: "+averageAge);
            outputFileWriter.write("The sum of ages is: "+sumOfAge+"\n");
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
