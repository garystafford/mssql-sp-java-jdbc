package com.article.examples;

import com.article.connection.SqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Examples of calls to SQL Server using JDBC
 *
 * @author Gary A. Stafford
 */
public class Examples {

    private static SqlConnection connection = new SqlConnection();

    /**
     * Statement example, no parameters, db returns integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightST() {
        double averageWeight = 0;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT ROUND(AVG([Weight]), 2)";
            sql += " FROM Production.Product";
            sql += " WHERE ([Weight] > 0)";
            sql += " AND (WeightUnitMeasureCode = 'LB')";
            stmt = connection.getConnection().createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                averageWeight = rs.getDouble(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * PreparedStatement example, no parameters, db returns integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightPS() {
        double averageWeight = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT ROUND(AVG([Weight]), 2) AS averageWeight";
            sql += " FROM Production.Product";
            sql += " WHERE ([Weight] > 0)";
            sql += " AND (WeightUnitMeasureCode = 'LB')";
            pstmt = connection.getConnection().prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                averageWeight = rs.getDouble("averageWeight");
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * CallableStatement, no parameters, db returns integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightCS() {
        CallableStatement cstmt = null;
        double averageWeight = 0;
        ResultSet rs = null;
        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call uspGetAverageProductWeight}");
            cstmt.execute();
            rs = cstmt.getResultSet();
            if (rs.next()) {
                averageWeight = rs.getDouble(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * CallableStatement example, (1) output parameter, db returns integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightOutCS() {
        CallableStatement cstmt = null;
        double averageWeight = 0;

        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call dbo.uspGetAverageProductWeightOUT(?)}");
            cstmt.registerOutParameter("averageWeight", java.sql.Types.DECIMAL);
            cstmt.execute();
            averageWeight = cstmt.getDouble("averageWeight");
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * CallableStatement example, (1) input parameter, db returns ResultSet
     *
     * @param lastNameStartsWith
     * @return List of employee names
     */
    public List<String> getEmployeesByLastNameCS(String lastNameStartsWith) {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<String> employeeFullName = new ArrayList<>();

        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call HumanResources.uspGetEmployeesByLastName(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cstmt.setString("lastNameStartsWith", lastNameStartsWith);
            boolean results = cstmt.execute();
            int rowsAffected = 0;

            // Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            while (rs.next()) {
                employeeFullName.add(
                        rs.getString("LastName") + ", "
                        + rs.getString("FirstName") + " "
                        + rs.getString("MiddleName"));
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return employeeFullName;
    }

    /**
     * CallableStatement example, (2) input parameters, db returns ResultSet
     *
     * @param color
     * @param size
     * @return List of Product objects
     */
    public List<Product> getProductsByColorAndSizeCS(String color, String size) {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call Production.uspGetProductsByColorAndSize(?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cstmt.setString("productColor", color);
            cstmt.setString("productSize", size);
            boolean results = cstmt.execute();
            int rowsAffected = 0;

            // Protects against lack of SET NOCOUNT in stored prodedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("Product"),
                        rs.getString("ProductNumber"),
                        rs.getString("Color"),
                        rs.getString("Size"),
                        rs.getString("Model"));
                productList.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return productList;
    }

    /**
     * Close the database connection
     *
     * @return
     */
    public boolean closeConnection() {
        if (connection.getConnection() != null) {
            try {
                connection.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(RunExamples.class.getName()).
                        log(Level.WARNING, null, ex);
                return false;
            }
        }
        return true;
    }
}