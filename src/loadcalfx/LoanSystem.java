/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadcalfx;

import java.time.LocalTime;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Timosamedet
 */
public class LoanSystem
{
    private static Label time;

    private static final Border black = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(2)));

    static boolean answer;

    public static void resetCalculator(TextField text1, TextField text2, TextField text3, TextField text4, TextField text5, TextArea tArea)
    {
        text1.setText(null);
        text2.setText(null);
        text3.setText(null);
        text4.setText(null);
        text5.setText(null);
        tArea.setText(null);

    }

    public static boolean exitCalculator(String title, String message)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);

        Label label = new Label();
        label.setText(message);

//        create two buttons
        Button yesButton = new Button("Yes");
        Button NoButton = new Button("No");

        yesButton.setOnAction(e ->
        {
            answer = true;
            window.close();

        });

        NoButton.setOnAction(e ->
        {
            answer = false;
            window.close();

        });

        HBox newlayout = new HBox(10);
        newlayout.getChildren().addAll(yesButton, NoButton);
        newlayout.setAlignment(Pos.CENTER);
        newlayout.setBorder(black);
        newlayout.setPadding(new Insets(8, 8, 8, 8));
        newlayout.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, newlayout);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static void generateReceipt(TextField text1, TextField text2, TextField text3, TextField text4, TextField text5, TextArea tArea, String receipts)
    {
        String annualInterestRate = String.format(text1.getText());
        String numberOfYears = String.format(text2.getText());
        String loanAmount = String.format(text3.getText());

        String monthlyPayment = String.format(text4.getText());
        String totalPayment = String.format(text5.getText());

        int refs = 1325 + (int)(Math.random() + 4238);

// ............................................
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
        {
            LocalTime currentTime = LocalTime.now();
            time.setText(currentTime.getHour() + " :" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

//.......................................................
        receipts = ("\t\t Loan Mangement Systems\n" + "Reference: \t\t\t " + refs + "\n ------------------------------------------------------------------------------- \t"
                + "\n Interest rate: \t \t\t" + annualInterestRate
                + "\nRepayment years: \t\t" + numberOfYears
                + "\nLoan Amount: \t\t\t" + "$" + loanAmount
                + "\nMonthly payment:\t\t" + monthlyPayment
                + "\nTotal paymentt:\t\t " + totalPayment
                + "\n -------------------------------------------------------------------------------------------\t"
                + "\nTime: " + clock
                + "\n\n\t\t Thank you \n");

        tArea.appendText(receipts);
    }

    public static void loanCalculator(TextField text1, TextField text2, TextField text3, TextField text4, TextField text5)
    {
        double annualInterestRate;
        annualInterestRate = Double.parseDouble(text1.getText());

        double monthlyInterestRate = annualInterestRate / 1200;

        int numberOfYears = Integer.parseInt(text2.getText());
        double loanAmount = Double.parseDouble(text3.getText());

// .....................................................................
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));
        String imonthlyPayment;

        imonthlyPayment = Double.toString(monthlyPayment);
        imonthlyPayment = String.format("$ %.2f", monthlyPayment);
        text4.setText(imonthlyPayment);
// ..............................................................

        double totalPayment = monthlyPayment * numberOfYears * 12;

        String itotalPayment;
        itotalPayment = String.format("$ %.2f", totalPayment);
        text5.setText(itotalPayment);

    }
}
