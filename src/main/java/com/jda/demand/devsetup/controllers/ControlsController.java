package com.jda.demand.devsetup.controllers;

import com.jda.demand.devsetup.components.ToggleSwitch;
import com.jda.demand.devsetup.lookup.Lookup;
import com.jda.demand.devsetup.services.commands.*;
import com.jda.demand.devsetup.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.Properties;
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
    private TextFlow commandTextFlow;
    @FXML
    private Text statusText;
    @FXML
    private Text commandText;

    @FXML
    private Hyperlink hyperlinkURL;



    private Properties properties;

    public TextFlow getCommandTextFlow() {
        return commandTextFlow;
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



    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Hyperlink getHyperlinkURL() {
        return hyperlinkURL;
    }

    public void setHyperlinkURL(Hyperlink hyperlinkURL) {
        this.hyperlinkURL = hyperlinkURL;
    }

    public void onAdminServerToggleSwitch() {
        resetLastExecutedCommand();
        if (getAdminServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new AdminServerStartCommand()).execute();
            getCommandText().setText(command);
            getCommandTextFlow().setVisible(true);

            String ADMIN_PORT = getProperties().getProperty(Constants.SERVER_ADMIN_PORT);
            String HOST_NAME = getProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://"+HOST_NAME+":"+ADMIN_PORT+"/console";
            getStatusText().setText("Admin Server Running at ");
            getHyperlinkURL().setText(url);
        }
    }

    public void onWebServerToggleSwitch() {
        resetLastExecutedCommand();
        if (getWebServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new ManagedServerStartCommand()).execute();
            getCommandText().setText(command);
            getCommandTextFlow().setVisible(true);

            String WEB_SERVER_PORT = getProperties().getProperty(Constants.SERVER_STANDARD_PORT);
            String HOST_NAME = getProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://"+HOST_NAME+":"+WEB_SERVER_PORT+"/";
            getStatusText().setText("Web Server Running at ");
            getHyperlinkURL().setText(url);
        }
    }

    public void onCisAgentToggleSwitch() {
        resetLastExecutedCommand();
        if (getCisAgent().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new CISAgentStartCommand()).execute();
            getCommandText().setText(command);
            getCommandTextFlow().setVisible(true);
        }
    }

    public void onSSOServerToggleSwitch() {
        resetLastExecutedCommand();
        if (getSsoServer().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new SSOServerStartCommand()).execute();
            getCommandText().setText(command);
            getCommandTextFlow().setVisible(true);
        }
    }

    public void onBasicPoolToggleSwitch() {
        resetLastExecutedCommand();
        if (getBasicPool().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new BasicNodePoolStartCommand()).execute();
            getCommandText().setText(command);
            getCommandTextFlow().setVisible(true);
        }
    }

    public void onRMIPoolToggleSwitch() {
        resetLastExecutedCommand();
        if (getRmiPool().isSwitchOn()) {
            String command = getCommandExecutor().setCommand(new RMIPoolStartCommand()).execute();
            getCommandText().setText(command);
            getCommandTextFlow().setVisible(true);
        }
    }

    public void onInstallButton() {
        resetLastExecutedCommand();
        String command = getCommandExecutor().setCommand(new InstallLicenseCommand()).execute();
        getCommandText().setText(command);
        getCommandTextFlow().setVisible(true);
    }

    public void onRunScpoTaskButton() {
        resetLastExecutedCommand();
        String command = getCommandExecutor().setCommand(new RunScpoTaskCommand()).execute();
        getCommandText().setText(command);
        getCommandTextFlow().setVisible(true);
    }

    public void onSetConfigCodeButton() {
        resetLastExecutedCommand();
        String command = getCommandExecutor().setCommand(new SetConfigCodeCommand()).execute();
        getCommandText().setText(command);
        getCommandTextFlow().setVisible(true);
    }

    public void onSRECleanupButton() {
        resetLastExecutedCommand();
        String command = getCommandExecutor().setCommand(new SRENodeCleanupCommand()).execute();
        getCommandText().setText(command);
        getCommandTextFlow().setVisible(true);
    }


    public void onHyperLinkURLClick() {

        Application application =(Application) Lookup.getInstance().getVariables().get(Constants.MAIN_APP);
        application.getHostServices().showDocument(getHyperlinkURL().getText());
    }
    private void resetLastExecutedCommand() {
        getCommandTextFlow().setVisible(false);
        getCommandText().setText(null);
        getStatusText().setText(null);
        getHyperlinkURL().setText(null);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setProperties(Lookup.getInstance().getBuildProperties());
    }
}
