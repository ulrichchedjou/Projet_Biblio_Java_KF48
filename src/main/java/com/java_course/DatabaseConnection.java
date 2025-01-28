package com.java_course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bibliotheque";
    private static final String USER = "postgres"; // replace with your PostgreSQL username
    private static final String PASSWORD = "sudoroot"; // replace with your PostgreSQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livres (isbn, title, author, categorie, is_available) VALUES (?, ?, ?, ?, ?) ON CONFLICT (isbn) DO NOTHING;";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livre.getIsbn());
            pstmt.setString(2, livre.getTitle());
            pstmt.setString(3, livre.getAuthor());
            pstmt.setString(4, livre.getCategorie());
            pstmt.setBoolean(5, livre.isAvailable());
            pstmt.executeUpdate();
            System.out.println("Livre ajouté à la base de données: " + livre.getTitle());
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du livre: " + e.getMessage());
        }
    }

    public static Livre rechercherLivre(String isbn) {
        String sql = "SELECT * FROM livres WHERE isbn = ?;";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, isbn);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Livre(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("categorie"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du livre: " + e.getMessage());
        }
        return null;
    }

    public static List<Livre> rechercherLivreParTitre(String title) {
        List<Livre> result = new ArrayList<>();
        String sql = "SELECT * FROM livres WHERE LOWER(title) = LOWER(?);";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Livre(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("categorie")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche par titre: " + e.getMessage());
        }
        return result;
    }

    public static List<Livre> rechercherLivreParAuteur(String author) {
        List<Livre> result = new ArrayList<>();
        String sql = "SELECT * FROM livres WHERE LOWER(author) = LOWER(?);";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Livre(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("categorie")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche par auteur: " + e.getMessage());
        }
        return result;
    }

    public static List<Livre> rechercherLivreParCategorie(String categorie) {
        List<Livre> result = new ArrayList<>();
        String sql = "SELECT * FROM livres WHERE LOWER(categorie) = LOWER(?);";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categorie);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(new Livre(rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("categorie")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche par catégorie: " + e.getMessage());
        }
        return result;
    }

    public static void ajouterMembre(Membres membre) {
        String sql = "INSERT INTO membres (member_id, name, email, date_inscription) VALUES (?, ?, ?, ?) ON CONFLICT (member_id) DO NOTHING;";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, membre.getMemberId());
            pstmt.setString(2, membre.getName());
            pstmt.setString(3, membre.getEmail());
            pstmt.setDate(4, new java.sql.Date(membre.getDateInscription().getTime()));
            pstmt.executeUpdate();
            System.out.println("Membre ajouté à la base de données: " + membre.getName());
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du membre: " + e.getMessage());
        }
    }

    public static void supprimerMembre(String memberId) {
        String sql = "DELETE FROM membres WHERE member_id = ?;";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Membre supprimé de la base de données: " + memberId);
            } else {
                System.out.println("Aucun membre trouvé avec l'ID: " + memberId);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du membre: " + e.getMessage());
        }
    }
}
