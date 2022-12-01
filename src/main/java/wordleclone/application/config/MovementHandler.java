package wordleclone.application.config;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.textfield.TextArea;

import java.util.Objects;

public class MovementHandler {

    private final TextArea[] currentLine;

    private int focus;

    public MovementHandler(int lineCapacity) {
        this.currentLine = new TextArea[lineCapacity];
        focus = 0;
    }

    public void focusNext(){
        currentLine[++focus].focus();
    }

    public void focusPrev(){
        currentLine[--focus].focus();
    }

    public void addSquare(TextArea s, int index){
        currentLine[index] = s;
        s.addFocusListener((Void) -> focusAt(index));
        s.addKeyDownListener(this::handleKeyInput);
    }

    public void focusAt(int index){
        focus = index;
    }

    public void focusFirst(){
        currentLine[0].focus();
        focus = 0;
    }

    public void handleKeyInput(KeyDownEvent k){
        if(Objects.equals(k.getKey().toString(), Key.BACKSPACE.toString())) focusPrev();
        else if (Objects.equals(k.getKey().toString(), Key.ARROW_LEFT.toString())) focusPrev();
        else if(Objects.equals(k.getKey().toString(), Key.ARROW_RIGHT.toString())) focusNext();
        else focusNext();
    }

}
