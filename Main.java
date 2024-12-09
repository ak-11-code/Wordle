import java.io.*;
import java.util.Scanner;
import java.util.Random;


public class Main {
  //Date: March 4, 2024
  //Purpose: Wordle game

  static Scanner input = new Scanner(System.in);

  public static void main(String[] args) throws IOException{

    //Initialize variables
    boolean wordIsCorrect = false;
    int attempt = 1;

    //Call welcome method
    welcomeMethod();

    //Generate a random word and assign it to a variable
    System.out.println("------------------------------");
    String randomWord = generateRandomWord(readFile());//Reads the file and generates a random word from the method call
    
    //Ask the user to guess words, check if it matches the random word
    while(wordIsCorrect == false && attempt < 7){ 
      /*While keeps running until the user guesses the word or the user runs out of attempts
      The boolean value continues to run as long as the user does not guess the word in the given attempts
      */
      
      //Collect user input and assign to variable to be further used in the main method
       String userWord = userInput();

        //Check if the word is found in the wordList library by calling the method
        boolean nameFound = wordIsValid(userWord);

        //If the user's word is found then proceed with checking the letters
            if(nameFound) {
              checkConditions(userWord, randomWord);
                
              //Increase attempt number
            attempt++;
              System.out.println("\nattempt: "+attempt);//Display the number of attempts to the user
            }//if

        else {
           System.out.println("Word not found. Please re-enter: \n");

        }

        //Change boolean expression if userWord is same as randomWord
      //If user gets the word right, the loop ends and the user is displayed with the winning prompt
        if(userWord.equals(randomWord)){
          wordIsCorrect = true;
        }//if
    }//while

    /*Method to welcome the user and inform them about the rules. 
    There is no return values and no parameters*/
    System.out.println("\n=======================================================\n");

    displayResult(wordIsCorrect, attempt, randomWord);//Show results of the user

  }//main
  
  //Method to welcome user
  //Prints all of the rules and instructions for the user to see when called in main
   public static void welcomeMethod() {
      System.out.println("Welcome to the Wordle Game!\n");
      System.out.println("You will have 6 attempts to guess a five letter word\n");
      System.out.println("We hope you enjoy the game!\n");
    }//welcomeMethod


   /*Method that reads from the file of 5 letters words and using its pathway to display it in the console*/
  public static String[]readFile() throws IOException{
    //Returns an array of strings that is the list of words from the wordList File
    BufferedReader readFile;

    readFile = new BufferedReader (new FileReader("wordListforWordle"));//Implements the file with the word list

    String[]wordsList = new String[784];//Places all the words in an array with same length as number of words in the file

    //Reading all of the file
    //Allows user to have access to all words in the file
      for (int index = 0; index<wordsList.length; index++) {
        wordsList[index]=readFile.readLine();
      }

      readFile.close();

      return wordsList;//The final list of words that are called from the file to be used in the program
  }//readFile

   /*Method to generate random word using math.random with a return String value and a 1D array parameter*/
  public static String generateRandomWord(String[] words) {
        int min = 0;
        int max = words.length;
        int range = max - min;
        int randomWordIndex = (int) (Math.random() * range) + min;
        return words[randomWordIndex];
    }//generateRandomWord


  /*Method to state results depending on the number of attempts and if the user guessed the word correctly. There is no return values and had 3 parameters, one is a boolean to check if the word is correct (wordIsCorrect), the other is the number of attempts (attempt), and the last is the random word chosen from the file (randomWord)*/
  public static void displayResult(boolean wordIsCorrect, int attempt, String randomWord){

    //If user has exceeded maximum attempts
    //Allows for total of 6 attempts
    if(attempt >= 7 ){
      System.out.println("You have exceeded the given number of attempts!\n");
    }//if

    //If user has guessed the correct word
    //User prompted with a congratulations message
    if(wordIsCorrect == true){
      System.out.println("\nCongratulations! You have guessed the correct word!\n");
    }//if
      
      //User is shown the correct word and are told they could not guess the word
    else{
      System.out.println("\nYou were not able to guess the correct word :(\n");
      System.out.println("The correct word was: " + randomWord);
    }//else

  }//displayResult

  /*Method to check if the guessed word is part of the library by reading it from the word list file. Method has a boolean return value and a String parameter*/
  public static boolean wordIsValid(String userWord) throws IOException {
      boolean nameFound = false;
      String[] words = readFile();
      for (int nameIndex = 0; nameIndex < words.length; nameIndex++) {
          if (words[nameIndex].equalsIgnoreCase(userWord)) {
              nameFound = true;
              break;
          }//if
      }//for
      return nameFound;
  }//wordIsValid

  /*Method to read the word and check each letter individually, and tell the user the result of each letter. There is no return values and has 2 String parameters, one is the user's guessed word and the other is the generated word from file*/
  public static void checkConditions (String guessedWord, String randomWord){
    if(guessedWord.length() == 5) {
      for(int i = 0; i<guessedWord.length(); i++){

      //If character is in the right position, user is prompted
        if(guessedWord.charAt(i) == randomWord.charAt(i)){
        System.out.println(guessedWord.charAt(i) + " is in the correct position\n");
      }//if

      //If character is in the word but not in the right position, user is prompted
      else if(randomWord.contains(String.valueOf((guessedWord.charAt(i))))){
        System.out.println(guessedWord.charAt(i) + " is in the word but not in the correct position\n");
      }//else if 

    //If character is not in the word, user is prompted
      else{
        System.out.println(guessedWord.charAt(i) + " is not in the word\n");
      }//else
      }//for
    }//if	
    
  }//checkConditions

  //Method to collect user input with a String return value and no parameters
  //Returns the input of the user with no additional spaces and fully lower case
  public static String userInput (){
    System.out.println("------------------------------");
    System.out.println("\nPlease enter a 5 letter word: ");
    String userWord = input.nextLine().trim().toLowerCase();

    return userWord;
  }//userInput

}//class


