package dao;

import java.sql.*;
import java.util.ArrayList;

import connection.DBConnection;
import model.Cappello;
import model.Categoria;

public class CappelloDAO {

    public boolean insert(Cappello c) {

        String sql = "INSERT INTO cappello (id_categoria, nome, descrizione, prezzo, taglia, colore, materiale, quantita_magazzino, immagine) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCategoria().getId_categoria());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getDescrizione());
            ps.setDouble(4, c.getPrezzo());
            ps.setString(5, c.getTaglia());
            ps.setString(6, c.getColore());
            ps.setString(7, c.getMateriale());
            ps.setInt(8, c.getQuantitaMagazzino());
            ps.setString(9, c.getImmagine());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Errore insert cappello", e);
        }
    }

    public Cappello getById(int id) {

        String sql = "SELECT * FROM cappello WHERE id_cappello = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore getById cappello", e);
        }

        return null;
    }

    public ArrayList<Cappello> getAll() {

        ArrayList<Cappello> list = new ArrayList<>();

        String sql = "SELECT * FROM cappello";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore getAll cappelli", e);
        }

        return list;
    }

    public boolean update(Cappello c) {

        String sql = "UPDATE cappello SET id_categoria=?, nome=?, descrizione=?, prezzo=?, taglia=?, colore=?, materiale=?, quantita_magazzino=?, immagine=? "
                   + "WHERE id_cappello=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCategoria().getId_categoria());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getDescrizione());
            ps.setDouble(4, c.getPrezzo());
            ps.setString(5, String.valueOf(c.getTaglia()));
            ps.setString(6, c.getColore());
            ps.setString(7, c.getMateriale());
            ps.setInt(8, c.getQuantitaMagazzino());
            ps.setString(9, c.getImmagine());
            ps.setInt(10, c.getId_cappello());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Errore update cappello", e);
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM cappello WHERE id_cappello=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException("Errore delete cappello", e);
        }
    }

    public ArrayList<Cappello> search(String nome, Integer idCategoria, String colore, String taglia, Double prezzoMin, Double prezzoMax) {

        ArrayList<Cappello> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM cappello WHERE 1=1");

        try (Connection conn = DBConnection.getConnection()) {

            if (nome != null && !nome.trim().isEmpty()) {
                sql.append(" AND nome LIKE ?");
            }
            if (idCategoria != null) {
                sql.append(" AND id_categoria = ?");
            }
            if (colore != null && !colore.trim().isEmpty()) {
                sql.append(" AND colore LIKE ?"); 
            }
            if (taglia != null) {
                sql.append(" AND taglia = ?");
            }
            if (prezzoMin != null) {
                sql.append(" AND prezzo >= ?");
            }
            if (prezzoMax != null) {
                sql.append(" AND prezzo <= ?");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int i = 1;

            if (nome != null && !nome.trim().isEmpty()) {
                ps.setString(i++, "%" + nome.trim() + "%"); 
            }
            if (idCategoria != null) {
                ps.setInt(i++, idCategoria);
            }
            if (colore != null && !colore.trim().isEmpty()) {
                ps.setString(i++, "%" + colore.trim() + "%");
            }
            if (taglia != null) {
                ps.setString(i++, taglia);
            }
            if (prezzoMin != null) {
                ps.setDouble(i++, prezzoMin);
            }
            if (prezzoMax != null) {
                ps.setDouble(i++, prezzoMax);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException("Errore search cappelli", e);
        }

        return list;
    }

    public ArrayList<Cappello> getPurchasedBuUser(int idUtente) {
        ArrayList<Cappello> listaCappelli = new ArrayList<>();
        
        String query = "SELECT DISTINCT c.* FROM ordine o " +
                       "JOIN dettaglio_ordine d ON o.id_ordine = d.id_ordine " +
                       "JOIN cappello c ON d.id_cappello = c.id_cappello " +
                       "WHERE o.id_utente = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, idUtente);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cappello c = mapRow(rs);
                    listaCappelli.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaCappelli;
    }
    
    private Cappello mapRow(ResultSet rs) throws SQLException {

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

        Categoria cat = new Categoria();
        cat.setId_categoria(rs.getInt("id_categoria"));
        c.setCategoria(cat);

        return c;
    }
}