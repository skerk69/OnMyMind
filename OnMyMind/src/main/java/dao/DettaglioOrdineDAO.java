package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import model.DettaglioOrdine;
import model.Cappello;

public class DettaglioOrdineDAO {

    public boolean insert(DettaglioOrdine d) {

        String sql = "INSERT INTO dettaglio_ordine (id_ordine, id_cappello, quantita, prezzo_unitario, nome_cappello, taglia, colore, immagine) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, d.getId_ordine());
            ps.setInt(2, d.getId_cappello());
            ps.setInt(3, d.getQuantita());
            ps.setDouble(4, d.getPrezzo_unitario());
            ps.setString(5, d.getNome_cappello());
            ps.setString(6, d.getTaglia());
            ps.setString(7, d.getColore());
            ps.setString(8, d.getImmagine());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<DettaglioOrdine> getByOrdine(int idOrdine) {

        ArrayList<DettaglioOrdine> list = new ArrayList<>();

        String sql = "SELECT d.*, c.materiale, c.quantita_magazzino, c.descrizione FROM dettaglio_ordine d " +
                     "LEFT JOIN cappello c ON d.id_cappello = c.id_cappello " +
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
                    d.setNome_cappello(rs.getString("nome_cappello"));
                    d.setTaglia(rs.getString("taglia"));
                    d.setColore(rs.getString("colore"));
                    d.setImmagine(rs.getString("immagine"));

                    Cappello c = new Cappello();
                    c.setId_cappello(d.getId_cappello());
                    c.setNome(d.getNome_cappello());
                    c.setPrezzo(d.getPrezzo_unitario());
                    c.setTaglia(d.getTaglia());
                    c.setColore(d.getColore());
                    c.setImmagine(d.getImmagine());
                    c.setMateriale(rs.getString("materiale"));
                    c.setDescrizione(rs.getString("descrizione"));
                    c.setQuantitaMagazzino(rs.getInt("quantita_magazzino"));
                    
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