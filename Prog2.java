/***
R. Nicholas Eizmendi
eizm4696
CS-203, Fall 2013
Programming Assignment 2
Prog2 class: Contains the main of the program.
***/
import java.io.*;

public class Prog2 implements StandardConstants{
   public static void main(String[]args){
   
      Database af;
      
      //If there is exactly one argument the program will run.
      if(args.length == 1){
         System.out.println("ANAGRAM FINDER -- REGMODE");
         af = new Database(args[0]);
      }
      
      //If there is none or more than one, the program will not run.
      else{
         System.out.println(FAIL + "bad arguments.");
         System.exit(0);
      }
   }
}