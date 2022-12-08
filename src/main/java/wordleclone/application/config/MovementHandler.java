package wordleclone.application.config;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.textfield.TextArea;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void focusNone(){currentLine[focus].setAutofocus(false);}

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
        String input = k.getKey().toString();
        if(Objects.equals(input, Key.BACKSPACE.toString()) && focus > 0) focusPrev();
        else if (Objects.equals(input, Key.ARROW_LEFT.toString())) focusPrev();
        else if(Objects.equals(input, Key.ARROW_RIGHT.toString())) focusNext();
        else if (Pattern.matches("[a-zA-Z]", input)) focusNext();
    }

}
