package wordleclone.application.components;

/*
    Class representing a grid made up of lines, each containing squares
 */
public class Grid {
    private final Line[] lines;
    private final int capacity;

    private int currentLine;

    private final int letterCapacity;

    public Grid(int guessCapacity, int letterCapacity) {
        this.capacity = guessCapacity;
        this.lines = new Line[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            lines[i] = new Line(letterCapacity);
        }
        currentLine = 0;

        this.letterCapacity = letterCapacity;
    }

    /*
        Returns the line in the "currentLine" position
     */
    public Line getCurrentLine(){
        if(isFull())
            return null;
        return lines[currentLine];
    }

    public int getCurrentLineIndex(){
        return currentLine;
    }

    public void moveToNextLine(){
        currentLine ++;
    }

    public int getGuessCapacity() {
        return capacity;
    }

    public int getLineCapacity() {
        return letterCapacity;
    }

    public Line getLine(int i){
        return lines[i];
    }
    public boolean isFull(){
        return capacity <= currentLine;
    }
}
