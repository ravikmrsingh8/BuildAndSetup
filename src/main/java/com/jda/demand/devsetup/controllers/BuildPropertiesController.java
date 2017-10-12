package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.components.BuildProperties;
import com.jda.demand.devsetup.components.EnvironmentVariables;
import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.services.commands.*;
import com.jda.demand.devsetup.utils.Constants;
import com.jda.demand.devsetup.utils.Utility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteResultHandler;

import java.io.File;
import java.io.IOException;
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
    private TextField serverHostName;
    @FXML
    private TextField adminPort;
    @FXML
    private TextField webServerPort;

    @FXML
    private TextField buildPropFile;
    @FXML
    private TextField dbName;
    @FXML
    private TextField sid;
    @FXML
    private TextField licFile;

    @FXML
    private TextField configCodeArguments;
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

    public TextField getServerHostName() {
        return serverHostName;
    }

    public TextField getDbName() {
        return dbName;
    }

    public TextField getSid() {
        return sid;
    }

    public TextField getConfigCodeArguments() {
        return configCodeArguments;
    }

    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }

    public Lookup getLookup() {
        return Lookup.getInstance();
    }


    public void onPropertiesButton() {
        BuildProperties.displayProperties();
    }

    public void onEnvironmentVariablesButton() {
        EnvironmentVariables.displayVariables();
    }

    public void onEncryptPasswordButton() {
        if (!Utility.isSet(getEnvFile().getText())) return;
        executeCommand(new GenerateEncryptedPasswordCommand(), new DefaultExecuteResultHandler());
    }

    public void onReloadBuildPropsButton() {
        if (!Utility.isSet(getEnvFile().getText())) return;
        //Loading properties based on env file
        String envFile = getEnvFile().getText();
        getLogger().log(Level.INFO, "ENV_FILE : " + envFile);
        if (envFile != null && !envFile.isEmpty()) {
            getLookup().load(envFile);
            setDependentProperties();
        }
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

        getLookup().load(file.getAbsolutePath());
        setDependentProperties();
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

        if ("false".equals(findBugOff)) {
            command.addArgument(Constants.JARS);
            command.addArgument(Constants.FIND_BUGS);
        }
        executeCommand(command, new DefaultExecuteResultHandler());
    }

    public void onInstallButton() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        executeCommand(new InstallLicenseCommand(), new DefaultExecuteResultHandler());
    }

    public void onGenerateConfigCodeButton() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        executeCommand(new RunScpoTaskCommand(), new DefaultExecuteResultHandler());
    }

    public void onSetConfigCodeButton() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        executeCommand(new SetConfigCodeCommand(), new DefaultExecuteResultHandler());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        //add ValueChangeListener for LicenseFile Text Field
        getLicFile().textProperty().addListener((observable, oldValue, newValue) -> {
            getLogger().log(Level.INFO, String.format("Lic File %s", newValue));
            getLookup().getVariables().put(Constants.LIC_FILE, newValue);
        });

    }

    private void setDependentProperties() {
        getBuildPropFile().setText(getLookup().getEnvironmentVariables().get(Constants.ENV_BUILD_PROPS));
        getServerHostName().setText(getLookup().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME));
        getAdminPort().setText(getLookup().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT));
        getWebServerPort().setText(getLookup().getBuildProperties().getProperty(Constants.SERVER_STANDARD_PORT));
        getDbName().setText(getLookup().getBuildProperties().getProperty(Constants.DB_HOST_NAME));
        getSid().setText(getLookup().getBuildProperties().getProperty(Constants.ORACLE_NET_SERVICE));
        getLicFile().setText(getLookup().getBuildProperties().getProperty(Constants.LICENSE_FILE));

        String findBugs = getLookup().getEnvironmentVariables().get(Constants.ENV_FINDBUGS_OFF);
        getFindBugs().setSelected("false".equals(findBugs));
        setConfigCodeText();
    }

    private void setConfigCodeText() {
        final String ORACLE_NET_SERVICE = getLookup().getBuildProperties().getProperty(Constants.ORACLE_NET_SERVICE);
        final String USER1 = getLookup().getBuildProperties().getProperty(Constants.USER1);
        final String USER2 = getLookup().getBuildProperties().getProperty(Constants.USER2);
        final String USER1_PASS = getLookup().getBuildProperties().getProperty(Constants.USER1_PASS);
        final String USER2_PASS = getLookup().getBuildProperties().getProperty(Constants.USER2_PASS);
        String text = USER1 + " " + USER1_PASS + "@" + ORACLE_NET_SERVICE + " " + USER2 + " " + USER2_PASS + "@" + ORACLE_NET_SERVICE;
        getConfigCodeArguments().setText(text);

    }

    private void executeCommand(Command command, ExecuteResultHandler handler) {
        if (command == null) return;
        getLogger().log(Level.INFO, "Running " + command);
        try {
            DefaultExecutor executor = new DefaultExecutor();
            executor.setWorkingDirectory(command.getWorkingDirectory());
            executor.execute(command, command.getEnvironmentVariables(), handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


