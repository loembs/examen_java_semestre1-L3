package com.patrick;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

import com.patrick.core.factory.FactoryServ;
import com.patrick.core.factory.FactoryRouter;
import com.patrick.entities.User;

import java.io.IOException;

public class App extends Application {
    
    private static Scene scene;
    private static User currentUser;
    private static final FactoryServ factoryServ = new FactoryServ();
    private static final FactoryRouter factoryRouter = new FactoryRouter();
    private static StackPane contentArea;

    @Override
    public void start(Stage stage) throws Exception {
        contentArea = new StackPane();
        factoryRouter.getInstanceRouter().initialiserUsersParDefaut(factoryServ);
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setTitle("Gestion du Cahier de Dette");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/patrick/views/" + fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier FXML : " + fxml + ".fxml");
            throw e;
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static boolean login(String login, String password) {
        User user = factoryRouter.getInstanceRouter().selectByLogin(login);
        if (user != null && factoryRouter.getInstanceRouter().selectByPassword(password) != null) {
            currentUser = user;
            return true;
        } else {
            showError("Erreur de connexion", "Login ou mot de passe incorrect.");
            return false;
        }
    }

    public static void loadMenuByRole() {
        try {
            switch (currentUser.getRole()) {
                case ADMIN:
                    setRoot("adminMenu");
                    contentArea = (StackPane) scene.lookup("#contentArea");
                    break;
                case BOUTIQUIER:
                    setRoot("boutiquierMenu");
                    contentArea = (StackPane) scene.lookup("#contentArea");
                    break;
                case CLIENT:
                    setRoot("clientMenu");
                    contentArea = (StackPane) scene.lookup("#contentArea");
                    break;
                default:
                    showError("Erreur", "Rôle inconnu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    

    public  static void loadView(String viewName) {
        try {
            contentArea.getChildren().clear(); 
            Parent view = App.loadFXML(viewName);
            contentArea.getChildren().add(view); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
