package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DBConnection;
import model.Pagamento;
import model.Ordine;

public class PagamentoDAO {

    public boolean insert(Pagamento p) {

        String sql = "INSERT INTO pagamento (id_ordine, importo, stato_pagamento) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getOrdine().getId_ordine());
            ps.setDouble(2, p.getImporto());
            ps.setString(3, p.getStato_pagamento().name().toLowerCase());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pagamento getByOrdine(int idOrdine) {

        String sql = "SELECT * FROM pagamento WHERE id_ordine=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idOrdine);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Pagamento p = new Pagamento();

                p.setId_pagamento(rs.getInt("id_pagamento"));
                p.setImporto(rs.getDouble("importo"));

                p.setStato_pagamento(
                        Pagamento.StatoPagamento.valueOf(rs.getString("stato_pagamento").toUpperCase())
                );

                Ordine o = new Ordine();
                o.setId_ordine(rs.getInt("id_ordine"));
                p.setOrdine(o);

                return p;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public boolean updateStato(int idPagamento, Pagamento.StatoPagamento stato) {

        String sql = "UPDATE pagamento SET stato_pagamento=? WHERE id_pagamento=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, stato.name().toLowerCase());
            ps.setInt(2, idPagamento);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int idPagamento) {

        String sql = "DELETE FROM pagamento WHERE id_pagamento=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPagamento);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}