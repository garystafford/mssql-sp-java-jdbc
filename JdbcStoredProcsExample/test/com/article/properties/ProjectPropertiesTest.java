package com.article.properties;

import org.junit.*;

/**
 *
 * @author Gary A. Stafford
 */
public class ProjectPropertiesTest {

    private static ProjectProperties properties = ProjectProperties.getInstance();

    /**
     *
     */
    public ProjectPropertiesTest() {
    }

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getProperties method, of class ProjectProperties.
     *
     * @throws Exception
     */
    @Test
    public void testGetPropertiesGetConnectorServerProperty() throws Exception {
        System.out.println("getPropertiesConnectorServerProperty");
        String expResult = "localhost";
        String result = properties.getProperties().
                getProperty("connector.server");
        System.out.println("connector.server: " + result);
        Assert.assertEquals(expResult, result.toLowerCase());
    }

    /**
     * Test of getXmlConfigurationFile method, of class ProjectProperties.
     *
     * @throws Exception
     */
    @Test
    public void testGetXmlGetConfigurationFileName() throws Exception {
        System.out.println("getXmlGetConfigurationFileName");
        String expResult = "properties.xml";
        String result = properties.getXmlConfigurationFile();
        System.out.println("getXmlConfigurationFile: " + result);
        Assert.assertEquals(expResult, result.toLowerCase());
    }

    /**
     * Test of getProperties method, of class ProjectProperties.
     *
     * @throws Exception
     */
    @Test
    public void testGetPropertiesSize() throws Exception {
        System.out.println("getPropertiesSize");
        int expResult = 5;
        int result = properties.getProperties().size();
        System.out.println("Properties size: " + result);
        Assert.assertEquals(expResult, result);
    }
}
