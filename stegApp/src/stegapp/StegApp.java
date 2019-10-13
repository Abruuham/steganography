/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegapp;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abrah
 */
public class StegApp extends Application {

    private final TextArea ta = new TextArea();
    private Stage stage;
    private final ImageView imageView = new ImageView();
    private final Label status = new Label("Ready");
    private String fName = "Untitled";
    private final RetrieveMessage rtm = new RetrieveMessage();

    private HBox toolBar() {

        HBox box = new HBox();
        String[] btns = {"New", "Open"};

        for (int i = 0; i < btns.length; i++) {

            Button b = new Button(btns[i]);

            b.setOnAction(toolBarHandler);

            box.getChildren().add(b);
        }

        box.setSpacing(5.0);
        box.setStyle("-fx-background-color:#D3D3D3");

        return box;
    }

    private MenuBar menuBar() {

        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");

        MenuItem exitMenuItem = new MenuItem("Exit");

        newMenuItem.setOnAction(menuHandler);
        openMenuItem.setOnAction(menuHandler);

        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, openMenuItem, new SeparatorMenuItem(), exitMenuItem);

        MenuBar bar = new MenuBar();
        bar.getMenus().add(fileMenu);

        return bar;

    }

    private void handleEvent(String event) {

        switch (event) {
            case "New":

                newFile();
                break;
            case "Open": {
                try {
                    openFile();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(StegApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            default:
                break;

        }

    }

    EventHandler<ActionEvent> menuHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            MenuItem mi = (MenuItem) event.getSource();
            handleEvent(mi.getText());
        }
    };
    EventHandler<ActionEvent> toolBarHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            Button mi = (Button) event.getSource();
            handleEvent(mi.getText());
        }
    };

    private void newFile() {

        fName = "Untitled";
        ta.clear();

        //ta.requestFocus();
    }

    private void openFile() throws MalformedURLException {
        try {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.bmp");

            //   FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image files (*.png)");
            fc.getExtensionFilters().add(filter);

            File file = fc.showOpenDialog(stage);

            if (file != null) {

                fName = file.toURI().toURL().toString();
                System.out.println(fName);
                Image image = new Image(fName);
                imageView.setFitHeight(600);
                imageView.setFitWidth(600);
                imageView.setPreserveRatio(true);
                imageView.setImage(image);
                //status.setText("Ready");
                //imageView.requestFocus();
                String strr = rtm.up(fName);
                //System.out.println(strr);
                readFile(strr);

            } else {
                System.out.println("This is null");
                //readFile(fName);
            }
        } catch (MalformedURLException e) {
            // new URL() failed
            e.printStackTrace();
            // ...
        }

    }

    private void readFile(String name) {

        ta.setText(name);

    }

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        GridPane menuPane = new GridPane();

        ta.setPrefWidth(600);
        root.setLeft(imageView);
        root.setRight(ta);

        menuPane.add(menuBar(), 0, 0);
        menuPane.add(toolBar(), 1, 0);
        root.setTop(menuPane);
        root.setBottom(status);

        Scene scene = new Scene(root, 1200, 600);

        stage.setTitle(fName);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
