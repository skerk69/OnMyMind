package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import model.Recensione;
import model.Utente;
import model.Cappello;

public class RecensioneDAO {

    public boolean insert(Recensione r) {

        String sql = "INSERT INTO recensione (id_utente, id_cappello, voto, commento) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getUtente().getId_utente());
            ps.setInt(2, r.getCappello().getId_cappello());
            ps.setInt(3, r.getVoto());
            ps.setString(4, r.getCommento());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Recensione> getByCappello(int idCappello) {

        ArrayList<Recensione> list = new ArrayList<>();

        String sql = "SELECT * FROM recensione WHERE id_cappello=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCappello);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Recensione r = new Recensione();

                r.setId_recensione(rs.getInt("id_recensione"));
                r.setVoto(rs.getInt("voto"));
                r.setCommento(rs.getString("commento"));
                r.setData_recensione(rs.getTimestamp("data_recensione").toLocalDateTime());

                Utente u = new Utente();
                u.setId_utente(rs.getInt("id_utente"));
                r.setUtente(u);

                Cappello c = new Cappello();
                c.setId_cappello(rs.getInt("id_cappello"));
                r.setCappello(c);

                list.add(r);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean hasReviewed(int idUtente, int idCappello) {
        String query = "SELECT COUNT(*) FROM recensione WHERE id_utente = ? AND id_cappello = ?";
        
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idUtente);
            ps.setInt(2, idCappello);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return false;
    }
    
    public boolean delete(int id) {

        String sql = "DELETE FROM recensione WHERE id_recensione=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}