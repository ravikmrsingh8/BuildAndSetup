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
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.ExecuteResultHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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


    public Hyperlink getHyperlinkURL() {
        return hyperlinkURL;
    }


    public Lookup getLookup() {
        return Lookup.getInstance();
    }


    public void onOpenLogsFolder() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        String SCPO_HOME = getLookup().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
        String _$ = File.separator;
        String logsDir = SCPO_HOME + _$ + Constants.WEBLOGIC + _$ + Constants.CONFIG + _$ + Constants.LOGS + _$;
        Command command = new OpenFolder(logsDir);
        executeCommand(command, new DefaultExecuteResultHandler());
    }

    public void onOpenStagedFolder() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        String SCPO_HOME = getLookup().getEnvironmentVariables().get(Constants.ENV_BUILD_ROOT);
        String _$ = File.separator;

        String stagedDir = SCPO_HOME + _$ + Constants.WEBLOGIC + _$ + Constants.CONFIG + _$;
        stagedDir += "JDADomain" + _$ + "servers" + _$ + "JDAServer" + _$ + "stage" + _$ + "WebWORKSApp" + _$ + "ear" + _$ + "scpoweb" + _$ + "scpoweb" + _$;
        Command command = new OpenFolder(stagedDir);
        executeCommand(command, new DefaultExecuteResultHandler());
    }

    public void onAdminServerToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getAdminServer().isSwitchOn()) {
            executeCommand(new AdminServerStartCommand(), new ProcessExecutionResultHandler(getAdminServer(), "AdminServer"));
            String ADMIN_PORT = getLookup().getBuildProperties().getProperty(Constants.SERVER_ADMIN_PORT);
            String HOST_NAME = getLookup().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://" + HOST_NAME + ":" + ADMIN_PORT + "/console";
            getStatusText().setText("Admin Server Running at ");
            getHyperlinkURL().setText(url);
        } else {
            executeCommand(new AdminServerStopCommand(), new DefaultExecuteResultHandler());
        }
    }

    public void onWebServerToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getWebServer().isSwitchOn()) {
            executeCommand(new ManagedServerStartCommand(), new ProcessExecutionResultHandler(getWebServer(), "AppServer"));
            String WEB_SERVER_PORT = getLookup().getBuildProperties().getProperty(Constants.SERVER_STANDARD_PORT);
            String HOST_NAME = getLookup().getBuildProperties().getProperty(Constants.SERVER_HOST_NAME);
            String url = "http://" + HOST_NAME + ":" + WEB_SERVER_PORT + "/";
            getStatusText().setText("Web Server Running at ");
            getHyperlinkURL().setText(url);
        } else {
            executeCommand(new ManagedServerShutdownCommand(), new DefaultExecuteResultHandler());
        }
    }

    public void onCisAgentToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.CIS_HOME)) return;
        resetLastExecutedCommand();
        if (getCisAgent().isSwitchOn()) {
            executeCommand(new CISAgentStartCommand(), new ProcessExecutionResultHandler(getCisAgent(), "CISAgent"));
        } else {
            executeCommand(new CISAgentStopCommand(), new DefaultExecuteResultHandler());
        }
    }

    public void onSSOServerToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.CIS_HOME)) return;
        resetLastExecutedCommand();
        if (getSsoServer().isSwitchOn()) {
            executeCommand(new SSOServerStartCommand(), new ProcessExecutionResultHandler(getSsoServer(), "SSOServer"));
        } else {
            executeCommand(null, null);
        }
    }

    public void onBasicPoolToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getBasicPool().isSwitchOn()) {
            executeCommand(new BasicNodePoolStartCommand(), new ProcessExecutionResultHandler(getBasicPool(), "Basic Node Pool"));
        } else {
            executeCommand(new BasicNodePoolShutdownCommand(), new DefaultExecuteResultHandler());
        }
    }

    public void onRMIPoolToggleSwitch() {
        if (!Utility.isLookupVariableSet(Constants.ENV_FILE)) return;
        resetLastExecutedCommand();
        if (getRmiPool().isSwitchOn()) {
            executeCommand(new RMIPoolStartCommand(), new ProcessExecutionResultHandler(getRmiPool(), "RMI Pool"));
        } else {
            executeCommand(new RMIPoolShutdownCommand(), new DefaultExecuteResultHandler());
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
    }

    private void executeCommand(Command command, ExecuteResultHandler handler) {
        if (command == null) return;
        getLogger().log(Level.INFO, "Running " + command);
        try {
            CommandExecutor executor = CommandExecutor.getExecutor();
            executor.setWorkingDirectory(command.getWorkingDirectory());
            executor.execute(command, command.getEnvironmentVariables(), handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getCommandText().setText(command.toString());
        getCommandBox().setVisible(true);
    }

    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }
}
