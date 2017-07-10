package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.components.ToggleSwitch;
import com.jda.demand.devsetup.lookup.BuildProperties;
import com.jda.demand.devsetup.services.commands.*;
import com.jda.demand.devsetup.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlsController implements Initializable {

    @FXML
    private ToggleSwitch adminServer;
    @FXML
    private ToggleSwitch webServer;
    @FXML
    private ToggleSwitch cisAgent;
    @FXML
    private ToggleSwitch ssoServer;

    @FXML
    private ToggleSwitch basicPool;
    @FXML
    private ToggleSwitch rmiPool;

    @FXML
    private Text statusText;
    @FXML
    private Text commandText;

    @FXML
    private Text URLText;

    private CommandExecutor commandExecutor;
    private BuildProperties properties;

    public ToggleSwitch getAdminServer() {
        return adminServer;
    }

    public ToggleSwitch getWebServer() {
        return webServer;
    }

    public ToggleSwitch getCisAgent() {
        return cisAgent;
    }

    public ToggleSwitch getSsoServer() {
        return ssoServer;
    }

    public ToggleSwitch getBasicPool() {
        return basicPool;
    }

    public ToggleSwitch getRmiPool() {
        return rmiPool;
    }

    public Text getStatusText() {
        return statusText;
    }

    public Text getCommandText() {
        return commandText;
    }


    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public BuildProperties getProperties() {
        return properties;
    }

    public void setProperties(BuildProperties properties) {
        this.properties = properties;
    }

    public Text getURLText() {
        return URLText;
    }

    public void setURLText(Text URLText) {
        this.URLText = URLText;
    }

    public void onAdminServerToggleSwitch() {
        getCommandText().setText(null);
        if (getAdminServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new AdminServerStartCommand()).execute();
            getCommandText().setText(command);

            String ADMIN_PORT = getProperties().getProperty(Constants.SERVER_ADMIN_PORT);
            String HOST_NAME = getProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://"+HOST_NAME+":"+ADMIN_PORT+"/console";
            getStatusText().setText("Admin Server Running at ");
            getURLText().setText(url);
        }
    }

    public void onWebServerToggleSwitch() {
        getCommandText().setText(null);
        if (getWebServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new ManagedServerStartCommand()).execute();
            getCommandText().setText(command);
        }
    }

    public void onCisAgentToggleSwitch() {
        getCommandText().setText(null);
        if (getCisAgent().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new CISAgentStartCommand()).execute();
            getCommandText().setText(command);
        }
    }

    public void onSSOServerToggleSwitch() {
        getCommandText().setText(null);
        if (getSsoServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new SSOServerStartCommand()).execute();
            getCommandText().setText(command);
        }
    }

    public void onBasicPoolToggleSwitch() {
        getCommandText().setText(null);
        if (getBasicPool().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new BasicNodePoolStartCommand()).execute();
            getCommandText().setText(command);
        }
    }

    public void onRMIPoolToggleSwitch() {
        getCommandText().setText(null);
        if (getRmiPool().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new RMIPoolStartCommand()).execute();
            getCommandText().setText(command);
        }
    }

    public void onInstallButton() {
        String command = getCommandExecutor().setCommand(new InstallLicenseCommand()).execute();
        getCommandText().setText(command);
    }

    public void onRunScpoTaskButton() {
        String command = getCommandExecutor().setCommand(new RunScpoTaskCommand()).execute();
        getCommandText().setText(command);
    }

    public void onSetConfigCodeButton() {
        String command = getCommandExecutor().setCommand(new SetConfigCodeCommand()).execute();
        getCommandText().setText(command);
    }

    public void onSRECleanupButton() {
        String command = getCommandExecutor().setCommand(new SRENodeCleanupCommand()).execute();
        getCommandText().setText(command);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCommandExecutor(new CommandExecutor());
        setProperties(BuildProperties.getInstance());
    }
}
