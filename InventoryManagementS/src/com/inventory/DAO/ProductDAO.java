package com.inventory.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.inventory.model.Product;

public class ProductDAO {
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/inventoryms";
        String username = "root";
        String password = "admin";
        return DriverManager.getConnection(url, username, password);
    }

    public void createProduct(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO products (productId, productName, category, subCategory, productWeight, brand) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getSubCategory());
            stmt.setInt(5, product.getProductWeight());
            stmt.setString(6, product.getBrand());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM products";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setCategory(rs.getString("category"));
                product.setSubCategory(rs.getString("subCategory"));
                product.setProductWeight(rs.getInt("productWeight"));
                product.setBrand(rs.getString("brand"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM products WHERE productId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setCategory(rs.getString("category"));
                product.setSubCategory(rs.getString("subCategory"));
                product.setProductWeight(rs.getInt("productWeight"));
                product.setBrand(rs.getString("brand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Update a product
    public void updateProduct(Product product) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE products SET productName=?, category=?, subCategory=?, productWeight=?, brand=? WHERE productId=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getCategory());
            stmt.setString(3, product.getSubCategory());
            stmt.setInt(4, product.getProductWeight());
            stmt.setString(5, product.getBrand());
            stmt.setInt(6, product.getProductId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a product
    public void deleteProduct(int productId) {
        try (Connection conn = getConnection()) {
            String sql = "DELETE FROM products WHERE productId=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
