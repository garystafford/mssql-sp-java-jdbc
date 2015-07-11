package com.article.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class to store all project properties
 *
 * @author Gary A. Stafford
 */
public class ProjectProperties {

    private static ProjectProperties instance = null;
    private java.util.Properties properties = new java.util.Properties();
    private String xmlConfigurationFile = "properties.xml";

    /**
     *
     * @throws Exception
     */
    private ProjectProperties() throws Exception {
        readXmlProperties();
    }

    private void readXmlProperties() throws IOException {
        try (InputStream in = getClass().
                        getResourceAsStream(xmlConfigurationFile)) {
            properties.loadFromXML(in);
        }
    }

    /**
     * @return the properties object
     */
    public java.util.Properties getProperties() {
        return properties;
    }

    /**
     * @return the xmlConfigurationFile
     */
    public String getXmlConfigurationFile() {
        return xmlConfigurationFile;
    }

    /**
     * Returns singleton instance of class.
     *
     * @return the instance
     */
    static public ProjectProperties getInstance() {
        if (instance == null) {
            try {
                instance = new ProjectProperties();
            } catch (Exception ex) {
                Logger.getLogger(ProjectProperties.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
}
