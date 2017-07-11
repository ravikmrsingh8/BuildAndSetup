package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.services.commands.BuildCommand;
import com.jda.demand.devsetup.services.commands.Command;
import com.jda.demand.devsetup.services.commands.CommandExecutor;
import com.jda.demand.devsetup.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildPropertiesController implements Initializable {

    Logger logger = Logger.getLogger(getClass().getName());
    @FXML
    private Button buildButton;
    @FXML
    private Button saveButton;

    @FXML
    private TextField cisHome;
    @FXML
    private TextField envFile;
    @FXML
    private TextField buildPropFile;
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

    public TextField getBuildPropFile() {
        return buildPropFile;
    }

    public void setBuildPropFile(TextField buildPropFile) {
        this.buildPropFile = buildPropFile;
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
        Command command = new BuildCommand();
        if(getClean().isSelected())command.addArgument(Constants.CLEAN);
        if(getSystem().isSelected())command.addArgument(Constants.SYSTEM);
        if(getCustomize().isSelected())command.addArgument(Constants.CUSTOMIZE);
        if(getBeaCreateServer().isSelected())command.addArgument(Constants.BEA_CREATE_SERVER);
        if(getDemandSrc().isSelected())command.addArgument(Constants.DEMAND_SRC);
        if(getScscSrc().isSelected())command.addArgument(Constants.SCSC_SRC);
        String findBugOff = getFindBugs().isSelected() ? "false" : "true";
        Lookup.getInstance().getEnvironmentVariables().put(Constants.ENV_FINDBUGS_OFF, findBugOff);
        new CommandExecutor().setCommand(command).execute();
    }

    public void onSaveButton() {
        Preferences preferences = Preferences.getInstance();
        Map<String, Object> map = Lookup.getInstance().getVariables();
        logger.log(Level.INFO, String.format("Lookup Variables %s",Lookup.getInstance().getVariables()));

        preferences.setProperty(Constants.CIS_HOME, (String)map.get(Constants.CIS_HOME));
        preferences.setProperty(Constants.ENV_FILE, (String)map.get(Constants.ENV_FILE));
        preferences.setProperty(Constants.LIC_FILE, (String)map.get(Constants.LIC_FILE));
        preferences.save();
    }
    public void onCISHomeInput(){
        System.out.println(getCisHome().getText());
        Lookup.getInstance().getVariables().put(Constants.CIS_HOME,getCisHome().getText());
    }

    public void onBrowseLicFile() {
        File file = new FileChooser().showOpenDialog(null);
        if(file != null) {
            getLicFile().setText(file.getAbsolutePath());
            Lookup.getInstance().getVariables().put(Constants.LIC_FILE,getLicFile().getText());
        }
    }


    public void onBrowseEnvFile() {
        File file = new FileChooser().showOpenDialog(null);
        if (file == null) return;
        getEnvFile().setText(file.getAbsolutePath());
        Lookup.getInstance().getVariables().put(Constants.ENV_FILE, getEnvFile().getText());
        Lookup.getInstance().load(file.getAbsolutePath());
        initializeDependentProperties(Lookup.getInstance());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Preferences preferences = Preferences.getInstance();
        if(preferences.isExist()) {
            logger.log(Level.INFO, "Preferences found");
            preferences.load();
            String envFile = preferences.getProperty(Constants.ENV_FILE);
            logger.log(Level.INFO, "ENV_FILE"+envFile);
            if( envFile != null && !envFile.isEmpty()) {
                Lookup.getInstance().load(envFile);
                getEnvFile().setText(envFile);
                getCisHome().setText((String)Lookup.getInstance().getVariables().get(Constants.CIS_HOME));
                getLicFile().setText((String)Lookup.getInstance().getVariables().get(Constants.LIC_FILE));
                initializeDependentProperties(Lookup.getInstance());
            }
        }

        //add ValueChangeListener for cisHome Text Field
        getCisHome().textProperty().addListener((observable, oldValue, newValue) -> {
            logger.log(Level.INFO, String.format("CIS HOME %s",newValue));
            Lookup.getInstance().getVariables().put(Constants.CIS_HOME,newValue);
        });
    }

    private void initializeDependentProperties(Lookup lookup) {
        getBuildPropFile().setText(lookup.getEnvironmentVariables().get(Constants.ENV_BUILD_PROPS));
        getAdminPort().setText(lookup.getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT));
        getWebServerPort().setText(lookup.getBuildProperties().getProperty(Constants.SERVER_STANDARD_PORT));
        getLicFile().setText(lookup.getBuildProperties().getProperty(Constants.LICENSE_FILE));
    }
}
