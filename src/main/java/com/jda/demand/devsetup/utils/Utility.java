package com.jda.demand.devsetup.utils;

import com.jda.demand.devsetup.lookup.Lookup;

import java.io.File;

public class Utility {

    public static String getPathWithPackage(File javaFile) {
        String file = javaFile.getAbsolutePath();
        String _$ = File.separator;
        String classesDir = _$ + Constants.CLASSES + _$;
        String filePathWithPackage = file.substring(file.indexOf(classesDir) + classesDir.length());
        String rootPackage = filePathWithPackage.substring(0,filePathWithPackage.indexOf(_$));
        Lookup.getInstance().getVariables().put(Constants.ROOT_PACKAGE,rootPackage);
        return filePathWithPackage;

    }

    public static String getPathAfterScpo(File file) {
        String filePath = file.getAbsolutePath();
        String _$ = File.separator;
        String UTSOURCE = _$ + Constants.UTSOURCE + _$;
        String SOURCE = _$ + Constants.SOURCE + _$;
        String EAR = _$ + Constants.EAR + _$;
        String RESOURCES = _$ + Constants.RESOURCES + _$;

        int index = 0;
        if(filePath.contains(UTSOURCE)) {
            index = filePath.indexOf(UTSOURCE) ;
        } else if(filePath.contains(SOURCE)) {
            index = filePath.indexOf(SOURCE);
        } else if(filePath.contains(EAR)) {
            index = filePath.indexOf(EAR);
        } else if(filePath.contains(RESOURCES)) {
            index = filePath.indexOf(RESOURCES);
        }
        return index != 0 ? filePath.substring(index+1): "";
    }

    public static String getValidJarName(String input) {
        String jarName = Constants.DEFAULT_JAR_NAME;
        if ((input != null) && (!input.trim().isEmpty())) jarName = input;
        if (!jarName.endsWith(".jar")) jarName += ".jar";
        return jarName;
    }
    public static boolean isSet(final String variable) {
        return variable != null && !variable.isEmpty();
    }

    public static boolean isLookupVariableSet(final String lookupVariabe) {
        return isSet((String)Lookup.getInstance().getVariables().get(lookupVariabe));
    }
}
