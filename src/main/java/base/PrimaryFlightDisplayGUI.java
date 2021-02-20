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

        // --- insert section: end

        Label frequencyLabel = new Label("Frequency : ");
        gridPane.add(frequencyLabel, 0, 2);

        Spinner<Integer> vcfSpinner = new Spinner<>();
        vcfSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
        gridPane.add(vcfSpinner, 1, 2);

        // apu
        Label apuLabel = new Label("APU : ");
        gridPane.add(apuLabel, 0, 3);

        ToggleGroup apuToggleGroup = new ToggleGroup();

        apuOffButton = new RadioButton("Off");
        apuOffButton.setToggleGroup(apuToggleGroup);
        apuOffButton.setSelected(true);
        gridPane.add(apuOffButton, 1, 3);

        apuOnButton = new RadioButton("On");
        apuOnButton.setToggleGroup(apuToggleGroup);
        apuOnButton.setSelected(false);
        gridPane.add(apuOnButton, 2, 3);

        Label apuFrequencyLabel = new Label("Frequency : ");
        gridPane.add(apuFrequencyLabel, 3, 3);

        Spinner<Integer> apuFrequencySpinner = new Spinner<>();
        apuFrequencySpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> apuFrequencySpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4000, 0);
        apuFrequencySpinner.setValueFactory(apuFrequencySpinnerValueFactory);
        gridPane.add(apuFrequencySpinner, 4, 3);

        // engine
        Label engineLabel = new Label("Engine : ");
        gridPane.add(engineLabel, 5, 3);

        ToggleGroup engineToggleGroup = new ToggleGroup();

        engineOffButton = new RadioButton("Off");
        engineOffButton.setToggleGroup(engineToggleGroup);
        engineOffButton.setSelected(true);
        gridPane.add(engineOffButton, 6, 3);

        engineOnButton = new RadioButton("On");
        engineOnButton.setToggleGroup(engineToggleGroup);
        engineOnButton.setSelected(false);
        gridPane.add(engineOnButton, 7, 3);

        Label engineFrequencyLabel = new Label("Frequency : ");
        gridPane.add(engineFrequencyLabel, 8, 3);

        Spinner<Integer> engineFrequencySpinner = new Spinner<>();
        engineFrequencySpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> engineFrequencySpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4000, 0);
        engineFrequencySpinner.setValueFactory(engineFrequencySpinnerValueFactory);
        gridPane.add(engineFrequencySpinner, 9, 3);

        CheckBox engineFireCheckbox = new CheckBox("Fire");
        gridPane.add(engineFireCheckbox, 10, 3);

        // gear
        Label gearLabel = new Label("Gear : ");
        gridPane.add(gearLabel, 0, 4);

        ToggleGroup gearToggleGroup = new ToggleGroup();

        gearOnButton = new RadioButton("Down");
        gearOnButton.setToggleGroup(gearToggleGroup);
        gearOnButton.setSelected(true);
        gridPane.add(gearOnButton, 1, 4);

        gearOffButton = new RadioButton("Up");
        gearOffButton.setToggleGroup(gearToggleGroup);
        gearOffButton.setSelected(false);
        gridPane.add(gearOffButton, 2, 4);

        Label gearBrakeLabel = new Label("Brake : ");
        gridPane.add(gearBrakeLabel, 3, 4);

        Spinner<Integer> gearBrakeSpinner = new Spinner<>();
        gearBrakeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> gearBrakeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        gearBrakeSpinner.setValueFactory(gearBrakeSpinnerValueFactory);
        gridPane.add(gearBrakeSpinner, 4, 4);

        // hydraulic_pump
        Label hydraulicPumpLabel = new Label("HydraulicPump : ");
        gridPane.add(hydraulicPumpLabel, 5, 4);

        Label hydraulicPumpBodyOilAmountLabel = new Label("Body Oil Amount");
        gridPane.add(hydraulicPumpBodyOilAmountLabel, 6, 4);

        hydraulicPumpBodyOilAmountValueLabel = new Label("0");
        gridPane.add(hydraulicPumpBodyOilAmountValueLabel, 7, 4);

        Label hydraulicPumpWingOilAmountLabel = new Label("Wing Oil Amount");
        gridPane.add(hydraulicPumpWingOilAmountLabel, 8, 4);

        hydraulicPumpWingOilAmountValueLabel = new Label("0");
        gridPane.add(hydraulicPumpWingOilAmountValueLabel, 9, 4);

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

    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
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
        tableView.refresh();
    }
}