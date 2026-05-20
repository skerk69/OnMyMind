package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import model.Indirizzo;
import model.Utente;

public class IndirizzoDAO {

    public boolean insert(Indirizzo i) {

        String sql = "INSERT INTO indirizzo (id_utente, via, citta, CAP, provincia, paese) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, i.getUtente().getId_utente());
            ps.setString(2, i.getVia());
            ps.setString(3, i.getCitta());
            ps.setString(4, i.getCap());
            ps.setString(5, i.getProvincia());
            ps.setString(6, i.getPaese());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Indirizzo> getByUtente(int idUtente) {

        ArrayList<Indirizzo> list = new ArrayList<>();

        String sql = "SELECT * FROM indirizzo WHERE id_utente=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUtente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Indirizzo i = new Indirizzo();

                i.setId_indirizzo(rs.getInt("id_indirizzo"));
                i.setVia(rs.getString("via"));
                i.setCitta(rs.getString("citta"));
                i.setCap(rs.getString("cap"));
                i.setProvincia(rs.getString("provincia"));
                i.setPaese(rs.getString("paese"));

                Utente u = new Utente();
                u.setId_utente(rs.getInt("id_utente"));
                i.setUtente(u);

                list.add(i);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM indirizzo WHERE id_indirizzo=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}