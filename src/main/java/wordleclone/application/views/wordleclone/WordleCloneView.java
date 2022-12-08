package wordleclone.application.views.wordleclone;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import wordleclone.application.components.Grid;
import wordleclone.application.components.Line;
import wordleclone.application.components.Square;
import wordleclone.application.config.GameLauncher;
import wordleclone.application.config.MovementHandler;
import wordleclone.application.config.WordGetter;

@PageTitle("Wordle Clone")
@Route(value = "")
public class WordleCloneView extends VerticalLayout {

    GameLauncher launcher;
    Grid grid;
    MovementHandler MoveHandler;

    String word;

    public WordleCloneView() {
        launcher = new GameLauncher();
        WordGetter getter = new WordGetter();
        word = getter.getWord();
        launcher.newGame(5, word);
        grid = launcher.getGrid();

        MoveHandler = new MovementHandler(grid.getLineCapacity());

        buildMap();
    }

    public void addAndCheckWord(){
        HorizontalLayout c = (HorizontalLayout) this.getComponentAt(grid.getCurrentLineIndex());
        for (int i = grid.getCurrentLine().getCurrentSquareIndex(); i < grid.getLineCapacity(); i++) {
            TextArea area = (TextArea) c.getComponentAt(i);
            launcher.addLetter(area.getValue().toCharArray()[0]);
        }
        boolean isCorrect = launcher.checkWord();
        buildMap();
    }

    public Component getFilledSquareComponent(Square square){
        switch (square.getStatus()) {
            case CORRECT -> {
                Label correct = new Label(String.valueOf(Character.toUpperCase(square.getLetter())));
                correct.addClassName("correct-box");
                return correct;
            }
            case WRONG_LETTER -> {
                Label wrong = new Label(String.valueOf(Character.toUpperCase(square.getLetter())));
                wrong.addClassName("wrong-box");
                return wrong;
            }
            case WRONG_PLACE -> {
                Label place = new Label(String.valueOf(Character.toUpperCase(square.getLetter())));
                place.addClassName("place-box");
                return place;
            }
        }
        return null;
    }

    public Component getEmptySquareComponent(){
        TextArea t = new TextArea();
        t.addClassName("empty-box");
        t.setReadOnly(true);
        return t;
    }

    public Component getFillableSquareComponent(){
        TextArea t = new TextArea();
        t.setMaxLength(1);
        t.addClassName("empty-box");
        return t;
    }

    public void buildMap(){
        removeAll();
        for (int i = 0; i < grid.getCurrentLineIndex(); i++) {
            HorizontalLayout line = new HorizontalLayout();
            line.setSizeUndefined();
            Line cline = grid.getLine(i);
            if(cline.getSquare(0).isEmpty()) break;
            for (int j = 0; j < cline.getCapacity(); j++) {
                Component comp = getFilledSquareComponent(cline.getSquare(j));
                line.add(comp);
            }
            add(line);
        }

        HorizontalLayout currentLine = new HorizontalLayout();
        currentLine.setSizeUndefined();
        Line cline = grid.getCurrentLine();
        for (int j = 0; j < cline.getCapacity(); j++) {
            Component comp = getFillableSquareComponent();
            MoveHandler.addSquare((TextArea) comp, j);
            currentLine.add(comp);
        }
        add(currentLine);

        for (int i = grid.getCurrentLineIndex() + 1; i < grid.getGuessCapacity(); i++){
            HorizontalLayout line = new HorizontalLayout();
            line.setSizeUndefined();
            for (int j = 0; j < grid.getLineCapacity(); j++) {
                Component comp = getEmptySquareComponent();
                line.add(comp);
            }
            add(line);
        }
        setAlignItems(Alignment.CENTER);

        MoveHandler.focusFirst();

        Button enter = new Button("Check");
        enter.addClickShortcut(Key.ENTER);
        enter.addClickListener(event -> addAndCheckWord());
        add(enter);
    }
}
