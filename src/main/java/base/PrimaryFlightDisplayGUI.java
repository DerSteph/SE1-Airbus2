package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.util.ArrayList;

public class PrimaryFlightDisplayGUI extends Application {

    private TableView tableView;
    private ArrayList<PrimaryFlightDisplayEntry> dataList;
    private ObservableList data;

    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;

    // slat
    private PrimaryFlightDisplayEntry slatEntry;

    //wastewater_tank
    private PrimaryFlightDisplayEntry wasteWaterTankEntry;

    //portable_watertank
    private PrimaryFlightDisplayEntry portableWaterTankEntry;

    //oxygen_bottle
    private PrimaryFlightDisplayEntry oxygenBottleEntry;
    private RadioButton oxygenBottleTakeOutButton;
    private RadioButton oxygenBottleRefillButton;

    //nitrogen_bottle
    private PrimaryFlightDisplayEntry nitrogenBottleEntry;
    private RadioButton nitrogenBottleTakeOutButton;
    private RadioButton nitrogenBottleRefillButton;

    //vhf
    private PrimaryFlightDisplayEntry vhfEntry;

    //sat_com
    private PrimaryFlightDisplayEntry satComEntry;

    //tcas
    private PrimaryFlightDisplayEntry tcasEntry;
    private RadioButton tcasOnButton;
    private RadioButton tcasOffButton;

