package com.article.connection;

import org.junit.*;

/**
 *
 * @author Gary A. Stafford
 */
public class SqlConnectionTest {

    private static SqlConnection connection = null;

    /**
     *
     */
    public SqlConnectionTest() {
    }

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        connection = new SqlConnection();
        connection.getConnection();
    }

    /**
     *
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        if (!connection.getConnection().isClosed()) {
            connection.getConnection().close();
        }
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
     * Test of createConnection method, of class Connector.
     *
     * @throws Exception
     */
    @Test
    public void testGetConnectionIsNotClosed() throws Exception {
        System.out.println("getConnectionIsNotClosed");
        Boolean expResult = false;
        Boolean result = connection.getConnection().isClosed();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of getDataSource method, of class SqlConnection.
     */
    @Test
    public void testGetDataSourceGetDatabaseName() {
        System.out.println("getDataSourceGetDatabaseName");
        String expResult = "adventureworks";
        String result = SqlConnection.getDataSource().
                getDatabaseName();
        System.out.println("getDatabaseName: " + result);
        Assert.assertEquals(expResult, result.toLowerCase());
    }
}