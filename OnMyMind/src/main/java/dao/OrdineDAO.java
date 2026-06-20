package dao;

import java.sql.*;
import java.util.ArrayList;

import connection.DBConnection;
import model.Ordine;
import model.Utente;

public class OrdineDAO {

    public int insert(Ordine o) {

        String sql = "INSERT INTO ordine (id_utente, totale, stato_ordine) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, o.getUtente().getId_utente());
            ps.setDouble(2, o.getTotale());
            ps.setString(3, o.getStato_ordine().name().toLowerCase());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public Ordine getById(int id) {

        String sql = "SELECT * FROM ordine WHERE id_ordine=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Ordine o = new Ordine();

                o.setId_ordine(rs.getInt("id_ordine"));
                o.setTotale(rs.getDouble("totale"));
                o.setData_ordine(rs.getTimestamp("data_ordine").toLocalDateTime());

                o.setStato_ordine(
                        Ordine.StatoOrdine.valueOf(rs.getString("stato_ordine").toUpperCase())
                );

                Utente u = new Utente();
                u.setId_utente(rs.getInt("id_utente"));
                o.setUtente(u);

                return o;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public ArrayList<Ordine> getByUtente(int idUtente) {

        ArrayList<Ordine> list = new ArrayList<>();

        String sql = "SELECT * FROM ordine WHERE id_utente=? ORDER BY data_ordine DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUtente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Ordine o = new Ordine();

                o.setId_ordine(rs.getInt("id_ordine"));
                o.setTotale(rs.getDouble("totale"));
                o.setData_ordine(rs.getTimestamp("data_ordine").toLocalDateTime());

                o.setStato_ordine(
                        Ordine.StatoOrdine.valueOf(rs.getString("stato_ordine").toUpperCase())
                );

                list.add(o);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean updateStato(int idOrdine, Ordine.StatoOrdine stato) {

        String sql = "UPDATE ordine SET stato_ordine=? WHERE id_ordine=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, stato.name().toLowerCase());
            ps.setInt(2, idOrdine);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM ordine WHERE id_ordine=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}