package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import model.bean.Bandeira;

/**
 * @author eucri
 */
public class BandeiraDAO {
    
    public void create(Bandeira b) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbbandeira (nomePais, nomeOficial, capital, codISO, continente, bandeira) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, b.getNomePais());
            stmt.setString(2, b.getNomeOficial());
            stmt.setString(3, b.getCapital());
            stmt.setString(4, b.getCodISO());
            stmt.setString(5, b.getContinente());
            stmt.setString(6, b.getBandeira());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar "+ex);
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Bandeira> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Bandeira> bandeiras = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM tbbandeira");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Bandeira bandeira = new Bandeira();
                bandeira.setIdPais(rs.getInt("idPais"));
                bandeira.setNomePais(rs.getString("nomePais"));
                bandeira.setNomeOficial(rs.getString("nomeOficial"));
                bandeira.setCapital(rs.getString("capital"));
                bandeira.setCodISO(rs.getString("codISO"));
                bandeira.setContinente(rs.getString("continente"));
                bandeira.setBandeira(rs.getString("bandeira"));
                bandeiras.add(bandeira);
                System.out.println(bandeira.getNomePais());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return bandeiras;
    }
}
