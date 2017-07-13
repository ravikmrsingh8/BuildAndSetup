package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.Main;
import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.lookup.Preferences;
import com.jda.demand.devsetup.services.commands.BuildCommand;
import com.jda.demand.devsetup.services.commands.Command;
import com.jda.demand.devsetup.services.commands.CommandExecutor;
import com.jda.demand.devsetup.utils.Constants;
import com.jda.demand.devsetup.utils.Utility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildPropertiesController implements Initializable {


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


    Logger logger = null;
    Lookup lookup = null;

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

    public TextField getCisHome() {
        return cisHome;
    }

    public TextField getEnvFile() {
        return envFile;
    }

    public TextField getBuildPropFile() {
        return buildPropFile;
    }

    public TextField getAdminPort() {
        return adminPort;
    }

    public TextField getWebServerPort() {
        return webServerPort;
    }

    public TextField getLicFile() {
        return licFile;
    }




    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public void onBuildButton() {
        if (!Utility.isSet(getEnvFile().getText())) return;
        Command command = new BuildCommand();
        if (getClean().isSelected()) command.addArgument(Constants.CLEAN);
        if (getSystem().isSelected()) command.addArgument(Constants.SYSTEM);
        if (getCustomize().isSelected()) command.addArgument(Constants.CUSTOMIZE);
        if (getBeaCreateServer().isSelected()) command.addArgument(Constants.BEA_CREATE_SERVER);
        if (getDemandSrc().isSelected()) command.addArgument(Constants.DEMAND_SRC);
        if (getScscSrc().isSelected()) command.addArgument(Constants.SCSC_SRC);
        String findBugOff = getFindBugs().isSelected() ? "false" : "true";
        getLookup().getEnvironmentVariables().put(Constants.ENV_FINDBUGS_OFF, findBugOff);
        new CommandExecutor().setCommand(command).execute();
    }

    public void onSaveButton() {
        Preferences preferences = Preferences.getInstance();
        getLogger().log(Level.INFO, String.format("Lookup Variables %s", getLookup().getVariables()));
        Lookup.getInstance().getVariables().forEach((key, value) -> {
            if (Constants.CIS_HOME.equals(key)) preferences.setProperty(Constants.CIS_HOME, (String) value);
            if (Constants.ENV_FILE.equals(key)) preferences.setProperty(Constants.ENV_FILE, (String) value);
            //if (Constants.LIC_FILE.equals(key)) preferences.setProperty(Constants.LIC_FILE, (String) value);
        });
        preferences.save();
    }


    public void onBrowseLicFile() {
        File file = new FileChooser().showOpenDialog(null);
        if (file != null) {
            getLicFile().setText(file.getAbsolutePath());
            Lookup.getInstance().getVariables().put(Constants.LIC_FILE, getLicFile().getText());
        }
    }


    public void onBrowseEnvFile() {
        File file = new FileChooser().showOpenDialog(null);
        if (file == null) return;
        getEnvFile().setText(file.getAbsolutePath());
        getLookup().getVariables().put(Constants.ENV_FILE, getEnvFile().getText());
        getLookup().load(file.getAbsolutePath());
        setDependentProperties();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setLogger(Logger.getLogger(getClass().getName()));
        setLookup(Lookup.getInstance());

        //update UI according to preferences
        getLookup().getVariables().forEach((key, value) -> {
            if (Constants.CIS_HOME.equals(key)) getCisHome().setText((String) value);
            if (Constants.ENV_FILE.equals(key)) getEnvFile().setText((String) value);
            if (Constants.LIC_FILE.equals(key)) getLicFile().setText((String) value);
        });

        //Loading properties based on env file
        String envFile = (String) getLookup().getVariables().get(Constants.ENV_FILE);
        getLogger().log(Level.INFO, "ENV_FILE : " + envFile);
        if (envFile != null && !envFile.isEmpty()) {
            getLookup().load(envFile);
            setDependentProperties();
        }


        //add ValueChangeListener for cisHome Text Field
        getCisHome().textProperty().addListener((observable, oldValue, newValue) -> {
            getLogger().log(Level.INFO, String.format("CIS HOME %s", newValue));
            getLookup().getVariables().put(Constants.CIS_HOME, newValue);
        });
    }

    private void setDependentProperties() {
        getBuildPropFile().setText(getLookup().getEnvironmentVariables().get(Constants.ENV_BUILD_PROPS));
        getAdminPort().setText(getLookup().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT));
        getWebServerPort().setText(getLookup().getBuildProperties().getProperty(Constants.SERVER_STANDARD_PORT));
        getLicFile().setText(getLookup().getBuildProperties().getProperty(Constants.LICENSE_FILE));
        String findBugs = getLookup().getEnvironmentVariables().get(Constants.ENV_FINDBUGS_OFF);
        getFindBugs().setSelected("false".equals(findBugs));
    }

}
