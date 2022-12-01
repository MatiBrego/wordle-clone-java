package wordleclone.application.config;

import wordleclone.application.components.Grid;
import wordleclone.application.components.GuessStatus;
import wordleclone.application.components.Line;
import wordleclone.application.components.Square;

public class GameLauncher {
    private Grid grid;

    private String currentWord;

    public void newGame(int guessCapacity, String word){
        this.grid = new Grid(guessCapacity, word.length());
        this.currentWord = word;
    }

    public void addLetter(char c){
        Line currentLine = grid.getCurrentLine();

        if(!currentLine.isFull()) {
            Square square = currentLine.getCurrentSquare();
            square.insertLetter(c);
            currentLine.moveToNextSquare();
        }
    }

    public boolean checkWord(){
        Line currentLine = grid.getCurrentLine();
        boolean correct = true;
        for (int i = 0; i < currentLine.getCapacity(); i++) {
            Square square = currentLine.getSquare(i);
            square.check(currentWord, i);

            correct = square.getStatus() == GuessStatus.CORRECT;
        }

        grid.moveToNextLine();
        return correct;
    }

    public Grid getGrid() {
        return this.grid;
    }

}
