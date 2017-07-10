package com.jda.demand.devsetup.utils;

public class Constants {
    public final static String OUTPUT_FOLDER = "Output\\";

    //Preferences lookup
    public static final String PREFERENCES = "preferences.properties";
    // keys for preferences.properties lookup
    public static final String CIS_HOME = "cis.home";
    public static final String ENV_FILE = "env.file.loc";
    public static final String LIC_FILE = "scpo.lic.file";


    //Build Options
    public static final String CLEAN = "clean";
    public static final String SYSTEM = "system";
    public static final String CUSTOMIZE = "customize";
    public static final String BEA_CREATE_SERVER = "bea_create_server";
    public static final String DEMAND_SRC = "demandsrc";
    public static final String SCSC_SRC = "scscsrc";


    //Environment Variables Keys
    public static final String ENV_JAVA_HOME = "JAVA_HOME";
    public static final String ENV_BUILD_ROOT = "BUILD_ROOT";
    public static final String ENV_BUILD_PROPS = "BUILD_PROPS";
    public static final String ENV_FINDBUGS_OFF = "FINDBUGS_OFF";

    //Keys for scpoweb.build.properties lookup
    public static final String ORACLE_NET_SERVICE = "ORACLE_NET_SERVICE";
    public static final String SERVER_ADMIN_PORT = "SERVER_ADMIN_PORT";
    public static final String SERVER_STANDARD_PORT = "SERVER_STANDARD_PORT";
    public static final String SERVER_HOST_NAME = "SERVER_HOST_NAME";


    //Directories from scpo
    public static final String WEBLOGIC = "weblogic";
    public static final String CONFIG = "config";
    public static final String CLASSES = "classes";
    public static final String BIN = "bin";
    public static final String PLATFORM = "platform";
    public static final String SCPOWEB = "scpoweb";
    public static final String DATABASE = "database";


    //EXECUTABLES
    public static final String START_ADMIN_SERVER = "startWebworksAdminServer.cmd";
    public static final String START_WEB_SERVER = "startManagedWebworksServer.cmd";

    public static final String START_NODE_POOL = "startNodePoolManager.cmd";
    public static final String BASIC_NODE_POOL = "Basic";
    public static final String RMI_NODE_POOL = "Rmi";

    public static final String LAUNCH = "launch.bat";
    public static final String CIS_AGENT_PY = "runCISAgent.py";
    public static final String SSO_SERVER_PY = "runSSOServer.py";

    public static final String INSTALL_LIC = "installLicense.cmd";
    public static final String RUN_SCPO_TASK = "runScpoTask.cmd";
    public static final String GENERATE_CONFIG_CODE = "generate_configcode";

    public static final String SET_CONFIG_CODE = "set_config_code.bat";
    public static final String SQL_PLUS = "sqlplus";
    public static final String SRE_CLEANUP_SQL = "Cleanup.sql";

    //Schemas
    public static final String SCPOMGR = "SCPOMGR";
    public static final String WWFMGR = "WWFMGR";

    //JAR Properties
    public static final String DEFAULT_JAR_NAME = "Test.jar";
}
