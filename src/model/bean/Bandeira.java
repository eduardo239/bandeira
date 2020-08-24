package model.bean;

import java.sql.Blob;
import javafx.scene.image.Image;

/**
 * @author eduardo
 */
public class Bandeira {
    
    private int idPais;
    private String nomePais;
    private String nomeOficial;
    private String capital;
    private String codISO;
    private String continente;
    private Blob bandeira;
    private Image image;
    
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public void setNomeOficial(String nomeOficial) {
        this.nomeOficial = nomeOficial;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCodISO() {
        return codISO;
    }

    public void setCodISO(String codISO) {
        this.codISO = codISO;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public Blob getBandeira() {
        return bandeira;
    }

    public void setBandeira(Blob bandeira) {
        this.bandeira = bandeira;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }



    
    


}
