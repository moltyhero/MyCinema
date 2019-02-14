package uiPart;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox
{

    public static void popUp(String title, String message)
    {
        Stage window = new Stage();

        // Block input events until we take care of it
        window.initModality(Modality.APPLICATION_MODAL);
        // Title of the alert
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        // The text
        label.setText(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


        // In the main!
        // button.SetOnAction (e -> {
        //		boolean result = AlertBox.display("Title", "Message");
    }

    static int confirm_num = 0;

    public static int user_input(String title, String message)
    {
        Stage window = new Stage();

        // Block input events until we take care of it
        window.initModality(Modality.APPLICATION_MODAL);
        // Title of the alert
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        // The text
        label.setText(message);

        TextField textField = new TextField ();
        textField.setAlignment(Pos.CENTER);

        // Confirmation
        Button confirmBtn = new Button("Confirm");


        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e)
            {
                confirm_num = Integer.parseInt(textField.getText());
                window.close();
            }
        });

        VBox layout = new VBox(10);
        // Create layout
        layout.getChildren().addAll(label, confirmBtn, textField);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return confirm_num;

        // In the main!
        // button.SetOnAction (e -> {
        //		int num = AlertBox.user_input("Title", "Message");
    }

}
