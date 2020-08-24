package model.dao;

import connection.ConnectionFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;
import model.bean.Bandeira;

public class BandeiraDAO {

    // - - - - - - - - -  CREATE IF NOT EXISTS - - - - - - - - - - - -
    public void createTable() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "CREATE TABLE IF NOT EXISTS `tbbandeira` "
                    + "( `idPais` int(3) NOT NULL AUTO_INCREMENT, "
                    + "`nomePais` varchar(128) COLLATE utf8_unicode_ci NOT NULL, "
                    + "`nomeOficial` varchar(128) COLLATE utf8_unicode_ci NOT NULL, "
                    + "`capital` varchar(128) COLLATE utf8_unicode_ci NOT NULL, "
                    + "`codISO` varchar(3) COLLATE utf8_unicode_ci NOT NULL, "
                    + "`continente` varchar(128) COLLATE utf8_unicode_ci NOT NULL, "
                    + "`bandeira` blob NOT NULL, PRIMARY KEY (`idPais`) ) "
                    + "ENGINE=MyISAM AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 "
                    + "COLLATE=utf8_unicode_ci";

            stmt = con.prepareStatement(sql);
            stmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // - - - - - - - - - - - - - - INSERT - - - - - - - - - - - - - - 
    public void create(Bandeira b) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement("INSERT INTO tbbandeira "
                    + "(nomePais, nomeOficial, capital, codISO, continente, bandeira) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, b.getNomePais());
            stmt.setString(2, b.getNomeOficial());
            stmt.setString(3, b.getCapital());
            stmt.setString(4, b.getCodISO());
            stmt.setString(5, b.getContinente());
            stmt.setBlob(6, b.getBandeira());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar " + ex);
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // - - - - - - - - - - - - - - SELECT - - - - - - - - - - - - - - 
    public List<Bandeira> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Bandeira> bandeiras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbbandeira");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bandeira bandeira = new Bandeira();
                bandeira.setIdPais(rs.getInt("idPais"));
                bandeira.setNomePais(rs.getString("nomePais"));
                bandeira.setNomeOficial(rs.getString("nomeOficial"));
                bandeira.setCapital(rs.getString("capital"));
                bandeira.setCodISO(rs.getString("codISO"));
                bandeira.setContinente(rs.getString("continente"));
                bandeira.setBandeira(rs.getBlob("bandeira"));
                bandeiras.add(bandeira);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return bandeiras;
    }

    // - - - - - - - - - - - - - - SELECT BY ID - - - - - - - - - - - - - - 
    public List readById(Bandeira b) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Bandeira> bandeiras = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbbandeira WHERE idPais=?");
            stmt.setInt(1, b.getIdPais());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Bandeira bById = new Bandeira();
                bById.setIdPais(rs.getInt("idPais"));
                bById.setNomePais(rs.getString("nomePais"));
                bById.setNomeOficial(rs.getString("nomeOficial"));
                bById.setCapital(rs.getString("capital"));
                bById.setCodISO(rs.getString("codISO"));
                bById.setContinente(rs.getString("continente"));

                // - - - - - tentativa de mostrar a imagem no app - - - - - - -
                InputStream is = rs.getBinaryStream("bandeira");
                OutputStream os = new FileOutputStream(new File("src/resources/a.png"));
                byte[] content = new byte[1024];
                int size = 0;
                os.write(content, 0, size);
                os.close();
                is.close();
                Image imagex = new Image("file:a.png", 250, 250, true, true);
                bById.setImage(imagex);
                // - - - - - - - - - - - - - - - - - - - - - - - - - -
                bById.setBandeira(rs.getBlob("bandeira"));
                bandeiras.add(bById);
            }

        } catch (SQLException | IOException ex) {
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return bandeiras;
    }

    // - - - - - - - - - - - - - - UPDATE - - - - - - - - - - - - - - 
    public void update(Bandeira b) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE tbbandeira SET nomePais=?, nomeOficial=?, capital=?, codISO=?, continente=?, bandeira=? WHERE idPais=?");
            stmt.setString(1, b.getNomePais());
            stmt.setString(2, b.getNomeOficial());
            stmt.setString(3, b.getCapital());
            stmt.setString(4, b.getCodISO());
            stmt.setString(5, b.getContinente());
            stmt.setBlob(6, b.getBandeira());
            stmt.setInt(7, b.getIdPais());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar " + ex);
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    // - - - - - - - - - - - - - - DELETE - - - - - - - - - - - - - - 
    public void delete(Bandeira b) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tbbandeira WHERE idPais=?");
            stmt.setInt(1, b.getIdPais());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir " + ex);
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
