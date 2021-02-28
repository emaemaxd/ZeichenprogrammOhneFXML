import com.sun.javafx.charts.Legend;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TheMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane gridpane = new GridPane();

        // line-Variablen
        GridPane gridpane1 = new GridPane();
        Button drawLine = new Button("zeichne");
        ColorPicker lineColor = new ColorPicker();
        TextField lineThic = new TextField();
        TextField lineTrans = new TextField();
        Pane linePane = new Pane();
        Line line = new Line(100,100, 250, 200);

        drawLine.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StrokeTransition stroke = new StrokeTransition();

                stroke.setDuration(Duration.millis(Double.parseDouble(lineTrans.getText())));
                stroke.setFromValue(Color.color(1,1,1));
                stroke.setToValue(lineColor.getValue());

                line.setStrokeWidth(Double.parseDouble(lineThic.getText()));
                linePane.getChildren().clear();
                linePane.getChildren().add(line);
                stroke.setShape(line);
                stroke.play();
            }
        });

        // Text-Variablen
        GridPane gridpane2 = new GridPane();
        Button drawText = new Button("ändern");
        ObservableList<String> textList = FXCollections.observableArrayList("BOLD", "MEDIUM", "LIGHT");
        ComboBox<String> comboboxText = new ComboBox<>(textList);
        TextField textFam = new TextField();
        TextField textSize = new TextField();
        TextField textEffRad = new TextField();
        TextField textEffAng = new TextField();
        Pane textPane = new Pane();
        Text text = new Text(0, 200,"Text zum verändern");
        textPane.getChildren().add(text);


        drawText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MotionBlur motion = new MotionBlur();
                String s = comboboxText.getValue().toString();
                switch (s) {        // ändere werte in combobox => andere FontWeight
                    case "BOLD":
                        text.setFont(Font.font(textFam.getText(), FontWeight.BOLD , Double.parseDouble(textSize.getText())));
                        break;
                    case "MEDIUM":
                        text.setFont(Font.font(textFam.getText(), FontWeight.MEDIUM , Double.parseDouble(textSize.getText())));
                        break;
                    case "LIGHT":
                        text.setFont(Font.font(textFam.getText(), FontWeight.LIGHT , Double.parseDouble(textSize.getText())));
                        break;
                }

                motion.setAngle(Double.parseDouble((textEffAng.getText())));
                motion.setRadius(Double.parseDouble((textEffRad.getText())));
                text.setEffect(motion);
                textPane.getChildren().clear();
                textPane.getChildren().add(text);
            }
        });

        // circle-Variablen
        GridPane gridpane3 = new GridPane();
        Button drawCircle = new Button("zeichne");
        ColorPicker circleColor = new ColorPicker();
        TextField circleSize = new TextField();
        TextField circleAni = new TextField();
        Pane circlePane = new Pane();
        Circle circle = new Circle(150, 100, 0);

        drawCircle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Timeline timeline = new Timeline();

                //circle = new Circle(150, 100, Double.parseDouble(circleSize.getText()));
                circle.setRadius(Double.parseDouble(circleSize.getText()));
                circle.setFill(circleColor.getValue());
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.setAutoReverse(true);
                KeyValue kv = new KeyValue(circle.centerXProperty(), 200);
                KeyFrame kf = new KeyFrame(Duration.millis(Double.parseDouble(circleAni.getText())), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();
                circlePane.getChildren().clear();
                circlePane.getChildren().add(circle);
            }
        });


        // line
        gridpane1.add(new Label("Farbe: "), 0,0);
        gridpane1.add(lineColor, 1,0);
        gridpane1.add(new Label("Strichstärke: "), 0,1);
        gridpane1.add(lineThic, 1,1);
        gridpane1.add(new Label("Transitiondauer: "), 0,2);
        gridpane1.add(lineTrans, 1,2);
        gridpane1.add(drawLine, 1, 3);

        // text
        gridpane2.add(new Label("Schriftdicke: "), 0,0);
        gridpane2.add(comboboxText, 1,0);
        gridpane2.add(new Label("Schriftart: "), 0,1);
        gridpane2.add(textFam, 1,1);
        gridpane2.add(new Label("Schriftgröße: "), 0,2);
        gridpane2.add(textSize, 1,2);
        gridpane2.add(new Label("Effektradius: "), 0,3);
        gridpane2.add(textEffRad, 1,3);
        gridpane2.add(new Label("Effektwinkel: "), 0,4);
        gridpane2.add(textEffAng, 1,4);
        gridpane2.add(drawText, 1, 5);

        // circle
        gridpane3.add(new Label("Farbe: "), 0,0);
        gridpane3.add(circleColor, 1,0);
        gridpane3.add(new Label("Größe: "), 0,1);
        gridpane3.add(circleSize, 1,1);
        gridpane3.add(new Label("Animationsdauer: "), 0,2);
        gridpane3.add(circleAni, 1,2);
        gridpane3.add(drawCircle, 1, 3);

        gridpane.add(gridpane1,0,0);
        gridpane.add(linePane, 0,1);
        gridpane.add(gridpane2, 1,0);
        gridpane.add(textPane, 1,1);
        gridpane.add(gridpane3, 2,0);
        gridpane.add(circlePane, 2,1);
        StackPane root = new StackPane();
        root.getChildren().add(gridpane);
        primaryStage.setTitle("Zeichenprogramm ohne FXML");
        primaryStage.setScene(new Scene(root, 750, 380));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
