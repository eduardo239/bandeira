package model.bean;

/**
 * @author eucri
 */
public class Bandeira {
    
    private int idPais;
    private String nomePais;
    private String nomeOficial;
    private String capital;
    private String codISO;
    private String continente;
    private String bandeira;

//    public Bandeira(int idPais, String nomePais, String nomeOficial, String capital, String codISO, String continente, String bandeira) {
//        this.idPais = idPais;
//        this.nomePais = nomePais;
//        this.nomeOficial = nomeOficial;
//        this.capital = capital;
//        this.codISO = codISO;
//        this.continente = continente;
//        this.bandeira = bandeira;
//    }
    
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

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }


}
