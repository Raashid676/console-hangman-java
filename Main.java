import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main (String[] args){

        // JAVA HANGMAN GAME

        String filepath = "Words.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine()) != null){
                words.add(line.trim());
            }
        }

        catch(FileNotFoundException e){
            System.out.println("Could not found file");

        }
        catch(IOException e){
            System.out.println("Something went wrong!");
        }
        Random random = new  Random();  
        String word = words.get(random.nextInt(words.size()));

        Scanner sc = new Scanner(System.in);
        ArrayList<Character> wordState = new ArrayList<>();
        int wrongGuesses = 0;

        for (int i = 0; i < word.length(); i++){
            wordState.add('_');
        }

        System.out.println("________________________");
        System.out.println("Welcome to Java Hangman!");
        System.out.println("________________________");

        while(wrongGuesses < 6){
            System.out.print(getHangmanArt(wrongGuesses));

            System.out.print("Word: ");
            for(char c : wordState){
                System.out.print(c + " ");
            }
            System.out.println();

            System.out.print("GUESS A LETTER: ");
            char GUESS = sc.next().toLowerCase().charAt(0);

        
            if(word.toLowerCase().indexOf(GUESS) >= 0){
                System.out.println("CORRECT GUESS!");
                for(int i = 0; i < word.length(); i++){
                    if(Character.toLowerCase(word.charAt(i)) == GUESS){
                        wordState.set(i, GUESS);
                    }
                }

                if(!wordState.contains('_')){
                    System.out.print(getHangmanArt(wrongGuesses));
                    System.out.println("YOU WIN!");
                    System.out.println("The word was: " + word);
                    break;
                }

            } else {
                wrongGuesses++;
                System.out.println("WRONG GUESS!");
            }
        }

        if(wrongGuesses >= 6){
            System.out.print(getHangmanArt(wrongGuesses));
            System.out.println("GAME OVER!");
            System.out.println("The word was: " + word);
        }

        sc.close();
    }

    static String getHangmanArt(int wrongGuesses){
        return switch(wrongGuesses){
            case 0 -> """
                    
                    
                    
                    """;

            case 1 -> """
                    
                      o
                    
                    """;

            case 2 -> """
                    
                      o
                      |
                    
                    """;

            case 3 -> """
                    
                      o
                     /|
                    
                    """;

            case 4 -> """
                    
                      o
                     /|\\
                    
                    """;

            case 5 -> """
                    
                      o
                     /|\\
                     /
                    """;

            case 6 -> """
                    
                      o
                     /|\\
                     / \\
                    """;

            default -> "";
        };
    }
}
