package br.vrbeneficios.cidadaos.model;

public class Cidadao {
    private Integer id;
    private String nome;
    private Integer idade;
    private Logradouro endereco;

    public Cidadao(Integer id, String nome, Integer idade, Logradouro endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Logradouro getEndereco() {
        return endereco;
    }

    public void setEndereco(Logradouro endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cidadao [" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", municipio=" + endereco.getCidade() +
                ", estado=" + endereco.getEstado() +
                ']';
    }
}
