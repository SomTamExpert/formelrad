package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Formelrad Application
 * 
 * @author Karpf Marco & Abdullah Al-Kubaisi
 * @version 31.10.2021
 */

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();

			// Creating an image
			Image image = new Image(getClass().getResourceAsStream("formelradelektronik.gif"));
			ImageView imageView = new ImageView(image);
			imageView.setX(10);
			imageView.setY(10);
			imageView.setFitHeight(300);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			root.getChildren().add(imageView);

			Label lbleistung = new Label("Leistung:");
			lbleistung.relocate(10, 285);
			lbleistung.setFont(Font.font(15));
			root.getChildren().add(lbleistung);

			TextField txLeistung = new TextField();
			txLeistung.relocate(100, 285);
			txLeistung.setMaxWidth(150);
			txLeistung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txLeistung);

			Label qnleistung = new Label("Watt");
			qnleistung.relocate(265, 285);
			qnleistung.setFont(Font.font(15));
			root.getChildren().add(qnleistung);

			Label lblSpannung = new Label("Spannung:");
			lblSpannung.relocate(10, 325);
			lblSpannung.setFont(Font.font(15));
			root.getChildren().add(lblSpannung);

			TextField txSpannung = new TextField();
			txSpannung.relocate(100, 325);
			txSpannung.setMaxWidth(150);
			txSpannung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txSpannung);

			Label qnSpannung = new Label("Volt");
			qnSpannung.relocate(265, 325);
			qnSpannung.setFont(Font.font(15));
			root.getChildren().add(qnSpannung);

			Label lblStrom = new Label("Strom:");
			lblStrom.relocate(10, 365);
			lblStrom.setFont(Font.font(15));
			root.getChildren().add(lblStrom);

			TextField txStrom = new TextField();
			txStrom.relocate(100, 365);
			txStrom.setMaxWidth(150);
			txStrom.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txStrom);

			Label qnStrom = new Label("Ampere");
			qnStrom.relocate(265, 365);
			qnStrom.setFont(Font.font(15));
			root.getChildren().add(qnStrom);

			Label lblWiderstand = new Label("Widerstand:");
			lblWiderstand.relocate(10, 405);
			lblWiderstand.setFont(Font.font(15));
			root.getChildren().add(lblWiderstand);

			TextField txWiderstand = new TextField();
			txWiderstand.relocate(100, 405);
			txWiderstand.setMaxWidth(150);
			txWiderstand.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txWiderstand);

			Label qnWiderstand = new Label("Ohm");
			qnWiderstand.relocate(265, 405);
			qnWiderstand.setFont(Font.font(15));
			root.getChildren().add(qnWiderstand);

			Button btnBerechnen = new Button();
			btnBerechnen.relocate(100, 445);
			btnBerechnen.setText("Berechnen");
			root.getChildren().add(btnBerechnen);

			Button btnLoeschen = new Button();
			btnLoeschen.relocate(190, 445);
			btnLoeschen.setText("Löschen");
			root.getChildren().add(btnLoeschen);

			txSpannung.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue == null || newValue.isEmpty())
					txSpannung.setStyle("-fx-text-inner-color: black;");
			});
			txLeistung.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue == null || newValue.isEmpty())
					txLeistung.setStyle("-fx-text-inner-color: black;");
			});
			txStrom.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue == null || newValue.isEmpty())
					txStrom.setStyle("-fx-text-inner-color: black;");
			});
			txWiderstand.textProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue == null || newValue.isEmpty())
					txWiderstand.setStyle("-fx-text-inner-color: black;");
			});

			btnBerechnen.setOnAction(e -> {
				double power = 0.0;
				double tension = 0.0;
				double current = 0.0;
				double resistence = 0.0;
				Integer inputAmount = 0;

				if (!txLeistung.getText().isEmpty()) {
					power = Double.parseDouble(txLeistung.getText());
					inputAmount++;
				}
				if (!txSpannung.getText().isEmpty()) {
					tension = Double.parseDouble(txSpannung.getText());
					inputAmount++;
				}
				if (!txStrom.getText().isEmpty()) {
					current = Double.parseDouble(txStrom.getText());
					inputAmount++;
				}
				if (!txWiderstand.getText().isEmpty()) {
					resistence = Double.parseDouble(txWiderstand.getText());
					inputAmount++;
				}
				if (inputAmount > 2 || inputAmount < 2) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warnung");
					alert.setHeaderText("Ungültige Eingabe");
					alert.setContentText("Bitte geben Sie 2 physikalische Grössen ein");
					alert.showAndWait();
				} else {
					Calculator myCalculator = new Calculator(power, tension, current, resistence);
					myCalculator.calculate();

					if (power != myCalculator.getLeistung())
						txLeistung.setStyle("-fx-text-inner-color: red;");
					if (tension != myCalculator.getSpannung())
						txSpannung.setStyle("-fx-text-inner-color: red;");
					if (current != myCalculator.getStrom())
						txStrom.setStyle("-fx-text-inner-color: red;");
					if (resistence != myCalculator.getWiderstand())
						txWiderstand.setStyle("-fx-text-inner-color: red;");

					txLeistung.setText(Double.toString(myCalculator.getLeistung()));
					txSpannung.setText(Double.toString(myCalculator.getSpannung()));
					txStrom.setText(Double.toString(myCalculator.getStrom()));
					txWiderstand.setText(Double.toString(myCalculator.getWiderstand()));
				}
			});

			btnLoeschen.setOnAction(e -> {
				txLeistung.clear();
				txSpannung.clear();
				txStrom.clear();
				txWiderstand.clear();
				txLeistung.setStyle("-fx-text-inner-color: black;");
				txSpannung.setStyle("-fx-text-inner-color: black;");
				txStrom.setStyle("-fx-text-inner-color: black;");
				txWiderstand.setStyle("-fx-text-inner-color: black;");
			});

			Scene scene = new Scene(root, 330, 490);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Formelrad");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
