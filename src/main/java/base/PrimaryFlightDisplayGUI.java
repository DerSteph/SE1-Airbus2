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
    private PrimaryFlightDisplayEntry slatDegreeEntry;
    private Spinner<Integer> slatSpinner;

    // left_aileron
    private PrimaryFlightDisplayEntry leftAileronDegreeEntry;
    private Spinner<Integer> leftAileronSpinner;

    // right_aileron
    private PrimaryFlightDisplayEntry rightAileronDegreeEntry;
    private Spinner<Integer> rightAileronSpinner;

    // rudder
    private PrimaryFlightDisplayEntry rudderDegreeEntry;
    private Spinner<Integer> rudderSpinner;

    // apu
    private PrimaryFlightDisplayEntry apuIsOnEntry;
    private RadioButton apuOffButton;
    private RadioButton apuOnButton;
    private PrimaryFlightDisplayEntry apuFrequencyEntry;

    // engine
    private PrimaryFlightDisplayEntry engineIsOnEntry;
    private RadioButton engineOffButton;
    private RadioButton engineOnButton;
    private PrimaryFlightDisplayEntry engineFrequencyEntry;
    private PrimaryFlightDisplayEntry engineFireEntry;

    // gear
    private PrimaryFlightDisplayEntry gearIsOnEntry;
    private RadioButton gearOffButton;
    private RadioButton gearOnButton;
    private PrimaryFlightDisplayEntry gearBrakeEntry;

    //hydraulic_pump
    private PrimaryFlightDisplayEntry hydraulicPumpBodyOilAmountEntry;
    private PrimaryFlightDisplayEntry hydraulicPumpWingOilAmountEntry;
    private Label hydraulicPumpBodyOilAmountValueLabel;
    private Label hydraulicPumpWingOilAmountValueLabel;

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

    //oxygen_bottle
    private PrimaryFlightDisplayEntry oxygenBottleEntry;
    private RadioButton oxygenBottleTakeOutButton;
    private RadioButton oxygenBottleRefillButton;

    //nitrogen_bottle
    private PrimaryFlightDisplayEntry nitrogenBottleEntry;
    private RadioButton nitrogenBottleTakeOutButton;
    private RadioButton nitrogenBottleRefillButton;

    //tcas
    private PrimaryFlightDisplayEntry tcasIsOnEntry;
    private PrimaryFlightDisplayEntry tcasIsAlarmEntry;
    private PrimaryFlightDisplayEntry tcasIsConnectedEntry;
    private PrimaryFlightDisplayEntry tcasAltitudeEntry;
    private RadioButton tcasOnButton;
    private RadioButton tcasOffButton;

    // turbulent air flow sensor
    private PrimaryFlightDisplayEntry turbulentAirFlowSensorIsAlarmEntry;
    private RadioButton turbulentAirFlowSensorOnButton;
    private RadioButton turbulentAirFlowSensorOffButton;

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

        // temperature_sensor
        Label temperatureSensorBodyAlarmLabel = new Label("TemperatureSensorBodyAlarm: ");
        gridPane.add(temperatureSensorBodyAlarmLabel, 0, 7);
        Label temperatureSensorWingLabel = new Label("TemperatureSensorWingAlarm: ");
        gridPane.add(temperatureSensorWingLabel, 2, 7);
        Label temperatureSensorBodyAmountLabel = new Label("TemperatureSensorBodyAmount: ");
        gridPane.add(temperatureSensorBodyAmountLabel, 0, 6);
        Label temperatureSensorWingAmountLabel = new Label("TemperatureSensorWingAmount: ");
        gridPane.add(temperatureSensorWingAmountLabel, 2, 6);

        temperatureSensorIsAlarmBodyText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorBodyAlarm));
        temperatureSensorIsAlarmWingText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorWingAlarm));
        temperatureSensorAmountBodyText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.temperatureBody));
        temperatureSensorAmountWingText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.temperatureWing));

        gridPane.add(temperatureSensorIsAlarmBodyText, 1, 7);
        temperatureSensorIsAlarmBodyText.setPrefSize(50, 20);
        gridPane.add(temperatureSensorIsAlarmWingText, 3, 7);
        temperatureSensorIsAlarmWingText.setPrefSize(50, 20);
        gridPane.add(temperatureSensorAmountBodyText, 1, 6);
        temperatureSensorAmountBodyText.setPrefSize(50, 20);
        gridPane.add(temperatureSensorAmountWingText, 3, 6);
        temperatureSensorAmountWingText.setPrefSize(50, 20);

        // airflow_sensor
        Label airFlowSensorBodyAlarmLabel = new Label("AirFlowSensorBodyAlarm: ");
        gridPane.add(airFlowSensorBodyAlarmLabel, 0, 13);
        Label airFlowSensorWingAlarmLabel = new Label("AirFlowSensorBodyAlarm: ");
        gridPane.add(airFlowSensorWingAlarmLabel, 2, 13);
        Label airFlowSensorPressureLabel = new Label("AirFlowSensorBodyAlarm: ");
        gridPane.add(airFlowSensorPressureLabel, 4, 13);

        airFlowSensorIsAlarmBodyText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm));
        airFlowSensorIsAlarmWingText = new TextField(Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm));
        airFlowSensorAmountText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.airPressure));

        gridPane.add(airFlowSensorIsAlarmBodyText, 1, 13);
        airFlowSensorIsAlarmBodyText.setPrefSize(50, 20);
        gridPane.add(airFlowSensorIsAlarmWingText, 3, 13);
        airFlowSensorIsAlarmWingText.setPrefSize(50, 20);
        gridPane.add(airFlowSensorAmountText, 5, 13);
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

        // apu
        Label apuLabel = new Label("APU : ");
        gridPane.add(apuLabel, 0, 14);

        ToggleGroup apuToggleGroup = new ToggleGroup();

        apuOffButton = new RadioButton("Off");
        apuOffButton.setToggleGroup(apuToggleGroup);
        apuOffButton.setSelected(true);
        gridPane.add(apuOffButton, 1, 14);

        apuOnButton = new RadioButton("On");
        apuOnButton.setToggleGroup(apuToggleGroup);
        apuOnButton.setSelected(false);
        gridPane.add(apuOnButton, 2, 14);

        Label apuFrequencyLabel = new Label("Frequency : ");
        gridPane.add(apuFrequencyLabel, 3, 14);

        Spinner<Integer> apuFrequencySpinner = new Spinner<>();
        apuFrequencySpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> apuFrequencySpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4000, 0);
        apuFrequencySpinner.setValueFactory(apuFrequencySpinnerValueFactory);
        gridPane.add(apuFrequencySpinner, 4, 14);

        // engine
        Label engineLabel = new Label("Engine : ");
        gridPane.add(engineLabel, 5, 14);

        ToggleGroup engineToggleGroup = new ToggleGroup();

        engineOffButton = new RadioButton("Off");
        engineOffButton.setToggleGroup(engineToggleGroup);
        engineOffButton.setSelected(true);
        gridPane.add(engineOffButton, 6, 14);

        engineOnButton = new RadioButton("On");
        engineOnButton.setToggleGroup(engineToggleGroup);
        engineOnButton.setSelected(false);
        gridPane.add(engineOnButton, 7, 14);

        Label engineFrequencyLabel = new Label("Frequency : ");
        gridPane.add(engineFrequencyLabel, 8, 14);

        Spinner<Integer> engineFrequencySpinner = new Spinner<>();
        engineFrequencySpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> engineFrequencySpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4000, 0);
        engineFrequencySpinner.setValueFactory(engineFrequencySpinnerValueFactory);
        gridPane.add(engineFrequencySpinner, 9, 14);

        CheckBox engineFireCheckbox = new CheckBox("Fire");
        gridPane.add(engineFireCheckbox, 10, 14);

        // gear
        Label gearLabel = new Label("Gear : ");
        gridPane.add(gearLabel, 0, 15);

        ToggleGroup gearToggleGroup = new ToggleGroup();

        gearOnButton = new RadioButton("Down");
        gearOnButton.setToggleGroup(gearToggleGroup);
        gearOnButton.setSelected(true);
        gridPane.add(gearOnButton, 1, 15);

        gearOffButton = new RadioButton("Up");
        gearOffButton.setToggleGroup(gearToggleGroup);
        gearOffButton.setSelected(false);
        gridPane.add(gearOffButton, 2, 15);

        Label gearBrakeLabel = new Label("Brake : ");
        gridPane.add(gearBrakeLabel, 3, 15);

        Spinner<Integer> gearBrakeSpinner = new Spinner<>();
        gearBrakeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> gearBrakeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        gearBrakeSpinner.setValueFactory(gearBrakeSpinnerValueFactory);
        gridPane.add(gearBrakeSpinner, 4, 15);

        // hydraulic_pump
        Label hydraulicPumpLabel = new Label("HydraulicPump : ");
        gridPane.add(hydraulicPumpLabel, 5, 15);

        Label hydraulicPumpBodyOilAmountLabel = new Label("Body Oil Amount");
        gridPane.add(hydraulicPumpBodyOilAmountLabel, 6, 15);

        hydraulicPumpBodyOilAmountValueLabel = new Label("0");
        gridPane.add(hydraulicPumpBodyOilAmountValueLabel, 7, 15);

        Label hydraulicPumpWingOilAmountLabel = new Label("Wing Oil Amount");
        gridPane.add(hydraulicPumpWingOilAmountLabel, 8, 15);

        hydraulicPumpWingOilAmountValueLabel = new Label("0");
        gridPane.add(hydraulicPumpWingOilAmountValueLabel, 9, 15);

        // slat
        Label slatLabel = new Label("Slat : ");
        gridPane.add(slatLabel, 0, 4);

        slatSpinner = new Spinner<>();
        slatSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> slatSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-60, 60, 0);
        slatSpinner.setValueFactory(slatSpinnerValueFactory);
        gridPane.add(slatSpinner, 1, 4);

        // left_aileron
        Label leftAileronLabel = new Label("LeftAileron : ");
        gridPane.add(leftAileronLabel, 2, 4);

        leftAileronSpinner = new Spinner<>();
        leftAileronSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> leftAileronSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-60, 60, 0);
        leftAileronSpinner.setValueFactory(leftAileronSpinnerValueFactory);
        gridPane.add(leftAileronSpinner, 3, 4);

        // right_aileron
        Label rightAileronLabel = new Label("RightAileron : ");
        gridPane.add(rightAileronLabel, 4, 4);

        rightAileronSpinner = new Spinner<>();
        rightAileronSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> rightAileronSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-60, 60, 0);
        rightAileronSpinner.setValueFactory(rightAileronSpinnerValueFactory);
        gridPane.add(rightAileronSpinner, 5, 4);

        // rudder
        Label rudderLabel = new Label("Rudder : ");
        gridPane.add(rudderLabel, 6, 4);

        rudderSpinner = new Spinner<>();
        rudderSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> rudderSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-60, 60, 0);
        rudderSpinner.setValueFactory(rudderSpinnerValueFactory);
        gridPane.add(rudderSpinner, 7, 4);

        // oxygen_bottle
        Label oxygenBottleLabel = new Label("OxygenBottle : ");
        gridPane.add(oxygenBottleLabel, 0, 16);

        ToggleGroup oxygenBottleToggleGroup = new ToggleGroup();
        oxygenBottleTakeOutButton = new RadioButton("Oxy TakeOut");
        oxygenBottleTakeOutButton.setToggleGroup(oxygenBottleToggleGroup);
        oxygenBottleTakeOutButton.setSelected(true);
        gridPane.add(oxygenBottleTakeOutButton, 1, 16);

        oxygenBottleRefillButton = new RadioButton("Oxy Refill");
        oxygenBottleRefillButton.setToggleGroup(weatherRadarToggleGroup);
        oxygenBottleRefillButton.setSelected(false);
        gridPane.add(oxygenBottleRefillButton, 2, 16);

        // ...

        // nitrogen_bottle
        Label nitrogenBottleLabel = new Label("NitrogenBottle : ");
        gridPane.add(nitrogenBottleLabel, 3, 16);

        ToggleGroup nitrogenBottleToggleGroup = new ToggleGroup();
        nitrogenBottleTakeOutButton = new RadioButton("Nit Takeout");
        nitrogenBottleTakeOutButton.setToggleGroup(nitrogenBottleToggleGroup);
        nitrogenBottleTakeOutButton.setSelected(true);
        gridPane.add(nitrogenBottleTakeOutButton, 4, 16);

        nitrogenBottleRefillButton = new RadioButton("Nit Refill");
        nitrogenBottleRefillButton.setToggleGroup(weatherRadarToggleGroup);
        nitrogenBottleRefillButton.setSelected(false);
        gridPane.add(nitrogenBottleRefillButton, 5, 16);
        // ...

        // tcas

        Label tcasIsOnLabel = new Label("TCAS : ");
        gridPane.add(tcasIsOnLabel, 0, 1);

        ToggleGroup tcasToggleGroup = new ToggleGroup();
        tcasOffButton = new RadioButton("Off");
        tcasOffButton.setToggleGroup(tcasToggleGroup);
        tcasOffButton.setSelected(true);
        gridPane.add(tcasOffButton, 1, 1);

        tcasOnButton = new RadioButton("On");
        tcasOnButton.setToggleGroup(tcasToggleGroup);
        tcasOnButton.setSelected(false);
        gridPane.add(tcasOnButton, 2, 1);

        Spinner<Integer> tcasSpinner = new Spinner<>();
        tcasSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> tcasSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
        tcasSpinner.setValueFactory(tcasSpinnerValueFactory);
        gridPane.add(tcasSpinner, 3, 1);

        Button tcasConnect = new Button("Connect");
        gridPane.add(tcasConnect, 4, 1);

        TextField tcasScanTextField = new TextField();
        gridPane.add(tcasScanTextField, 5, 1);

        Button tcasScanButton = new Button("Scan");
        gridPane.add(tcasScanButton, 6, 1);

        TextField tcasAltitude = new TextField();
        gridPane.add(tcasAltitude, 7, 1);

        Button tcasSetAltitude = new Button("Set Alt");
        gridPane.add(tcasSetAltitude, 8, 1);

        Button tcasDetermineAltitude = new Button("Det Alt");
        gridPane.add(tcasDetermineAltitude, 9 ,1);

        // right_navigation_light
        Label rightNavigationLightLabel = new Label("RightNavigationLight : ");
        gridPane.add(rightNavigationLightLabel,6,5);

        ToggleGroup rightNavigationLightToggleGroup = new ToggleGroup();

        rightNavigationLightOffButton = new RadioButton("Off");
        rightNavigationLightOffButton.setToggleGroup(rightNavigationLightToggleGroup);
        rightNavigationLightOffButton.setSelected(true);
        gridPane.add(rightNavigationLightOffButton,7,5);

        rightNavigationLightOnButton = new RadioButton("On");
        rightNavigationLightOnButton.setToggleGroup(rightNavigationLightToggleGroup);
        rightNavigationLightOnButton.setSelected(false);
        gridPane.add(rightNavigationLightOnButton,8,5);

        // tail_navigation_light
        Label tailNavigationLightLabel = new Label("TailNavigationLight : ");
        gridPane.add(tailNavigationLightLabel,6,6);

        ToggleGroup tailNavigationLightToggleGroup = new ToggleGroup();

        tailNavigationLightOffButton = new RadioButton("Off");
        tailNavigationLightOffButton.setToggleGroup(tailNavigationLightToggleGroup);
        tailNavigationLightOffButton.setSelected(true);
        gridPane.add(tailNavigationLightOffButton,7,6);

        tailNavigationLightOnButton = new RadioButton("On");
        tailNavigationLightOnButton.setToggleGroup(tailNavigationLightToggleGroup);
        tailNavigationLightOnButton.setSelected(false);
        gridPane.add(tailNavigationLightOnButton,8,6);

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
        gridPane.add(fuelFlowSensorLabel,6,7);

        ToggleGroup fuelFlowSensorToggleGroup = new ToggleGroup();

        fuelFlowText = new TextField(Integer.toString(PrimaryFlightDisplay.instance.fuelFlow));

        gridPane.add(fuelFlowText,7,7);
        fuelFlowText.setPrefSize(50,20);

        Button tcasAlarm = new Button("Alarm");
        gridPane.add(tcasAlarm, 10 ,1);


        // TurbulentAirFlowSensor
        Label turbulentAirFlowSensorLabel = new Label("TurbulentAirFlowSensor (isAlarm) : ");
        gridPane.add(turbulentAirFlowSensorLabel, 0, 3);

        ToggleGroup turbulentAirFlowSensorToggleGroup = new ToggleGroup();
        turbulentAirFlowSensorOffButton = new RadioButton("True");
        turbulentAirFlowSensorOffButton.setToggleGroup(turbulentAirFlowSensorToggleGroup);
        turbulentAirFlowSensorOffButton.setSelected(true);
        gridPane.add(turbulentAirFlowSensorOffButton, 1, 3);

        turbulentAirFlowSensorOnButton = new RadioButton("False");
        turbulentAirFlowSensorOnButton.setToggleGroup(turbulentAirFlowSensorToggleGroup);
        turbulentAirFlowSensorOnButton.setSelected(false);
        gridPane.add(turbulentAirFlowSensorOnButton, 2, 3);

        // --- insert section: end

        Label frequencyLabel = new Label("Frequency : ");
        gridPane.add(frequencyLabel, 0, 5);

        Spinner<Integer> vcfSpinner = new Spinner<>();
        vcfSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
        gridPane.add(vcfSpinner, 1, 5);

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

    // tcas
    public void setTCASToggleGroup(boolean isTCASOn) {
        if(isTCASOn)
        {
            tcasOffButton.setSelected(false);
            tcasOnButton.setSelected(true);

        }
        else
        {
            tcasOffButton.setSelected(true);
            tcasOnButton.setSelected(false);
        }
    }

    // turbulent air flow sensor
    public void setTurbulentAirFlowSensor(boolean isTurbulentAirFlowSensor) {
        if(isTurbulentAirFlowSensor)
        {
            turbulentAirFlowSensorOffButton.setSelected(false);
            turbulentAirFlowSensorOnButton.setSelected(true);
        }
        else
        {
            turbulentAirFlowSensorOffButton.setSelected(true);
            turbulentAirFlowSensorOnButton.setSelected(false);
        }
    }


    // airflow_sensor


    // apu
    public void setAPUToggleGroup(boolean isAPUOn) {
        if (isAPUOn) {
            apuOffButton.setSelected(false);
            apuOnButton.setSelected(true);
        } else {
            apuOffButton.setSelected(true);
            apuOnButton.setSelected(false);
        }
    }

    // engine
    public void setEngineToggleGroup(boolean isEngineOn) {
        if (isEngineOn) {
            engineOffButton.setSelected(false);
            engineOnButton.setSelected(true);
        } else {
            engineOffButton.setSelected(true);
            engineOnButton.setSelected(false);
        }
    }

    // gear
    public void setGearToggleGroup(boolean isGearOn) {
        if (isGearOn) {
            gearOffButton.setSelected(false);
            gearOnButton.setSelected(true);
        } else {
            gearOffButton.setSelected(true);
            gearOnButton.setSelected(false);
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

        // tcas
        tcasIsOnEntry = new PrimaryFlightDisplayEntry("TCAS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASOn));
        tcasIsAlarmEntry = new PrimaryFlightDisplayEntry("TCAS (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASAlarm));
        tcasIsConnectedEntry = new PrimaryFlightDisplayEntry("TCAS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASConnected));
        tcasAltitudeEntry = new PrimaryFlightDisplayEntry("TCAS (Altitude)", Integer.toString(PrimaryFlightDisplay.instance.altitudeTCAS));

        dataList.add(tcasIsOnEntry);
        dataList.add(tcasIsAlarmEntry);
        dataList.add(tcasIsConnectedEntry);
        dataList.add(tcasAltitudeEntry);

        // turbulent air flow sensor
        turbulentAirFlowSensorIsAlarmEntry = new PrimaryFlightDisplayEntry("TurbulentAirFlowSensor (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm));
        dataList.add(turbulentAirFlowSensorIsAlarmEntry);

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

        // apu
        apuIsOnEntry = new PrimaryFlightDisplayEntry("APU (isAPUStarted)", Boolean.toString(PrimaryFlightDisplay.instance.isAPUStarted));
        dataList.add(apuIsOnEntry);
        apuFrequencyEntry = new PrimaryFlightDisplayEntry("APU (rpmAPU)", Integer.toString(PrimaryFlightDisplay.instance.rpmAPU));
        dataList.add(apuFrequencyEntry);

        // engine
        engineIsOnEntry = new PrimaryFlightDisplayEntry("Engine (isEngineStarted)", Boolean.toString(PrimaryFlightDisplay.instance.isEngineStarted));
        dataList.add(engineIsOnEntry);
        engineFrequencyEntry = new PrimaryFlightDisplayEntry("Engine (rpmEngine)", Integer.toString(PrimaryFlightDisplay.instance.rpmEngine));
        dataList.add(engineFrequencyEntry);
        engineFireEntry = new PrimaryFlightDisplayEntry("Engine (isEngineFire)", Boolean.toString(PrimaryFlightDisplay.instance.isEngineFire));
        dataList.add(engineFireEntry);

        // gear
        gearIsOnEntry = new PrimaryFlightDisplayEntry("Gear (isGearDown)", Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        dataList.add(gearIsOnEntry);
        gearBrakeEntry = new PrimaryFlightDisplayEntry("Gear (gearBrakePercentage)", Integer.toString(PrimaryFlightDisplay.instance.gearBrakePercentage));
        dataList.add(gearBrakeEntry);

        // hydraulic_pump
        hydraulicPumpBodyOilAmountEntry = new PrimaryFlightDisplayEntry("HydraulicPump (hydraulicPumpBodyOilAmount)", Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount));
        dataList.add(hydraulicPumpBodyOilAmountEntry);
        hydraulicPumpWingOilAmountEntry = new PrimaryFlightDisplayEntry("HydraulicPump (hydraulicPumpWingOilAmount)", Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount));
        dataList.add(hydraulicPumpWingOilAmountEntry);

        // slat
        slatDegreeEntry = new PrimaryFlightDisplayEntry("Slat (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeSlat));
        dataList.add(slatDegreeEntry);

        // left_aileron
        leftAileronDegreeEntry = new PrimaryFlightDisplayEntry("LeftAileron (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeLeftAileron));
        dataList.add(leftAileronDegreeEntry);

        // right_aileron
        rightAileronDegreeEntry = new PrimaryFlightDisplayEntry("RightAileron (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeRightAileron));
        dataList.add(rightAileronDegreeEntry);

        // rudder
        rudderDegreeEntry = new PrimaryFlightDisplayEntry("Rudder (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeRudder));
        dataList.add(rudderDegreeEntry);


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

        // apu
        apuIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAPUStarted));
        setAPUToggleGroup(PrimaryFlightDisplay.instance.isAPUStarted);
        apuFrequencyEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.rpmAPU));

        //engine
        engineIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isEngineStarted));
        setEngineToggleGroup(PrimaryFlightDisplay.instance.isEngineStarted);
        engineFrequencyEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.rpmEngine));
        engineFireEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isEngineFire));

        // gear
        gearIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        setGearToggleGroup(PrimaryFlightDisplay.instance.isGearDown);
        gearBrakeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.gearBrakePercentage));

        // hydraulic_pump
        hydraulicPumpBodyOilAmountEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount));
        hydraulicPumpBodyOilAmountValueLabel.setText(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount));
        hydraulicPumpWingOilAmountEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount));
        hydraulicPumpWingOilAmountValueLabel.setText(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount));

        // slat
        slatDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeSlat));

        // left_aileron
        leftAileronDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeLeftAileron));

        // right_aileron
        rightAileronDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeRightAileron));

        // rudder
        rudderDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeRudder));

        // tcas
        tcasIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTCASOn));
        tcasIsConnectedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTCASConnected));
        tcasIsAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTCASAlarm));
        tcasAltitudeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.altitudeTCAS));
        setTCASToggleGroup(PrimaryFlightDisplay.instance.isTCASOn);

        // turbulent air flow sensor
        turbulentAirFlowSensorIsAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm));
        setTurbulentAirFlowSensor(PrimaryFlightDisplay.instance.isTCASAlarm);

        // right_navigation_light
        rightNavigationLightIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isRightNavigationLightOn));
        setRightNavigationLightToggleGroup(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        // tail_navigation_light
        tailNavigationLightIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTailNavigationLightOn));
        setTailNavigationLightToggleGroup(PrimaryFlightDisplay.instance.isTailNavigationLightOn);




        tableView.refresh();
    }
}