package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.components.ToggleSwitch;
import com.jda.demand.devsetup.services.commands.CommandExecutor;
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

    public void onAdminServerToggleSwitch() {
        getCommandText().setText(null);
        if (getAdminServer().isSwitchOn()) {
            String command = CommandExecutor.execute(Constants.START_ADMIN_SERVER);
            getCommandText().setText(command);
        }
    }

    public void onWebServerToggleSwitch() {
        getCommandText().setText(null);
        if (getWebServer().isSwitchOn()) {
            String command = CommandExecutor.execute(Constants.START_WEB_SERVER);
            getCommandText().setText(command);
        }
    }

    public void onCisAgentToggleSwitch() {
        getCommandText().setText(null);
        if (getCisAgent().isSwitchOn()) {
            String command = CommandExecutor.execute(Constants.START_CIS_AGENT);
            getCommandText().setText(command);
        }
    }

    public void onSSOServerToggleSwitch() {
        getCommandText().setText(null);
        if (getSsoServer().isSwitchOn()) {
            String command = CommandExecutor.execute(Constants.START_SSO_SERVER);
            getCommandText().setText(command);
        }
    }

    public void onBasicPoolToggleSwitch() {
        getCommandText().setText(null);
        if (getBasicPool().isSwitchOn()) {
            String command = CommandExecutor.execute(Constants.START_BASIC_NODE_POOL);
            getCommandText().setText(command);
        }
    }

    public void onRMIPoolToggleSwitch() {
        getCommandText().setText(null);
        if (getRmiPool().isSwitchOn()) {
            String command = CommandExecutor.execute(Constants.START_RMI_NODE_POOL);
            getCommandText().setText(command);
        }
    }

    public void onInstallButton() {
        getCommandText().setText(CommandExecutor.execute(Constants.INSTALL_LIC));
    }

    public void onRunScpoTaskButton() {
        getCommandText().setText(CommandExecutor.execute(Constants.RUN_SCPO_TASK));
    }

    public void onSetConfigCodeButton() {
        getCommandText().setText(CommandExecutor.execute(Constants.SET_CONFIG_CODE));
    }

    public void onSRECleanupButton() {
        getCommandText().setText(CommandExecutor.execute(Constants.SRE_CLEANUP));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
