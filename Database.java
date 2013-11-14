/***
R. Nicholas Eizmendi
eizm4696
CS-203, Fall 2013
Programming Assignment 2
Database class: Handles the data in the program.
***/

import java.io.*;
import java.util.ArrayList;

public class Database implements StandardConstants{

   BufferedReader input;

   ArrayList dictionary, inputList;

   int dictionarySize, dataSize;

   public Database(String filename){
      this.createDictionary(FILE_DICTIONARY);
      this.dataInput(filename);
      
      System.out.format("%nDictionary: %d lines.", dictionarySize);
      System.out.format("%nInput: %d words.", dataSize);
   }

/***
Method:  createDictionary
Purpose: Takes data from a dictionary file and creates a dictionary.
Parameters:
   String filename:  Directory of the dictionary file.
Returns:
   void
***/    
   private void createDictionary(String dictionaryFile){
      //Dictionary input; if there is no file the exception will be caught.
      try{
         input = new BufferedReader(new FileReader (dictionaryFile));
         dictionary = new ArrayList<String>();
      
         System.out.format("%nLoaded \"%s\" as dictionary.",dictionaryFile);
      }
      catch(FileNotFoundException fnf2){
         System.out.println(FAIL + "no dictionary file found.");
         System.exit(0);
      }
      
      //declares the String for line reading.
      String currentLine;
      //Data input line by line
      try{
         while((currentLine = input.readLine()) != null) {
            dictionary.add(currentLine);
            dictionarySize ++;
         }
      }
      //if a bad line is read, exception will be caught
      catch(IOException ioe){
         System.out.println(FAIL + "bad dictionary input at Line" + dataSize + ".");
      }
   }

/***
Method:  dataInput
Purpose: Takes data from a file and enters it into the list.
Parameters:
   String filename:  Directory of the dictionary file.
Returns:
   void
***/   
   private void dataInput(String inputFile){
   
      //Data input; if there is no file the exception will be caught.
      try{
         input = new BufferedReader(new FileReader (inputFile));
         inputList = new ArrayList<String>();
      
         System.out.format("%nLoaded \"%s\" as input.",inputFile);
      }
      catch(FileNotFoundException fnf2){
         System.out.println(FAIL + "no input file found.");
         System.exit(0);
      }
      
      //declares the String for line reading.
      String currentLine;
      //Data input line by line
      try{
         while((currentLine = input.readLine()) != null) {
            dictionary.add(currentLine);
            dataSize ++;
         }
      }
      //if a bad line is read, exception will be caught
      catch(IOException ioe){
         System.out.println(FAIL + "bad data input at Line" + dataSize + ".");
      }
   }
}