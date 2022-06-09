package br.vrbeneficios.cidadaos.model;

public class Logradouro {
    private Integer id;
    private String cidade;
    private Integer regiao;
    private String estado;

    public Logradouro(Integer id, String cidade, Integer regiao, String estado) {
        this.id = id;
        this.cidade = cidade;
        this.regiao = regiao;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(this.id != null){
            throw new IllegalArgumentException("Não é possível atribuir um novo" +
                    " valor para um id que já existe");
        }
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getRegiao() {
        return regiao;
    }

    public void setRegiao(Integer regiao) {
        this.regiao = regiao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
