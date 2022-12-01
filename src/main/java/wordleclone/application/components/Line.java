package wordleclone.application.components;

/*
    Represents a line in the grid, and contains an array of squares
 */
public class Line {
    private final Square[] squares;
    private final int capacity;

    private int currentSquare;

    public Line(int letterCapacity) {
        this.capacity = letterCapacity;
        squares = new Square[capacity];
        for (int i = 0; i < capacity; i++) {
            squares[i] = new Square();
        }
        currentSquare = 0;
    }

    /*
        Returns the square in the current position
     */
    public Square getCurrentSquare(){
        return squares[currentSquare];
    }

    public int getCurrentSquareIndex(){return currentSquare;}

    public Square getSquare(int i) {
        return squares[i];
    }

    public void moveToNextSquare(){
        currentSquare ++;
    }

    public void moveToPrevSquare(){
        currentSquare --;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean isFull(){
        return this.capacity == currentSquare;
    }

    public boolean currentIsFirst(){
        return currentSquare == 0;
    }
}
