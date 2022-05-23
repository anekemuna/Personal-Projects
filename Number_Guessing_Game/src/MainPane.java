import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class MainPane extends BorderPane
{
    // instance variables
    final int GUESSES = 5;
    static int guessCount = 0;
    private int chosen;
    GridPane gPane;
    HBox hBox;
    TextField inputField;
    Label introLabel, resultLabel, guessLabel;
    Button startButton, endButton, submitButton;

    public MainPane()
    {
        // initialize instance variables
        chosen = (int) ((Math.random() * 100) + 1);
        startButton = new Button("New Game");
        endButton = new Button("End Game");
        submitButton = new Button("Submit");
        introLabel = new Label("Guess the Number (1-100):");
        resultLabel = new Label("");
        guessLabel = new Label((GUESSES - guessCount) +" guesses left");
        guessLabel.setTextFill(Color.VIOLET);
        inputField = new TextField();

        // add handlers
        submitButton.setOnAction(new SubmitButtonHandler());
        startButton.setOnAction(new StartButtonHandler());
        endButton.setOnAction(new EndButtonHandler());

        // add to gridPane
        gPane = new GridPane();
        gPane.add(inputField, 0, 0);
        gPane.add(submitButton, 1, 0);
        gPane.add(resultLabel, 0, 1);
        gPane.add(guessLabel, 1, 1);
        gPane.setPadding(new Insets(20,0,0,0));
        gPane.setVgap(15);
        gPane.setHgap(10);
        gPane.setAlignment(Pos.TOP_CENTER);

        // add to hBox
        hBox = new HBox(startButton, endButton);
        hBox.setSpacing(220);

        // Add to borderPane and align
        setTop(introLabel);
        setAlignment(introLabel, Pos.CENTER);
        setPadding(new Insets(30, 30, 30, 30));
        setCenter(gPane);
        setBottom(hBox);

    }

    private class SubmitButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            guessCount++;

            try
            {
                int input = Integer.parseInt(inputField.getText());

                if(guessCount >= GUESSES)
                {
                    guessLabel.setText((GUESSES - guessCount) +" guesses left");
                    resultLabel.setTextFill(Color.RED);
                    resultLabel.setText("Press \"New Game\" button");
                }
                else
                {
                    guessLabel.setText((GUESSES - guessCount) +" guesses left");
                    if(input == chosen)
                    {
                        resultLabel.setTextFill(Color.GREEN);
                        resultLabel.setText("Correct!!!");
                    }
                    else if(input < chosen)
                    {
                        resultLabel.setTextFill(Color.RED);
                        resultLabel.setText("Too Low!");
                    }
                    else
                    {
                        resultLabel.setTextFill(Color.RED);
                        resultLabel.setText("Too High!");
                    }
                }

            }
            catch (NumberFormatException nfe)
            {
                resultLabel.setTextFill(Color.RED);
                resultLabel.setText("Error: Input has to be number characters");
            }
        }
    }

    private class EndButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            Platform.exit();
        }
    }

    private class StartButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            chosen = (int) ((Math.random() * 100) + 1);
            guessCount = 0;
            guessLabel.setText((GUESSES - guessCount) +" guesses left");
        }
    }

}
