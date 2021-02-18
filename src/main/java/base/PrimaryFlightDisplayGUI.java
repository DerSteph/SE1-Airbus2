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

    // temperature_sensor
    private PrimaryFlightDisplayEntry temperatureSensorAmountBodyEntry;
    private PrimaryFlightDisplayEntry temperatureSensorAmountWingEntry;
    private PrimaryFlightDisplayEntry temperatureSensorIsAlarmBodyEntry;
    private PrimaryFlightDisplayEntry temperatureSensorIsAlarmWingEntry;

    private TextField temperatureSensorAmountBodyText;
    private TextField temperatureSensorAmountWingText;
    private TextField temperatureSensorIsAlarmBodyText;
    private TextField temperatureSensorIsAlarmWingText;

    // airflow_sensor
    private PrimaryFlightDisplayEntry airFlowSensorPressureEntry;
    private PrimaryFlightDisplayEntry airFlowSensorIsAlarmBodyEntry;
    private PrimaryFlightDisplayEntry airFlowSensorIsAlarmWingEntry;

    private TextField airFlowSensorIsAlarmBodyText;
    private TextField airFlowSensorIsAlarmWingText;
    private TextField airFlowSensorAmountText;

    // battery
    private PrimaryFlightDisplayEntry batteryPercentageEntry;

    private TextField batteryPercantageText;

    // deicing_system
    private PrimaryFlightDisplayEntry isDeIcingSystemActivatedEntry;
    private PrimaryFlightDisplayEntry amountDeIcingSystemEntry;

    private TextField deIcingSystemAmountText;
    private TextField deIcingSystemActivatedText;

    // pitot_tube
    private PrimaryFlightDisplayEntry isPitotTubeCleaned;
    private PrimaryFlightDisplayEntry velocity;

    // radar_altimeter
    private PrimaryFlightDisplayEntry isRadarAltimeterOn;
    private PrimaryFlightDisplayEntry altitudeRadarAltimeter;

    // engine_oil_tank
    private PrimaryFlightDisplayEntry levelEngineOilTank;

    // fuel_tank
    private PrimaryFlightDisplayEntry amountOfFuel;

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

        // temperature_sensor
        Label temperatureSensorBodyAlarmLabel = new Label("TemperatureSensorBodyAlarm: ");
        gridPane.add(temperatureSensorBodyAlarmLabel, 0, 4);
        Label temperatureSensorWingLabel = new Label("TemperatureSensorWingAlarm: ");
        gridPane.add(temperatureSensorWingLabel, 2, 4);
        Label temperatureSensorBodyAmountLabel = new Label("TemperatureSensorBodyAmount: ");
        gridPane.add(temperatureSensorBodyAmountLabel, 0, 6);
        Label temperatureSensorWingAmountLabel = new Label("TemperatureSensorWingAmount: ");
        gridPane.add(temperatureSensorWingAmountLabel, 2, 6);

        temperatureSensorIsAlarmBodyText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorBodyAlarm));
        temperatureSensorIsAlarmWingText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorWingAlarm));
        temperatureSensorAmountBodyText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.temperatureBody));
        temperatureSensorAmountWingText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.temperatureWing));

        gridPane.add(temperatureSensorIsAlarmBodyText, 1, 4);
        temperatureSensorIsAlarmBodyText.setPrefSize(50, 20);
        gridPane.add(temperatureSensorIsAlarmWingText, 3, 4);
        temperatureSensorIsAlarmWingText.setPrefSize(50, 20);
        gridPane.add(temperatureSensorAmountBodyText, 1, 6);
        temperatureSensorAmountBodyText.setPrefSize(50, 20);
        gridPane.add(temperatureSensorAmountWingText, 3, 6);
        temperatureSensorAmountWingText.setPrefSize(50, 20);

        // airflow_sensor
        Label airFlowSensorBodyAlarmLabel = new Label("AirFlowSensorBodyAlarm: ");
        gridPane.add(airFlowSensorBodyAlarmLabel, 0, 8);
        Label airFlowSensorWingAlarmLabel = new Label("AirFlowSensorBodyAlarm: ");
        gridPane.add(airFlowSensorWingAlarmLabel, 2, 8);
        Label airFlowSensorPressureLabel = new Label("AirFlowSensorBodyAlarm: ");
        gridPane.add(airFlowSensorPressureLabel, 4, 8);

        airFlowSensorIsAlarmBodyText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm));
        airFlowSensorIsAlarmWingText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm));
        airFlowSensorAmountText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.airPressure));

        gridPane.add(airFlowSensorIsAlarmBodyText, 1, 8);
        airFlowSensorIsAlarmBodyText.setPrefSize(50, 20);
        gridPane.add(airFlowSensorIsAlarmWingText, 3, 8);
        airFlowSensorIsAlarmWingText.setPrefSize(50, 20);
        gridPane.add(airFlowSensorAmountText, 5, 8);
        airFlowSensorAmountText.setPrefSize(50, 20);

        // battery
        Label batteryLabel = new Label("BatteryPercantage: ");
        gridPane.add(batteryLabel, 0, 10);

        batteryPercantageText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.percentageBattery));

        gridPane.add(batteryPercantageText, 1, 10);
        batteryPercantageText.setPrefSize(50, 20);

        // deicing_system
        Label deIcingSystemActivatedLabel = new Label("DeIcingSystemActivated: ");
        gridPane.add(deIcingSystemActivatedLabel, 0, 12);
        Label deIcingSystemAmountLabel = new Label("DeIcingSystemAmount: ");
        gridPane.add(deIcingSystemAmountLabel, 2, 12);

        deIcingSystemActivatedText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isDeIcingSystemActivated));
        deIcingSystemAmountText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.amountDeIcingSystem));

        gridPane.add(deIcingSystemActivatedText, 1, 12);
        deIcingSystemActivatedText.setPrefSize(50, 20);
        gridPane.add(deIcingSystemAmountText, 3, 12);
        deIcingSystemAmountText.setPrefSize(50, 20);

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

    // airflow_sensor


    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);

        // airflow_sensor
        airFlowSensorIsAlarmBodyEntry = new PrimaryFlightDisplayEntry("AirFlowSensorBody (Alarm)", Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm));
        airFlowSensorIsAlarmWingEntry = new PrimaryFlightDisplayEntry("AirFlowSensorWing (Alarm)", Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm));
        airFlowSensorPressureEntry = new PrimaryFlightDisplayEntry("AirFlowSensor (AirPressure)", Integer.toString(PrimaryFlightDisplay.instance.airPressure));
        dataList.add(airFlowSensorIsAlarmBodyEntry);
        dataList.add(airFlowSensorIsAlarmWingEntry);
        dataList.add(airFlowSensorPressureEntry);

        // temperature_sensor
        temperatureSensorIsAlarmBodyEntry = new PrimaryFlightDisplayEntry("TemperatureSensorBody (Alarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorBodyAlarm));
        temperatureSensorIsAlarmWingEntry = new PrimaryFlightDisplayEntry("TemperatureSensorWing (Alarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorWingAlarm));
        temperatureSensorAmountBodyEntry = new PrimaryFlightDisplayEntry("TemperatureSensorWing (Amount)", Integer.toString(PrimaryFlightDisplay.instance.temperatureBody));
        temperatureSensorAmountWingEntry = new PrimaryFlightDisplayEntry("TemperatureSensorWing (Amount)", Integer.toString(PrimaryFlightDisplay.instance.temperatureWing));
        dataList.add(temperatureSensorIsAlarmBodyEntry);
        dataList.add(temperatureSensorIsAlarmWingEntry);
        dataList.add(temperatureSensorAmountBodyEntry);
        dataList.add(temperatureSensorAmountWingEntry);

        // battery
        batteryPercentageEntry = new PrimaryFlightDisplayEntry("Battery (Percantage)", Integer.toString(PrimaryFlightDisplay.instance.percentageBattery));
        dataList.add(batteryPercentageEntry);

        // deicing_system
        isDeIcingSystemActivatedEntry = new PrimaryFlightDisplayEntry("DeIcingSystem (Activated)", Boolean.toString(PrimaryFlightDisplay.instance.isDeIcingSystemActivated));
        amountDeIcingSystemEntry = new PrimaryFlightDisplayEntry("DeIcingSystem (Amount)", Integer.toString(PrimaryFlightDisplay.instance.amountDeIcingSystem));
        dataList.add(isDeIcingSystemActivatedEntry);
        dataList.add(amountDeIcingSystemEntry);
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