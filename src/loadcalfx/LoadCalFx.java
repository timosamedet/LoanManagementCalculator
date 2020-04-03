/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadcalfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoadCalFx extends Application
{

    private static final Border black = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(8), new BorderWidths(2)));
    Button loanButton;
    Button resetButton;
    Button genButton;
    Button exitButton;
    String receipts;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage)
    {

        primaryStage.setTitle("Loan Management Calculator");
        primaryStage.setOnCloseRequest(e ->
        {
            e.consume();
            boolean result = LoanSystem.exitCalculator("Exit System", "Sure you want to close the window ?");
            if(result == true)
                primaryStage.close();

        });

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 695, 365);

        Label lbTitle = new Label();
        lbTitle.setText("LOAN MANAGEMENT CALCULATOR");
        lbTitle.setTextFill(Color.BLACK);
        lbTitle.setFont(new Font(20));

        StackPane sPane = new StackPane();
        sPane.getChildren().addAll(lbTitle);
        sPane.setPadding(new Insets(5, 5, 5, 5));
        sPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        sPane.setPrefWidth(500);
        sPane.setBorder(black);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(20);
        gridPane.setHgap(15);

        //Annual Interest label
        Label annualInterestlb = new Label("Annual Interest:");
        annualInterestlb.setTextFill(Color.BLACK);
        GridPane.setConstraints(annualInterestlb, 0, 0);

        // Annual Interest input
        TextField annualIneterestInput = new TextField();
        annualIneterestInput.setPromptText("Enter Annual Interest");
        GridPane.setConstraints(annualIneterestInput, 1, 0);

        //Years label
        Label yearslb = new Label("Years");
        yearslb.setTextFill(Color.BLACK);
        GridPane.setConstraints(yearslb, 0, 1);

        // Number of years input
        TextField yearsInput = new TextField();
        yearsInput.setPromptText("Enter Number of years");
        GridPane.setConstraints(yearsInput, 1, 1);

        //LoanAmount label
        Label loanAmoutnlb = new Label("Loan Amount");
        loanAmoutnlb.setTextFill(Color.BLACK);
        GridPane.setConstraints(loanAmoutnlb, 0, 2);

        // LoanAmount input
        TextField loanAmountInput = new TextField();
        loanAmountInput.setPromptText("Enter Loan Amount");
        GridPane.setConstraints(loanAmountInput, 1, 2);

        //MonthlyPayment label
        Label MonthlyPaymentlb = new Label("Monthly Payment");
        MonthlyPaymentlb.setTextFill(Color.BLACK);
        GridPane.setConstraints(MonthlyPaymentlb, 0, 3);

        // MonthlyPayment input
        TextField monthlyPaymentInput = new TextField();
        monthlyPaymentInput.setPromptText("Enter Monthly Payment");
        GridPane.setConstraints(monthlyPaymentInput, 1, 3);

        //TotalPayment label
        Label totalPaymentlb = new Label("Total Payment");
        totalPaymentlb.setTextFill(Color.BLACK);
        GridPane.setConstraints(totalPaymentlb, 0, 4);

        // TotalPayment Input
        TextField totalPaymentInput = new TextField();
        totalPaymentInput.setPromptText("Enter Total Payment");
        GridPane.setConstraints(totalPaymentInput, 1, 4);

        gridPane.getChildren().addAll(annualInterestlb, annualIneterestInput, yearslb, yearsInput, loanAmoutnlb, loanAmountInput, MonthlyPaymentlb, monthlyPaymentInput, totalPaymentlb, totalPaymentInput);

        TextArea textArea = new TextArea();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(textArea);
        scrollPane.setMaxHeight(220);

        loanButton = new Button("Loan Calculator");
        loanButton.setFont(new Font(15));
        loanButton.setMaxWidth(Double.MAX_VALUE);
        loanButton.setPrefWidth(200);
        loanButton.setOnAction(e -> LoanSystem.loanCalculator(annualIneterestInput, yearsInput, loanAmountInput, monthlyPaymentInput, totalPaymentInput));

        resetButton = new Button(" Reset Calculator");
        resetButton.setFont(new Font(15));
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setPrefWidth(200);
        resetButton.setOnAction(e -> LoanSystem.resetCalculator(annualIneterestInput, yearsInput, loanAmountInput, monthlyPaymentInput, totalPaymentInput, textArea));

        genButton = new Button("Generate Receipts");
        genButton.setFont(new Font(15));
        genButton.setMaxWidth(Double.MAX_VALUE);
        genButton.setPrefWidth(200);
        genButton.setOnAction(e -> LoanSystem.generateReceipt(annualIneterestInput, yearsInput, loanAmountInput, monthlyPaymentInput, totalPaymentInput, textArea, receipts));

        exitButton = new Button(" Exit Calculator");
        exitButton.setFont(new Font(15));
        exitButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setPrefWidth(200);
        exitButton.setOnAction(e ->
        {

            boolean result = LoanSystem.exitCalculator("Exit System", "Sure you want to close the window ?");
            if(result)
                primaryStage.close();

        });

        HBox hBox = new HBox(25);
        hBox.getChildren().addAll(loanButton, resetButton, genButton, exitButton);
        hBox.setBorder(black);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(5));
        hBox.setPrefSize(300, 30);

        Insets topInsets = new Insets(5);
        borderPane.setTop(sPane);
        BorderPane.setMargin(sPane, topInsets);
        borderPane.setLeft(gridPane);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(hBox);
        borderPane.setPadding(new Insets(10));
        borderPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
