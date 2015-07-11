package com.article.examples;

import org.junit.*;

/**
 *
 * @author Gary A. Stafford
 */
public class ExamplesTest {

    private static Examples examples = new Examples();

    /**
     *
     */
    public ExamplesTest() {
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
        examples.closeConnection();
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
     * Test of getAverageProductWeightCS method, of class Examples.
     *
     * @throws Exception
     */
    @Test
    public void testGetAverageProductWeightGreaterThanZero() throws Exception {
        System.out.println("getAverageProductWeightGreaterThanZero");
        double expResult = 0.00;
        double result = examples.getAverageProductWeightCS();
        System.out.println("averageWeight: " + result);
        Assert.assertTrue(result > expResult);
    }
    
        /**
     * Test of getAverageProductWeightCS method, of class Examples.
     *
     * @throws Exception
     */
    @Test
    public void testGetAverageProductWeightCS() throws Exception {
        System.out.println("getAverageProductWeightCS");
        double expResult = 13.48;
        double result = examples.getAverageProductWeightCS();
        System.out.println("averageWeight: " + result);
        Assert.assertEquals(expResult, result, .001);
    }
}