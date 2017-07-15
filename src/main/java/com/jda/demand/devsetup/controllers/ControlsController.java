package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.components.ToggleSwitch;
import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.services.commands.*;
import com.jda.demand.devsetup.utils.Constants;
import com.jda.demand.devsetup.utils.Utility;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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
    private VBox commandBox;
    @FXML
    private Text statusText;
    @FXML
    private Text commandText;

    @FXML
    private Hyperlink hyperlinkURL;

    @FXML
    private Lookup lookup;

    public VBox getCommandBox() {
        return commandBox;
    }

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
        return new CommandExecutor();
    }


    public Hyperlink getHyperlinkURL() {
        return hyperlinkURL;
    }


    public Lookup getLookup() {
        return lookup;
    }

    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    public void onAdminServerToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getAdminServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new AdminServerStartCommand()).execute();
            getCommandText().setText(command);
            getCommandBox().setVisible(true);
            String ADMIN_PORT = getLookup().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT);
            String HOST_NAME = getLookup().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://" + HOST_NAME + ":" + ADMIN_PORT + "/console";
            getStatusText().setText("Admin Server Running at ");
            getHyperlinkURL().setText(url);
        }
    }

    public void onWebServerToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getWebServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new ManagedServerStartCommand()).execute();
            getCommandText().setText(command);
            getCommandBox().setVisible(true);
            String WEB_SERVER_PORT = getLookup().getBuildProperties().getProperty(Constants.SERVER_STANDARD_PORT);
            String HOST_NAME = getLookup().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://" + HOST_NAME + ":" + WEB_SERVER_PORT + "/";
            getStatusText().setText("Web Server Running at ");
            getHyperlinkURL().setText(url);
        }
    }

    public void onCisAgentToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.CIS_HOME)) return;
        resetLastExecutedCommand();
        if (getCisAgent().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new CISAgentStartCommand()).execute();
            getCommandText().setText(command);
            getCommandBox().setVisible(true);
        }
    }

    public void onSSOServerToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.CIS_HOME)) return;
        resetLastExecutedCommand();
        if (getSsoServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new SSOServerStartCommand()).execute();
            getCommandText().setText(command);
            getCommandBox().setVisible(true);
        }
    }

    public void onBasicPoolToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getBasicPool().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new BasicNodePoolStartCommand()).execute();
            getCommandText().setText(command);
            getCommandBox().setVisible(true);
        }
    }

    public void onRMIPoolToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getRmiPool().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new RMIPoolStartCommand()).execute();
            getCommandText().setText(command);
            getCommandBox().setVisible(true);
        }
    }

    public void onHyperLinkURLClick() {
        Application application = (Application) getLookup().getVariables().get(Constants.MAIN_APP);
        application.getHostServices().showDocument(getHyperlinkURL().getText());
    }

    private void resetLastExecutedCommand() {
        getCommandBox().setVisible(false);
        getCommandText().setText(null);
        getStatusText().setText(null);
        getHyperlinkURL().setText(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lookup = Lookup.getInstance();
    }
}
