package GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * this class handle the creation of a user interface for usage in creating an ideal factory.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = layout();
        primaryStage.setTitle("Parallel Factory");
        primaryStage.setScene(new Scene(root, 600, 400));
        Platform.setImplicitExit(false);
        primaryStage.setResizable(true);
        primaryStage.setOnCloseRequest(we -> System.exit(0));
        primaryStage.show();
    }

    private Parent layout(){
        Pane root = new MineSweeperPane();

        return root;
    }
}
