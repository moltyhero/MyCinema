package uiPart;

import LogicPart.Cinema;
import LogicPart.Hall;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application
{
    Hall cinema_hall;
    Scene mainMenu, manualSelection;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane border = new BorderPane();
        HBox hbox = new HBox();
        border.setTop(hbox);
        border.setLeft(addMenuVBox());

        // Initialize the matrix
        cinema_hall = new Hall();
        Cinema.CreateMat(cinema_hall);

        window.setTitle("Cinema");
        mainMenu = new Scene(border, 1280, 720);

        window.setScene(mainMenu);
        window.show();

    }


    // Contains the left menu
    public VBox addMenuVBox()
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Select option");
        //title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Button options[] = new Button[] {
                new Button("Chair number"),
                new Button("Manual assign"),
                new Button("Exit")};

        // Chair number
        options[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e)
            {

                BorderPane border = new BorderPane();
                HBox hbox = new HBox();
                border.setTop(hbox);
                border.setLeft(addMenuVBox());

                VBox vBox = addNonManualVBox(cinema_hall);

                int num = AlertBox.user_input("Title", "Message");
                Image image = new Image(getClass().getResourceAsStream("Assets/selectedSit.png"));
                Cinema.FewChairs(cinema_hall, num, vBox, image);

                border.setCenter(vBox);
                manualSelection = new Scene(border, 1280, 720);

                window.setScene(manualSelection);

            }
        });
        // Manual assign
        options[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                BorderPane border = new BorderPane();
                HBox hbox = new HBox();
                border.setTop(hbox);
                border.setLeft(addMenuVBox());
                border.setCenter(addManualVBox(cinema_hall));

                manualSelection = new Scene(border, 1280, 720);

                window.setScene(manualSelection);

            }
        });
        // Exit button
        options[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e)
            {
                window.close();
            }
        });



        for (int i=0; i<3; i++) {
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        vbox.setAlignment(Pos.BASELINE_CENTER);

        return vbox;
    }

    // For Manual selection
    public VBox addManualVBox(Hall hall)
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);


        for (int i=0; i<hall.getNum_of_rows(); i++)
        {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER);
            for (int j = i; j < (hall.getNum_of_columms() - (i)); j++)
            {
                if (hall.getHall()[i][j] == null) // So the people find da wey to their queen
                {
                    Label label = new Label();
                    label.setPrefWidth(25);
                    label.setPrefHeight(25);
                    row.getChildren().add(label);
                    continue;
                }
                // Create a new button
                Button btn = new Button();
                Image image = null;
                if (hall.getHall()[i][j].isTaken()==true)
                {
                    image = new Image(getClass().getResourceAsStream("Assets/takenSit.png"));
                }
                else
                {
                    image = new Image(getClass().getResourceAsStream("Assets/availableSit.png"));
                }

                ImageView temp = new ImageView(image);
                temp.setFitHeight(25);
                temp.setFitWidth(25);
                btn.setGraphic(temp);

                // Sets what happens when you click on the button
                int finalI = i;
                int finalJ = j;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        Image image = null;

                        if (hall.getHall()[finalI][finalJ].isTaken()==true)
                        {
                            hall.getHall()[finalI][finalJ].setTaken(false);
                            image = new Image(getClass().getResourceAsStream("Assets/availableSit.png"));
                        }
                        else
                        {
                            hall.getHall()[finalI][finalJ].setTaken(true);
                            image = new Image(getClass().getResourceAsStream("Assets/TakenSit.png"));
                        }
                        ImageView temp = new ImageView(image);
                        temp.setFitHeight(25);
                        temp.setFitWidth(25);
                        btn.setGraphic(temp);
                    }
                });

                row.getChildren().add(btn);
            }
            //VBox.setMargin(btn, new Insets(0, 0, 0, 8));


            vbox.getChildren().add(row);
        }


        return vbox;
    }

    public VBox addNonManualVBox(Hall hall)
    {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);


        for (int i=0; i<hall.getNum_of_rows(); i++)
        {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER);
            for (int j = i; j < (hall.getNum_of_columms() - (i)); j++)
            {
                if (hall.getHall()[i][j] == null) // So the people find da wey to their queen
                {
                    Label label = new Label();
                    label.setPrefWidth(25);
                    label.setPrefHeight(25);
                    row.getChildren().add(label);
                    continue;
                }
                // Create a new button
                Button btn = new Button();
                Image image = null;
                if (hall.getHall()[i][j].isTaken()==true)
                {
                    image = new Image(getClass().getResourceAsStream("Assets/takenSit.png"));
                }
                else
                {
                    image = new Image(getClass().getResourceAsStream("Assets/availableSit.png"));
                }

                ImageView temp = new ImageView(image);
                temp.setFitHeight(25);
                temp.setFitWidth(25);
                btn.setGraphic(temp);

                // Sets what happens when you click on the button
                int finalI = i;
                int finalJ = j;
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e)
                    {
                        int kelet = 5;
                        if (Cinema.CheckSits(hall, hall.getHall()[finalI][finalJ], kelet) == false)
                        {
                            AlertBox.popUp("Failed", "Failed");
                        }
                    }
                });

                row.getChildren().add(btn);

            }
            //VBox.setMargin(btn, new Insets(0, 0, 0, 8));


            vbox.getChildren().add(row);
        }


        return vbox;
    }


    public static void main(String[] args)
    {
        launch(args);
    }


    /*public void test(VBox group)
    {
        Node nodeOut = group.getChildren().get(0);
        if(nodeOut instanceof HBox){
            for(Node nodeIn:((VBox)nodeOut).getChildren()){
                if(nodeIn instanceof Button)
                {
                    Image image = null;
                    if (cinema_hall.getHall()[i][j].isTaken()==true)
                    {
                        image = new Image(getClass().getResourceAsStream("Assets/takenSit.png"));
                    }
                    else
                    {
                        image = new Image(getClass().getResourceAsStream("Assets/availableSit.png"));
                    }

                    ImageView temp = new ImageView(image);
                    temp.setFitHeight(25);
                    temp.setFitWidth(25);
                    ((Button)nodeIn).setGraphic(temp);
                }
            }
        }
    }*/
}
