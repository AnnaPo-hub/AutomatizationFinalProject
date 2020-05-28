package sqlUtils;

import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtils {
    private static String id = null;

    public static Connection getConnection() throws SQLException {
        final Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/app", "app", "pass");
        return connection;
    }

    public static String getStatusForPaymentByDebitCard() throws SQLException {
        val idSQL = "SELECT payment_id FROM order_entity order by created desc limit 1;";
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(idSQL);) {
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getString("payment_id");
                }
            }
        }
        String statusSQL = "SELECT status FROM payment_entity WHERE transaction_id =?; ";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL);) {
            statusStmt.setString(1, id);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    public static String getPaymentAmount() throws SQLException {
        String amountSQL = "SELECT amount FROM payment_entity WHERE transaction_id =?; ";
        String amount = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(amountSQL);) {
            statusStmt.setString(1, id);
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getString("amount");
                }
            }
        }
        return amount;
    }

    public static String getStatusForPaymentByCreditCard() throws SQLException {
        String statusSQL = "SELECT status FROM credit_request_entity order by created desc limit 1; ";
        String status = null;
        try (val conn = getConnection();
             val statusStmt = conn.prepareStatement(statusSQL);) {
            try (val rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }
}