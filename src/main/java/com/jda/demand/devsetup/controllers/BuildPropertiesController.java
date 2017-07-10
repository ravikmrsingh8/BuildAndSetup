package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.lookup.BuildProperties;
import com.jda.demand.devsetup.lookup.EnvironmentVariables;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class BuildPropertiesController implements Initializable {

    @FXML
    private Button buildButton;
    @FXML
    private Button saveButton;

    @FXML
    private TextField cisHome;
    @FXML
    private TextField envFile;
    @FXML
    private TextField builPropFile;
    @FXML
    private TextField adminPort;
    @FXML
    private TextField webServerPort;
    @FXML
    private TextField licFile;

    @FXML
    private CheckBox findBugs;
    @FXML
    private CheckBox clean;
    @FXML
    private CheckBox system;
    @FXML
    private CheckBox customize;
    @FXML
    private CheckBox beaCreateServer;
    @FXML
    private CheckBox demandSrc;
    @FXML
    private CheckBox scscSrc;

    public CheckBox getFindBugs() {
        return findBugs;
    }

    public CheckBox getClean() {
        return clean;
    }

    public CheckBox getSystem() {
        return system;
    }

    public CheckBox getCustomize() {
        return customize;
    }

    public CheckBox getBeaCreateServer() {
        return beaCreateServer;
    }

    public CheckBox getDemandSrc() {
        return demandSrc;
    }

    public CheckBox getScscSrc() {
        return scscSrc;
    }

    public Button getBuildButton() {
        return buildButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }


    public void setBuildButton(Button buildButton) {
        this.buildButton = buildButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public TextField getCisHome() {
        return cisHome;
    }

    public void setCisHome(TextField cisHome) {
        this.cisHome = cisHome;
    }

    public TextField getEnvFile() {
        return envFile;
    }

    public void setEnvFile(TextField envFile) {
        this.envFile = envFile;
    }

    public TextField getBuilPropFile() {
        return builPropFile;
    }

    public void setBuilPropFile(TextField builPropFile) {
        this.builPropFile = builPropFile;
    }

    public TextField getAdminPort() {
        return adminPort;
    }

    public void setAdminPort(TextField adminPort) {
        this.adminPort = adminPort;
    }

    public TextField getWebServerPort() {
        return webServerPort;
    }

    public void setWebServerPort(TextField webServerPort) {
        this.webServerPort = webServerPort;
    }

    public TextField getLicFile() {
        return licFile;
    }

    public void setLicFile(TextField licFile) {
        this.licFile = licFile;
    }

    public void setFindBugs(CheckBox findBugs) {
        this.findBugs = findBugs;
    }

    public void setClean(CheckBox clean) {
        this.clean = clean;
    }

    public void setSystem(CheckBox system) {
        this.system = system;
    }

    public void setCustomize(CheckBox customize) {
        this.customize = customize;
    }

    public void setBeaCreateServer(CheckBox beaCreateServer) {
        this.beaCreateServer = beaCreateServer;
    }

    public void setDemandSrc(CheckBox demandSrc) {
        this.demandSrc = demandSrc;
    }

    public void setScscSrc(CheckBox scscSrc) {
        this.scscSrc = scscSrc;
    }

    public void onBuildButton() {

    }

    public void onSaveButton() {

    }

    public void onBrowseLicFile() {
        File file = new FileChooser().showOpenDialog(null);
        getLicFile().setText(file.getAbsolutePath());
        Preferences.getInstance().load();
        Preferences.getInstance().setProperty(Constants.LIC_FILE, getLicFile().getText());
    }

    public void onBrowseEnvFile() {
        File file = new FileChooser().showOpenDialog(null);
        getEnvFile().setText(file.getAbsolutePath());
        EnvironmentVariables.getInstance().load(file.getAbsolutePath());
        String buildPropFilePath = EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_PROPS);
        if (buildPropFilePath != null) {
            initializeBuildProperties(buildPropFilePath);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences preferences = Preferences.getInstance();
        preferences.load();
        String envFilePath = preferences.getProperty(Constants.ENV_FILE);
        if (envFilePath != null) {
            getEnvFile().setText(envFilePath);
            EnvironmentVariables.getInstance().load(envFilePath);
            String buildPropFilePath = EnvironmentVariables.getInstance().getVariable(Constants.ENV_BUILD_PROPS);
            if (buildPropFilePath != null) {
                initializeBuildProperties(buildPropFilePath);
            }
        }
    }

    private void initializeBuildProperties(String buildPropFilePath) {
        getBuilPropFile().setText(buildPropFilePath);
        BuildProperties properties = BuildProperties.getInstance();
        properties.load(buildPropFilePath);
        getAdminPort().setText(properties.getProperty(Constants.SERVER_ADMIN_PORT));
        getWebServerPort().setText(properties.getProperty(Constants.SERVER_STANDARD_PORT));
    }
}
