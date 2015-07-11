package com.article.examples;

import java.util.List;

/**
 * Main class that calls all example methods
 *
 * @author Gary A. Stafford
 */
public class RunExamples {

    private static Examples examples = new Examples();
    private static ProcessTimer timer = new ProcessTimer();

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("");
        System.out.println("SQL SERVER STATEMENT EXAMPLES");
        System.out.println("======================================");

        // Statement example, no parameters, db returns integer
        timer.setStartTime(System.nanoTime());
        double averageWeight = examples.getAverageProductWeightST();
        timer.setEndTime(System.nanoTime());
        System.out.println("GetAverageProductWeightST");
        System.out.println("Duration (ms): " + timer.getDuration());
        System.out.println("Average product weight (lb): " + averageWeight);
        System.out.println("");

        // PreparedStatement example, no parameters, db returns integer
        timer.setStartTime(System.nanoTime());
        averageWeight = examples.getAverageProductWeightPS();
        timer.setEndTime(System.nanoTime());
        System.out.println("GetAverageProductWeightPS");
        System.out.println("Duration (ms): " + timer.getDuration());
        System.out.println("Average product weight (lb): " + averageWeight);
        System.out.println("");

        // CallableStatement, no parameters, db returns integer
        timer.setStartTime(System.nanoTime());
        averageWeight = examples.getAverageProductWeightCS();
        timer.setEndTime(System.nanoTime());
        System.out.println("GetAverageProductWeightCS");
        System.out.println("Duration (ms): " + timer.getDuration());
        System.out.println("Average product weight (lb): " + averageWeight);
        System.out.println("");

        // CallableStatement example, (1) output parameter, db returns integer
        timer.setStartTime(System.nanoTime());
        averageWeight = examples.getAverageProductWeightOutCS();
        timer.setEndTime(System.nanoTime());
        System.out.println("GetAverageProductWeightOutCS");
        System.out.println("Duration (ms): " + timer.getDuration());
        System.out.println("Average product weight (lb): " + averageWeight);
        System.out.println("");

        // CallableStatement example, (1) input parameter, db returns ResultSet
        timer.setStartTime(System.nanoTime());
        String lastNameStartsWith = "Sa";
        List<String> employeeFullName =
                examples.getEmployeesByLastNameCS(lastNameStartsWith);
        timer.setEndTime(System.nanoTime());
        System.out.println("GetEmployeesByLastNameCS");
        System.out.println("Duration (ms): " + timer.getDuration());
        System.out.println("Last names starting with '"
                + lastNameStartsWith + "': " + employeeFullName.size());
        if (employeeFullName.size() > 0) {
            System.out.println("Last employee found: "
                    + employeeFullName.get(employeeFullName.size() - 1));
        } else {
            System.out.println(
                    "No employees found with last name starting with '"
                    + lastNameStartsWith + "'");
        }
        System.out.println("");

        // CallableStatement example, (2) input parameters, db returns ResultSet
        timer.setStartTime(System.nanoTime());
        String color = "Red";
        String size = "44";
        List<Product> productList =
                examples.getProductsByColorAndSizeCS(color, size);
        timer.setEndTime(System.nanoTime());
        System.out.println("GetProductsByColorAndSizeCS");
        System.out.println("Duration (ms): " + timer.getDuration());
        if (productList.size() > 0) {
            System.out.println("Products found (color: '" + color
                    + "', size: '" + size + "'): " + productList.size());
            System.out.println("First product: "
                    + productList.get(0).getProduct()
                    + " (" + productList.get(0).getProductNumber() + ")");
        } else {
            System.out.println("No products found with color '" + color
                    + "' and size '" + size + "'");
        }
        System.out.println("");

        examples.closeConnection();
    }
}