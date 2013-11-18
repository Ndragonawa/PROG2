/***
R. Nicholas Eizmendi
eizm4696
CS-203, Fall 2013
Programming Assignment 2
Database class: Handles the data in the program.
***/

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Database implements StandardConstants{

   BufferedReader input;

   Word[] dictionary;
   ArrayList inputList;

   int dictionarySize, dataSize;

   public Database(String filename){
      this.createDictionary(FILE_DICTIONARY);
      this.dataInput(filename);
      
      System.out.format("%nDictionary: %d lines.", dictionarySize);
      System.out.format("%nInput: %d words.", dataSize);
      
      this.sortByNumericValue(dictionary);
      this.anagramGrouper(dictionary);
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
         dictionary = new Word[99171];
      
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
            dictionary[dictionarySize] = new Word(currentLine);
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
         inputList = new ArrayList<Word>();
      
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
            inputList.add(new Word(currentLine));
            dataSize ++;
         }
      }
      //if a bad line is read, exception will be caught
      catch(IOException ioe){
         System.out.println(FAIL + "bad data input at Line" + dataSize + ".");
      }
   }

   public void sortByNumericValue(Word[] list){
      Word[] copy = new Word[list.length];
      mergeSort(list, copy, 0, list.length - 1);
   }
   
   private void mergeSort(Word[] list, Word[] copy, int left, int right){
      if(left < right){
         int center = (left + right)/2;
         mergeSort(list, copy, left, center);
         mergeSort(list, copy, center + 1, right);
         merge(list, copy, left, center + 1, right );
      }
   }
   
   private void merge(Word[] list, Word[] copy, int leftPos, int rightPos, int rightEnd){
      int leftEnd = rightPos - 1;
      int tempPos = leftPos;
      int size = rightEnd - leftPos + 1;
   
      while(leftPos <= leftEnd && rightPos <= rightEnd){
         if(list[leftPos].getValue() <= (list[rightPos].getValue()))
            copy[tempPos++] = list[leftPos++];
         else
            copy[tempPos++] = list[rightPos++];
      }
      while(leftPos <= leftEnd)
         copy[tempPos++] = list[leftPos++];
      while(rightPos <= rightEnd)
         copy[tempPos++] = list[rightPos++];
         
      for(int i = 0; i < size; i ++, rightEnd--)
         list[rightEnd] = copy[rightEnd];
   }
   
   public void anagramGrouper(Word[] sortedList){   
      ArrayList<LinkedList<Word>> anagramGroups = new ArrayList<LinkedList<Word>>();
      LinkedList<Word> anagramGroup;
      int i = 0;
      int wordValueA = 0;
      int wordValueB = 0;
      while(i < dictionarySize){
         wordValueA = sortedList[i].getValue();
         anagramGroup = new LinkedList<Word>();
         while(i < dictionarySize){
            wordValueB = sortedList[i].getValue();
            if(wordValueA == wordValueB){
               anagramGroup.add(sortedList[i]);
               i++;
            }
            else{
               wordValueA = wordValueB;
               break;
            }
         } 
      } 
   }
   
   public void anagramFinder(){
   }
   
   private int binarySearch(ArrayList<LinkedList<Word>> anagramGroups, int wordValue){
      int left = 0;
      int right = anagramGroups.size() - 1;
      while(left <= right){
         int middle = (left + right)/2;
         if(wordValue == anagramGroups.get(middle).getFirst().getValue())
            return(middle);
         else if(wordValue < anagramGroups.get(middle).getFirst().getValue())
            right = middle - 1;
         else
            left = middle + 1;
      }
      return(-1);
   }
}