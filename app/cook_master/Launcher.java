package app.cook_master;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Launcher extends Application {
    private Stage primaryStage;
    private StackPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cook Master");

        // Charger l'image de logo
        Image logoImage = new Image("file:img/CM.jpg");

        // Empêcher le redimensionnement de la fenêtre
        this.primaryStage.setResizable(false);

        // Définir une taille fixe pour la fenêtre
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMinHeight(600);

        // Définir le logo de l'application
        primaryStage.getIcons().add(logoImage);

        root = new StackPane();
        navigateToLoginPage();

        Scene scene = new Scene(root, 1000, 600);
        String css = this.getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void navigateToLoginPage() {
        StackPane loginLayout = new StackPane();
        loginLayout.getStyleClass().add("container-login");

        // Création de l'ImageView avec l'image en fond
        Image backgroundImage = new Image("file:img/hero-bg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(600);

        // Ajout d'un effet de fondu sur l'image de fond
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        backgroundImageView.setEffect(colorAdjust);

        // Création du conteneur pour le label et le bouton
        VBox contentLayout = new VBox();
        contentLayout.setSpacing(10);
        contentLayout.getStyleClass().add("container-login");

        // Création de l'ImageView avec l'image en fond
        Image Logo = new Image("file:img/CM.jpg");
        ImageView LogoView = new ImageView(Logo);
        LogoView.setFitWidth(150);
        LogoView.setFitHeight(75);

        // Création des éléments de la page
        Label loginLabel = new Label("Page de connexion");
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Se connecter");

        // Ajout des classes CSS aux éléments
        loginLabel.getStyleClass().add("title");
        emailField.getStyleClass().add("textfield");
        passwordField.getStyleClass().add("textfield");
        loginButton.getStyleClass().add("button");

        loginButton.setOnAction(e -> {
            String email = emailField.getText(); // Récupérer le texte saisi dans le champ email
            String password = passwordField.getText(); // Récupérer le texte saisi dans le champ mot de passe

            // Vérification des informations de connexion
            if (isValidCredentials(email, password)) {
                navigateToHomePage(); // Accéder à la page d'accueil si les informations de connexion sont valides
            } else {
                // Afficher un message d'erreur
                Label errorLabel = new Label("Email ou mot de passe incorrect");
                errorLabel.getStyleClass().add("error");
                contentLayout.getChildren().add(errorLabel);

                // Effacer le contenu des champs email et mot de passe
                emailField.setText("");
                passwordField.setText("");

                emailField.requestFocus();

                // Supprimer errorLabel après 3 secondes
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                    contentLayout.getChildren().remove(errorLabel);
                }));
                timeline.play();
            }
        });

        contentLayout.getChildren().addAll(LogoView, loginLabel, emailField, passwordField, loginButton);

        // Ajout de l'ImageView et du conteneur du contenu dans la StackPane
        loginLayout.getChildren().addAll(backgroundImageView, contentLayout);

        root.getChildren().setAll(loginLayout);
    }

    private boolean isValidCredentials(String email, String password) {
        // Effectuez ici votre logique de vérification des informations de connexion
        // Par exemple, vérifier si l'email et le mot de passe correspondent à un
        // utilisateur enregistré dans votre système

        // Retournez true si les informations sont valides, false sinon
        return email.equals("admin@test.com") && password.equals("azerty");
    }

    public void navigateToHomePage() {
        VBox homeLayout = new VBox();
        homeLayout.getStyleClass().add("container");

        Label homeLabel = new Label("Page d'accueil");
        homeLabel.getStyleClass().add("title");

        Button searchButton = new Button("Rechercher un utilisateur");
        searchButton.getStyleClass().add("button");

        Button profileButton = new Button("Voir mon profil");
        profileButton.getStyleClass().add("button");

        searchButton.setOnAction(e -> navigateToSearchPage());
        profileButton.setOnAction(e -> navigateToProfilePage());

        homeLayout.getChildren().addAll(homeLabel, searchButton, profileButton);
        root.getChildren().setAll(homeLayout);
    }

    public void navigateToSearchPage() {
        VBox searchLayout = new VBox();
        searchLayout.getStyleClass().add("container");

        Label searchLabel = new Label("Page de recherche d'un utilisateur");
        searchLabel.getStyleClass().add("title");

        Button backButton = new Button("Retour");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> navigateToHomePage());

        searchLayout.getChildren().addAll(searchLabel, backButton);
        root.getChildren().setAll(searchLayout);
    }

    public void navigateToProfilePage() {
        VBox profileLayout = new VBox();
        profileLayout.getStyleClass().add("container");

        Label profileLabel = new Label("Page de profil d'un utilisateur");
        profileLabel.getStyleClass().add("title");

        Button backButton = new Button("Retour");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> navigateToHomePage());

        profileLayout.getChildren().addAll(profileLabel, backButton);
        root.getChildren().setAll(profileLayout);
    }
}
