package Quiz.ClientSide;

import Quiz.ClientSide.controllers.EnterNameInterfaceController;
import Quiz.ClientSide.controllers.QuestionInterfaceController;
import Quiz.ClientSide.controllers.SelectCategoryInterfaceController;

import Quiz.ClientSide.controllers.WaitController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class GameInterface extends Application {

    Client client;
    String playerName;
    Stage primaryStage;

    // Kategorifönstret
    FXMLLoader selectCategoryLoader;
    Parent selectCategory;
    SelectCategoryInterfaceController selectCategoryController;
    Scene categoryScene;

    // Fönstret innehållande frågan och de 4 svarsalternativen
    FXMLLoader questionLoader;
    Parent question;
    QuestionInterfaceController questionController;
    Scene questionScene;

    // Fönstret där man anger sitt namn
    FXMLLoader enterNameLoader;
    Parent enterName;
    EnterNameInterfaceController enterNameController;
    Scene enterNameScene;

    FXMLLoader waitLoader;
    Parent wait;
    WaitController waitController;
    Scene waitScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        // Fönstret där man väljer kategori
        this.selectCategoryLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("selectCategory.fxml")));
        this.selectCategory = selectCategoryLoader.load();
        this.selectCategoryController = selectCategoryLoader.getController();
        this.categoryScene = new Scene(selectCategory, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Fönstret innehållande frågan och de 4 svarsalternativen
        this.questionLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("question.fxml")));
        this.question = questionLoader.load();
        this.questionController = questionLoader.getController();
        this.questionScene = new Scene(question, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.questionController.questionText.setWrappingWidth(Constants.WINDOW_WIDTH - 20);

        // Fönstret där man anger sitt namn
        this.enterNameLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("enterName.fxml")));
        this.enterName = enterNameLoader.load();
        this.enterNameController = enterNameLoader.getController();
        this.enterNameScene = new Scene(enterName, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Wait-fönster
        this.waitLoader = new FXMLLoader(Objects.requireNonNull(getClass().getClassLoader().getResource("wait.fxml")));
        this.wait = waitLoader.load();
        this.waitController = waitLoader.getController();
        this.waitScene = new Scene(wait, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        new GameSetup(this);

        // Första fönstret som man ser.
        primaryStage.setScene(enterNameScene);

        categoryScene.getStylesheets().add("styles.css");
        questionScene.getStylesheets().add("styles.css");
        enterNameScene.getStylesheets().add("styles.css");

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Closing game interface.");
            System.exit(0);
        });

        primaryStage.setTitle(Constants.TITLE);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}