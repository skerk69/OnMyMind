package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import model.DettaglioOrdine;
import model.Cappello;
import model.Ordine;

public class DettaglioOrdineDAO {

    public boolean insert(DettaglioOrdine d) {

        String sql = "INSERT INTO dettaglio_ordine (id_ordine, id_cappello, quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getOrdine().getId_ordine());
            ps.setInt(2, d.getCappello().getId_cappello());
            ps.setInt(3, d.getQuantita());
            ps.setDouble(4, d.getPrezzo_unitario());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DettaglioOrdine> getByOrdine(int idOrdine) {

        ArrayList<DettaglioOrdine> list = new ArrayList<>();

        String sql = "SELECT d.*, c.* FROM dettaglio_ordine d " +
                     "JOIN cappello c ON d.id_cappello = c.id_cappello " +
                     "WHERE d.id_ordine = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrdine);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    DettaglioOrdine d = new DettaglioOrdine();

                    d.setId_ordine(rs.getInt("id_ordine"));
                    d.setId_cappello(rs.getInt("id_cappello"));
                    d.setQuantita(rs.getInt("quantita"));
                    d.setPrezzo_unitario(rs.getDouble("prezzo_unitario"));

                    Cappello c = new Cappello();
                    c.setId_cappello(rs.getInt("id_cappello"));
                    c.setNome(rs.getString("nome"));
                    c.setDescrizione(rs.getString("descrizione"));
                    c.setPrezzo(rs.getDouble("prezzo"));
                    c.setTaglia(rs.getString("taglia"));
                    c.setColore(rs.getString("colore"));
                    c.setMateriale(rs.getString("materiale"));
                    c.setQuantitaMagazzino(rs.getInt("quantita_magazzino"));
                    c.setImmagine(rs.getString("immagine"));
                    
                    d.setCappello(c);
                    
                    list.add(d);
                }
            }

        } catch (Exception e) { 
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean deleteByOrdine(int idOrdine) {

        String sql = "DELETE FROM dettaglio_ordine WHERE id_ordine=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrdine);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteSingle(int idOrdine, int idCappello) {

        String sql = "DELETE FROM dettaglio_ordine WHERE id_ordine=? AND id_cappello=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrdine);
            ps.setInt(2, idCappello);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}