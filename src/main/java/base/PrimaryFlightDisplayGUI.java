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

    // right_navigation_light
    private PrimaryFlightDisplayEntry rightNavigationLightIsOnEntry;
    private RadioButton rightNavigationLightOffButton;
    private RadioButton rightNavigationLightOnButton;

    // tail_navigation_light
    private PrimaryFlightDisplayEntry tailNavigationLightIsOnEntry;
    private RadioButton tailNavigationLightOffButton;
    private RadioButton tailNavigationLightOnButton;

    // exhaust_gas_temperature_sensor
    private PrimaryFlightDisplayEntry temperatureExhaustGasTemperatureSensorEntry;
    private PrimaryFlightDisplayEntry isAlarmMajorExhaustGasTemperatureSensorEntry;
    private PrimaryFlightDisplayEntry isAlarmCriticalExhaustGasTemperatureSensorEntry;

    private TextField temperatureExhaustGasTemperatureSensorText;
    private TextField isAlarmMajorExhaustGasTemperatureSensorText;
    private TextField isAlarmCriticalExhaustGasTemperatureSensorText;

    // fuel_flow_sensor
    private PrimaryFlightDisplayEntry fuelFlowEntry;
    private TextField fuelFlowText;

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

        // right_navigation_light
        Label rightNavigationLightLabel = new Label("RightNavigationLight : ");
        gridPane.add(rightNavigationLightLabel,6,1);

        ToggleGroup rightNavigationLightToggleGroup = new ToggleGroup();

        rightNavigationLightOffButton = new RadioButton("Off");
        rightNavigationLightOffButton.setToggleGroup(rightNavigationLightToggleGroup);
        rightNavigationLightOffButton.setSelected(true);
        gridPane.add(rightNavigationLightOffButton,7,1);

        rightNavigationLightOnButton = new RadioButton("On");
        rightNavigationLightOnButton.setToggleGroup(rightNavigationLightToggleGroup);
        rightNavigationLightOnButton.setSelected(false);
        gridPane.add(rightNavigationLightOnButton,8,1);

        // tail_navigation_light
        Label tailNavigationLightLabel = new Label("TailNavigationLight : ");
        gridPane.add(tailNavigationLightLabel,6,2);

        ToggleGroup tailNavigationLightToggleGroup = new ToggleGroup();

        tailNavigationLightOffButton = new RadioButton("Off");
        tailNavigationLightOffButton.setToggleGroup(tailNavigationLightToggleGroup);
        tailNavigationLightOffButton.setSelected(true);
        gridPane.add(tailNavigationLightOffButton,7,2);

        tailNavigationLightOnButton = new RadioButton("On");
        tailNavigationLightOnButton.setToggleGroup(tailNavigationLightToggleGroup);
        tailNavigationLightOnButton.setSelected(false);
        gridPane.add(tailNavigationLightOnButton,8,2);

        // exhaust_gas_temperature_sensor
        Label exhaustGasTemperatureSensorLabel = new Label("ExhaustGasTemperatureSensor : ");
        gridPane.add(exhaustGasTemperatureSensorLabel, 6,3);
        Label exhaustGasTemperatureLabel = new Label("ExhaustGasTemperature : ");
        gridPane.add(exhaustGasTemperatureLabel, 7, 3);
        Label isAlarmMajorExhaustGasTemperatureSensorLabel = new Label("IsAlarmMajorExhaustGas : ");
        gridPane.add(isAlarmMajorExhaustGasTemperatureSensorLabel, 9,3);
        Label isAlarmCriticalExhaustGasTemperatureSensorLabel = new Label("IsAlarmCriticalExhaustGas : ");
        gridPane.add(isAlarmCriticalExhaustGasTemperatureSensorLabel, 11,3);



        temperatureExhaustGasTemperatureSensorText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.exhaustGasTemperature));
        isAlarmMajorExhaustGasTemperatureSensorText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isAlarmMajorExhaustGasTemperatureSensor));
        isAlarmCriticalExhaustGasTemperatureSensorText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isAlarmCriticalExhaustGasTemperatureSensor));

        gridPane.add(temperatureExhaustGasTemperatureSensorText, 8,3);
        temperatureExhaustGasTemperatureSensorText.setPrefSize(50,20);
        gridPane.add(isAlarmMajorExhaustGasTemperatureSensorText,10,3);
        isAlarmMajorExhaustGasTemperatureSensorText.setPrefSize(50,20);
        gridPane.add(isAlarmCriticalExhaustGasTemperatureSensorText,12,3);
        isAlarmCriticalExhaustGasTemperatureSensorText.setPrefSize(50,20);

        // fuel_flow_sensor

        Label fuelFlowSensorLabel = new Label("FuelFlowSensor : ");
        gridPane.add(fuelFlowSensorLabel,6,4);

        ToggleGroup fuelFlowSensorToggleGroup = new ToggleGroup();

        fuelFlowText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.fuelFlow));

        gridPane.add(fuelFlowText,7,4);
        fuelFlowText.setPrefSize(50,20);


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

    // right_navigation_light
    public void setRightNavigationLightToggleGroup(boolean isRightNavigationLightOn){

        if(isRightNavigationLightOn) {
            rightNavigationLightOnButton.setSelected(true);
            rightNavigationLightOffButton.setSelected(false);
        } else {
            rightNavigationLightOnButton.setSelected(false);
            rightNavigationLightOffButton.setSelected(true);
        }
    }

    // tail_navigation_light
    public void setTailNavigationLightToggleGroup(boolean isTailNavigationLightOn){

        if(isTailNavigationLightOn) {
            tailNavigationLightOnButton.setSelected(true);
            tailNavigationLightOffButton.setSelected(false);
        } else {
            tailNavigationLightOnButton.setSelected(false);
            tailNavigationLightOffButton.setSelected(true);
        }
    }

    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);

        // right_navigation_light
        rightNavigationLightIsOnEntry = new PrimaryFlightDisplayEntry("RightNavigationLight (isOn)",Boolean.toString(PrimaryFlightDisplay.instance.isRightNavigationLightOn));
        dataList.add(rightNavigationLightIsOnEntry);

        // tail_navigation_light
        tailNavigationLightIsOnEntry = new PrimaryFlightDisplayEntry("TailNavigationLight (isOn)",Boolean.toString(PrimaryFlightDisplay.instance.isTailNavigationLightOn));
        dataList.add(tailNavigationLightIsOnEntry);

        // exhaust_gas_temperature_sensor
        temperatureExhaustGasTemperatureSensorEntry = new PrimaryFlightDisplayEntry("ExhaustGasTemperature",Integer.toString(PrimaryFlightDisplay.instance.exhaustGasTemperature));
        dataList.add(temperatureExhaustGasTemperatureSensorEntry);
        isAlarmMajorExhaustGasTemperatureSensorEntry = new PrimaryFlightDisplayEntry("IsAlarmMajorExhaustGasTemperature",Boolean.toString(PrimaryFlightDisplay.instance.isAlarmMajorExhaustGasTemperatureSensor));
        dataList.add(isAlarmMajorExhaustGasTemperatureSensorEntry);
        isAlarmCriticalExhaustGasTemperatureSensorEntry = new PrimaryFlightDisplayEntry("IsAlarmCriticalExhaustGasTemperature",Boolean.toString(PrimaryFlightDisplay.instance.isAlarmCriticalExhaustGasTemperatureSensor));
        dataList.add(isAlarmCriticalExhaustGasTemperatureSensorEntry);

        // fuel_flow_sensor
        fuelFlowEntry = new PrimaryFlightDisplayEntry("FuelFlow",Integer.toString(PrimaryFlightDisplay.instance.fuelFlow));
        dataList.add(fuelFlowEntry);

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

        // right_navigation_light
        rightNavigationLightIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isRightNavigationLightOn));
        setRightNavigationLightToggleGroup(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        // tail_navigation_light
        tailNavigationLightIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTailNavigationLightOn));
        setTailNavigationLightToggleGroup(PrimaryFlightDisplay.instance.isTailNavigationLightOn);




        tableView.refresh();
    }
}