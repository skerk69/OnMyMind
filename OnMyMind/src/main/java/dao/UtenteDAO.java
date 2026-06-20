package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

import connection.DBConnection;
import model.Utente;

public class UtenteDAO {

    public boolean insertUtente(Utente u) {
        String sql = "INSERT INTO utente (nome, cognome, email, password, telefono, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, hashPassword(u.getPassword()));
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getRuolo().getDbValue());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Errore insert utente", e);
        }
    }

    public Utente getById(int id) {
        String sql = "SELECT * FROM utente WHERE id_utente = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente u = new Utente();
                u.setId_utente(rs.getInt("id_utente"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTelefono(rs.getString("telefono"));
                u.setRuolo(Utente.Ruolo.fromDb(rs.getString("ruolo")));

                return u;
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore get utente by id", e);
        }

        return null;
    }

    public Utente login(String email, String password) {
        String sql = "SELECT * FROM utente WHERE email = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, hashPassword(password));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utente u = new Utente();
                u.setId_utente(rs.getInt("id_utente"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setRuolo(Utente.Ruolo.fromDb(rs.getString("ruolo")));

                return u;
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore login utente", e);
        }

        return null;
    }

    public boolean updateUtente(Utente u) {
        String sql = "UPDATE utente SET nome=?, cognome=?, email=?, password=?, telefono=?, ruolo=? WHERE id_utente=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, hashPassword(u.getPassword()));
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getRuolo().getDbValue());
            ps.setInt(7, u.getId_utente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Errore update utente", e);
        }
    }

    public boolean deleteUtente(int id) {
        String sql = "DELETE FROM utente WHERE id_utente=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Errore delete utente", e);
        }
    }

    public ArrayList<Utente> getAll() {
        ArrayList<Utente> list = new ArrayList<>();

        String sql = "SELECT * FROM utente";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Utente u = new Utente();
                u.setId_utente(rs.getInt("id_utente"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setTelefono(rs.getString("telefono"));
                u.setRuolo(Utente.Ruolo.fromDb(rs.getString("ruolo")));

                list.add(u);
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore getAll utenti", e);
        }

        return list;
    }
    
    public boolean checkEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM utente WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hashBytes = digest.digest(password.getBytes());
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore: Algoritmo di hashing non trovato", e);
        }
    }
}