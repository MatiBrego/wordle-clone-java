package wordleclone.application.config;

import com.vaadin.flow.component.textfield.TextArea;

public class MovementHandler {

    private TextArea[] currentLine;

    private int focus;

    public MovementHandler(int lineCapacity) {
        this.currentLine = new TextArea[lineCapacity];
        focus = 0;
    }

    public void focusNext(){
        currentLine[0].focus();
    }

    public void addSquare(TextArea s, int index){
        currentLine[index] = s;
    }
}