    public static void main(String... args) {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        Application.launch(args);

        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("A350 - Primary Flight Display");

        Airplane airplane = new Airplane();
        airplane.build();

        Cockpit cockpit = new Cockpit(airplane);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        Button startupButton = new Button("Startup");
        startupButton.setPrefSize(75, 20);

        startupButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.startup();
                update();
            }
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.taxi();
                update();
            }
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.takeoff();
                update();
            }
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.climbing();
                update();
            }
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.rightTurn();
                update();
            }
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.leftTurn();
                update();
            }
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.descent();
                update();
            }
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.landing();
                update();
            }
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.shutdown();
                update();
            }
        });

        hBox.getChildren().addAll(startupButton, taxiButton, takeoffButton, climbingButton, rightTurnButton,
                leftTurnButton, descentButton, landingButton, shutdownButton);

        TabPane tabPane = new TabPane();

        Tab visualTab = new Tab();
        visualTab.setText("Visual");
        visualTab.setContent(buildVisualView());
        tabPane.getTabs().add(visualTab);

        Tab tableTab = new Tab();
        tableTab.setText("Table");
        buildTableView();
        tableTab.setContent(tableView);
        tabPane.getTabs().add(tableTab);

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(hBox, tabPane);

        Scene scene = new Scene(vbox, 850, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane buildVisualView() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.BASELINE_LEFT);

        Label frontGearLabel = new Label("Front Gear : ");
        gridPane.add(frontGearLabel, 0, 0);

        ComboBox<String> frontGearComboBox = new ComboBox<>();
        frontGearComboBox.getItems().addAll("down", "up");
        frontGearComboBox.setValue("down");
        frontGearComboBox.setEditable(false);
        gridPane.add(frontGearComboBox, 1, 0);

        Label rearGearLabel = new Label("Rear Gear : ");
        gridPane.add(rearGearLabel, 2, 0);

        ComboBox<String> rearGearComboBox = new ComboBox<>();
        rearGearComboBox.getItems().addAll("down", "up");
        rearGearComboBox.setValue("down");
        rearGearComboBox.setEditable(false);
        gridPane.add(rearGearComboBox, 3, 0);

        Label flapLabel = new Label("Flap : ");
        gridPane.add(flapLabel, 4, 0);

        ComboBox<String> flapComboBox = new ComboBox<>();
        flapComboBox.getItems().addAll("neutral", "one", "two", "three");
        flapComboBox.setValue("neutral");
        flapComboBox.setEditable(false);
        gridPane.add(flapComboBox, 5, 0);

        // --- insert section: begin

        // weather_radar
        Label weatherRadarLabel = new Label("WeatherRadar : ");
        gridPane.add(weatherRadarLabel, 6, 0);

        ToggleGroup weatherRadarToggleGroup = new ToggleGroup();

        weatherRadarOffButton = new RadioButton("Off");
        weatherRadarOffButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOffButton.setSelected(true);
        gridPane.add(weatherRadarOffButton, 7, 0);

        weatherRadarOnButton = new RadioButton("On");
        weatherRadarOnButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOnButton.setSelected(false);
        gridPane.add(weatherRadarOnButton, 8, 0);

        // slat
        Label slatLabel = new Label("Slat : ");
        gridPane.add(slatLabel, 9, 0);
        // ...

        // wastewater_tank
        Label wasteWaterTankLabel = new Label("WasteWaterTank : ");
        gridPane.add(wasteWaterTankLabel, 10, 0);
        // ...

        // portable_watertank
        Label portableWaterTankLabel = new Label("PortableWaterTank : ");
        gridPane.add(portableWaterTankLabel, 11, 0);
        // ...

        // oxygen_bottle
        Label oxygenBottleLabel = new Label("OxygenBottle : ");
        gridPane.add(oxygenBottleLabel, 12, 0);

        ToggleGroup oxygenBottleToggleGroup = new ToggleGroup();
        oxygenBottleTakeOutButton = new RadioButton("Oxy TakeOut");
        oxygenBottleTakeOutButton.setToggleGroup(oxygenBottleToggleGroup);
        oxygenBottleTakeOutButton.setSelected(true);
        gridPane.add(oxygenBottleTakeOutButton, 13, 0);

        oxygenBottleRefillButton = new RadioButton("Oxy Refill");
        oxygenBottleRefillButton.setToggleGroup(weatherRadarToggleGroup);
        oxygenBottleRefillButton.setSelected(false);
        gridPane.add(oxygenBottleRefillButton, 14, 0);

        // ...

        // nitrogen_bottle
        Label nitrogenBottleLabel = new Label("NitrogenBottle : ");
        gridPane.add(nitrogenBottleLabel, 15, 0);

        ToggleGroup nitrogenBottleToggleGroup = new ToggleGroup();
        nitrogenBottleTakeOutButton = new RadioButton("Nit Takeout");
        nitrogenBottleTakeOutButton.setToggleGroup(nitrogenBottleToggleGroup);
        nitrogenBottleTakeOutButton.setSelected(true);
        gridPane.add(nitrogenBottleTakeOutButton, 16, 0);

        nitrogenBottleRefillButton = new RadioButton("Nit Refill");
        nitrogenBottleRefillButton.setToggleGroup(weatherRadarToggleGroup);
        nitrogenBottleRefillButton.setSelected(false);
        gridPane.add(nitrogenBottleRefillButton, 17, 0);
        // ...

        // vhf
        Label vhfLabel = new Label("VHF : ");
        gridPane.add(vhfLabel, 18, 0);
        // ...

        // sat_com
        Label satComLabel = new Label("VHF : ");
        gridPane.add(satComLabel, 19, 0);
        // ...

        // tcas

        Label tcasLabel = new Label("TCAS : ");
        gridPane.add(tcasLabel, 20, 0);

        ToggleGroup tcasToggleGroup = new ToggleGroup();
        tcasOffButton = new RadioButton("Off");
        tcasOffButton.setToggleGroup(tcasToggleGroup);
        tcasOffButton.setSelected(true);
        gridPane.add(tcasOffButton, 21, 0);

        tcasOnButton = new RadioButton("On");
        tcasOnButton.setToggleGroup(weatherRadarToggleGroup);
        tcasOnButton.setSelected(false);
        gridPane.add(tcasOnButton, 22, 0);

        // --- insert section: end

        Label frequencyLabel = new Label("Frequency : ");
        gridPane.add(frequencyLabel, 0, 2);

        Spinner<Integer> vcfSpinner = new Spinner<>();
        vcfSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
        gridPane.add(vcfSpinner, 1, 2);

        return gridPane;
    }

    public void buildTableView() {
        tableView = new TableView();
        data = getInitialTableData();
        tableView.setItems(data);

        TableColumn attributeColumn = new TableColumn("attribute");
        attributeColumn.setCellValueFactory(new PropertyValueFactory("attribute"));

        TableColumn valueColumn = new TableColumn("value");
        valueColumn.setCellValueFactory(new PropertyValueFactory("value"));

        tableView.getColumns().setAll(attributeColumn, valueColumn);
        tableView.setPrefWidth(450);
        tableView.setPrefHeight(450);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // weather_radar
    public void setWeatherRadarToggleGroup(boolean isWeatherRadarOn) {
        if (isWeatherRadarOn) {
            weatherRadarOffButton.setSelected(false);
            weatherRadarOnButton.setSelected(true);
        } else {
            weatherRadarOffButton.setSelected(true);
            weatherRadarOnButton.setSelected(false);
        }
    }

    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
    }

    private ObservableList getInitialTableData() {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update() {
        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);


        
        tableView.refresh();
    }
}