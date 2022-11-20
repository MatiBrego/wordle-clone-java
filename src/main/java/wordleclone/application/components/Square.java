package wordleclone.application.components;

/*
    Represents a square in the grid, which can hold a letter.
 */
public class Square {
    private char letter;
    private boolean full;

    GuessStatus status;

    public Square() {
        full = false;
        letter = ' ';
        status = GuessStatus.EMPTY;
    }

    public boolean isEmpty(){
        return !full;
    }

    public void insertLetter(char c){
        this.letter = c;
        full = true;
    }

    public void removeLetter(){
        this.letter = ' ';
        full = false;
    }

    public void check(String answer, int position){
        if(answer.charAt(position) == this.letter) status = GuessStatus.CORRECT;
        else if (answer.indexOf(letter) >= 0) status = GuessStatus.WRONG_PLACE;
        else status = GuessStatus.WRONG_LETTER;
    }

    public GuessStatus getStatus() {
        return status;
    }

    public char getLetter() {
        return letter;
    }
}
