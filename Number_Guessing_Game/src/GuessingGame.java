import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuessingGame extends Application
{
    public static final int SIZE_W = 450, SIZE_H = 200;
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        BorderPane root = new MainPane();
        root.setPrefSize(SIZE_W, SIZE_H);

        // Set Scene and stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Number Guessing Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
