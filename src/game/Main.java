package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    // show message dialog
    public static void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Encounterred");
        alert.setHeaderText("");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Kamrun Nahar");
        stage.show();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(30, 30, 25, 25));

        // Game Pane
        // Game ID
        Label gameIdLabel = new Label("Game's ID:");
        grid.add(gameIdLabel, 0, 1);
        TextField gameIdTextField = new TextField();
        grid.add(gameIdTextField, 1, 1);

        // Game Title
        Label gameTitleLabel = new Label("Game's Title:");
        grid.add(gameTitleLabel, 0, 2);
        TextField gameTitleTextField = new TextField();
        grid.add(gameTitleTextField, 1, 2);

        // Add Game Button
        Button addGameButton = new Button("Game Add ");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(addGameButton);
        grid.add(hbBtn, 0, 3);

        // Display Game Button
        Button displayGameButton = new Button("Display");
        HBox hbBtn4 = new HBox(10);
        hbBtn4.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn4.getChildren().add(displayGameButton);
        grid.add(hbBtn4, 1, 3);

        // Player and Game Pane
        // Player and Game Id
        Label playerAndGameIdLabel = new Label("Player and Game ID:");
        grid.add(playerAndGameIdLabel, 0, 4);
        TextField playerAndGameTextField = new TextField();
        grid.add(playerAndGameTextField, 1, 4);

        // Foreign Game Id
        Label foreignGameIdLabel = new Label("Game's ID:");
        grid.add(foreignGameIdLabel, 0, 5);
        TextField foreignGameTextField = new TextField();
        grid.add(foreignGameTextField, 1, 5);

        // Player and Game Id
        Label foreignPlayerIdLabel = new Label("Player's ID:");
        grid.add(foreignPlayerIdLabel, 0, 6);
        TextField foreignPlayerTextField = new TextField();
        grid.add(foreignPlayerTextField, 1, 6);

        // Player Date
        Label playerDateLabel = new Label("Playing Date:");
        grid.add(playerDateLabel, 0, 7);
        TextField playerDateTextField = new TextField();
        grid.add(playerDateTextField, 1, 7);

        // Score
        Label scoreLabel = new Label("Score:");
        grid.add(scoreLabel, 0, 8);
        TextField scoreTextField = new TextField();
        grid.add(scoreTextField, 1, 8);

        // Add Play Game Button
        Button playGameButton = new Button("Add PlayGame");
        HBox hbBtn5 = new HBox(10);
        hbBtn5.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn5.getChildren().add(playGameButton);
        grid.add(hbBtn5, 0, 9);

        // Display Results Button
        Button displayPlayerAndGameButton = new Button("Display");
        HBox hbBtn6 = new HBox(10);
        hbBtn6.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn6.getChildren().add(displayPlayerAndGameButton);
        grid.add(hbBtn6, 1, 9);

        // Player Controls

        // PlayerID
        Label playerIdLabel = new Label("Player's ID:");
        grid.add(playerIdLabel, 4, 1);
        TextField playerIdTextField = new TextField();
        grid.add(playerIdTextField, 5, 1);

        // FirstName
        Label fNameLabel = new Label("First Name");
        grid.add(fNameLabel, 4, 2);
        TextField fNameTextField = new TextField();
        grid.add(fNameTextField, 5, 2);

        // Last Name
        Label lNameLabel = new Label("Last Name:");
        grid.add(lNameLabel, 4, 3);
        TextField lNameTextField = new TextField();
        grid.add(lNameTextField, 5, 3);

        // Address
        Label addressLabel = new Label("Address:");
        grid.add(addressLabel, 4, 4);
        TextField addressTextField = new TextField();
        grid.add(addressTextField, 5, 4);

        // PostalCode
        Label pCodeLabel = new Label("Postal Code:");
        grid.add(pCodeLabel, 4, 5);
        TextField pCodeTextField = new TextField();
        grid.add(pCodeTextField, 5, 5);

        // Province
        Label provinceLabel = new Label("Province:");
        grid.add(provinceLabel, 4, 6);
        TextField provinceTextField = new TextField();
        grid.add(provinceTextField, 5, 6);

        // Phone Number
        Label phoneNumLabel = new Label("Phone No.:");
        grid.add(phoneNumLabel, 4, 7);
        TextField phoneNumTextField = new TextField();
        grid.add(phoneNumTextField, 5, 7);

        // Add Player Button
        Button addPlayerButton = new Button("Add ");
        HBox hbBtn3 = new HBox(10);
        hbBtn3.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn3.getChildren().add(addPlayerButton);
        grid.add(hbBtn3, 4, 8);

        // Add Player Button
        Button updatePlayerButton = new Button("Update ");
        HBox hbBtn7 = new HBox(10);
        hbBtn7.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn7.getChildren().add(updatePlayerButton);
        grid.add(hbBtn7, 5, 8);

        // Display Player Button
        Button displayPlayerButton = new Button("Display Player ");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn2.getChildren().add(displayPlayerButton);
        grid.add(hbBtn2, 6, 8);

        // Make Text Area
        TextArea textArea = new TextArea();
        textArea.setEditable(false);

        // vBox text area
        VBox vBox = new VBox();
        vBox.getChildren().add(grid);
        vBox.getChildren().add(textArea);

        // Add game button action event
        addGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                int game_id = Integer.parseInt(gameIdTextField.getText());
                String game_title = gameTitleTextField.getText();

                // Adding data to database
                Game game = new Game(game_id, game_title);
                try {
                    DbConnect.insertGameData(game);
                    textArea.setText("Inserted");
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }

            }
        });
        // Display Game Button Action event
        displayGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                // Reading game data from database

                try {
                    String sql = "select * from Game";
                    ResultSet resultSet = DbConnect.query("Game", sql);

                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    String output = "";
                    for (int i = 1; i <= columnsNumber; i++) {
                        output += String.format("%10s\t", rsmd.getColumnName(i));
                    }
                    output += "\n";
                    output += "\n";
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            if (i > 1) {
                                output = String.format("%10s\t", output);

                            }
                            String columnValue = resultSet.getString(i);
                            output += String.format("%10s\t", columnValue);
                        }
                        output += "\n";
                    }
                    textArea.setText(output);
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }

            }
        });

        // Add player button action event
        addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // Read values from controls


                int player_id = Integer.parseInt(playerIdTextField.getText());
                String first_name = fNameTextField.getText();
                String last_name = lNameTextField.getText();
                String address = addressTextField.getText();
                String postal_code = pCodeTextField.getText();
                String province = provinceTextField.getText();
                String phone_number = phoneNumTextField.getText();

                // Writing player to database
                Player player = new Player(player_id, first_name, last_name, address, postal_code, province,
                        phone_number);
                try {
                    DbConnect.insertPlayerData(player);
                    textArea.setText("Inserted");
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }

            }
        });

        // Update player button action event
        updatePlayerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                int player_id = Integer.parseInt(playerIdTextField.getText());
                String first_name = fNameTextField.getText();
                String last_name = lNameTextField.getText();
                String address = addressTextField.getText();
                String postal_code = pCodeTextField.getText();
                String province = provinceTextField.getText();
                String phone_number = phoneNumTextField.getText();

                // Writing player to database
                Player player = new Player(player_id, first_name, last_name, address, postal_code, province,
                        phone_number);
                try {
                    DbConnect.updatePlayerData(player);
                    textArea.setText("Updated");
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }
            }
        });

        // Display player button action event
        displayPlayerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // Read values from controls

                // Reading player to database

                try {
                    String sql = "select * from Player";
                    ResultSet resultSet = DbConnect.query("Player", sql);

                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    String output = "";
                    for (int i = 1; i <= columnsNumber; i++) {
                        output += String.format("%10s\t", rsmd.getColumnName(i));
                    }
                    output += "\n";
                    output += "\n";
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            if (i > 1) {
                                output = String.format("%10s\t", output);

                            }
                            String columnValue = resultSet.getString(i);
                            output += String.format("%10s\t", columnValue);
                        }
                        output += "\n";
                    }
                    textArea.setText(output);
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }

            }
        });
        // Add PlayerAndGame button action event
        playGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // Read values from controls

                int player_game_id = Integer.parseInt(playerAndGameTextField.getText());
                int game_id = Integer.parseInt(foreignGameTextField.getText());
                int player_id = Integer.parseInt(foreignPlayerTextField.getText());
                String player_date = playerDateTextField.getText();
                int score = Integer.parseInt(scoreTextField.getText());

                // Writing player and game to database
                PlayerAndGame playerAndGame = new PlayerAndGame(player_id, game_id, player_id, player_date, score);
                try {
                    DbConnect.insertPlayerAndGameData(playerAndGame);
                    textArea.setText("Inserted");
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }

            }
        });

        // Display PlayerAndGame button action event
        displayPlayerAndGameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                // Read values from controls

                // Reading player to database

                try {
                    String sql = "select * from PlayerAndGame";
                    ResultSet resultSet = DbConnect.query("PlayerAndGame", sql);

                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int columnsNumber = rsmd.getColumnCount();
                    String output = "";
                    for (int i = 1; i <= columnsNumber; i++) {
                        output += String.format("%10s\t", rsmd.getColumnName(i));
                    }
                    output += "\n";
                    output += "\n";
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnsNumber; i++) {
                            if (i > 1) {
                                output = String.format("%10s\t", output);

                            }
                            String columnValue = resultSet.getString(i);
                            output += String.format("%10s\t", columnValue);
                        }
                        output += "\n";
                    }
                    textArea.setText(output);
                } catch (SQLException ex) {
                    textArea.setText(ex.getMessage());
                }

            }
        });

        // Make Scene
        Scene scene = new Scene(vBox, 800, 600);
        stage.setResizable(false);
        stage.setScene(scene);

    }

    public static void main(String[] args) {
        launch();
    }
}
