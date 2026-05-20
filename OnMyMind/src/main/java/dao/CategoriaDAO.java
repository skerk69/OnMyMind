package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import model.Categoria;

public class CategoriaDAO {

    public boolean insert(Categoria c) {

        String sql = "INSERT INTO categoria (nome_categoria, descrizione) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNomeCategoria());
            ps.setString(2, c.getDescrizione());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria getById(int id) {

        String sql = "SELECT * FROM categoria WHERE id_categoria=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Categoria c = new Categoria();

                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNomeCategoria(rs.getString("nome_categoria"));
                c.setDescrizione(rs.getString("descrizione"));

                return c;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public ArrayList<Categoria> getAll() {

        ArrayList<Categoria> list = new ArrayList<>();

        String sql = "SELECT * FROM categoria";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Categoria c = new Categoria();

                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNomeCategoria(rs.getString("nome_categoria"));
                c.setDescrizione(rs.getString("descrizione"));

                list.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public boolean update(Categoria c) {

        String sql = "UPDATE categoria SET nome_categoria=?, descrizione=? WHERE id_categoria=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNomeCategoria());
            ps.setString(2, c.getDescrizione());
            ps.setInt(3, c.getId_categoria());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM categoria WHERE id_categoria=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}