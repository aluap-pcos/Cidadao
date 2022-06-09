package br.vrbeneficios.cidadaos.dao;

import br.vrbeneficios.cidadaos.model.Cidadao;
import br.vrbeneficios.cidadaos.model.Logradouro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadaoDao {
    private Connection conexao;

    public CidadaoDao(Connection conexao){
        this.conexao = conexao;
    }

    public void inserirPessoa(Cidadao cidadao){
        inserirLogradouro(cidadao);
        try(PreparedStatement preparedStatement =
                    conexao.prepareStatement("INSERT INTO PESSOAS (nome, idade, endereco) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, cidadao.getNome());
            preparedStatement.setInt(2, cidadao.getIdade());
            preparedStatement.setInt(3, cidadao.getEndereco().getId());
            preparedStatement.execute();

            cidadao.setId(buscarChavesGeradas(preparedStatement).get(0));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void inserirLogradouro(Cidadao cidadao){
        try(PreparedStatement preparedStatement =
                    conexao.prepareStatement("INSERT INTO logradouro (cidade, estado, regiao) " +
                            "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, cidadao.getEndereco().getCidade());
            preparedStatement.setString(2, cidadao.getEndereco().getEstado());
            preparedStatement.setInt(3, cidadao.getEndereco().getRegiao());

            preparedStatement.execute();

            cidadao.getEndereco().setId(buscarChavesGeradas(preparedStatement).get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> buscarChavesGeradas(PreparedStatement preparedStatement) throws SQLException {
        List<Integer> chavesGeradas = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
            while (resultSet.next()){
                chavesGeradas.add(resultSet.getInt(1));
            }
        }

        return chavesGeradas;
    }

    public List<Cidadao> buscarSomenteOsDaRegiaoSudeste(){
        List<Cidadao> cidadaos = new ArrayList<>();
        try(PreparedStatement preparedStatement = conexao.prepareStatement
                ("SELECT P.id, P.nome, P.idade, L.cidade, L.estado, L.regiao FROM pessoas AS P JOIN " +
                        "logradouro AS L JOIN regiao AS R ON L.regiao = R.id ON P.endereco = L.id " +
                        "WHERE R.id = 4")){

            preparedStatement.execute();
            cidadaos = retornaCidadaos(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidadaos;
    }

    public List<Cidadao> buscarSomenteOsMaioresDe30(){
        List<Cidadao> cidadaos = new ArrayList<>();
        try(PreparedStatement preparedStatement = conexao.prepareStatement
                ("SELECT P.id, P.nome, P.idade, L.cidade, L.estado, L.regiao FROM pessoas AS P JOIN " +
                        "logradouro AS L ON P.endereco = L.id WHERE P.idade >= 30")){

            preparedStatement.execute();
            cidadaos = retornaCidadaos(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidadaos;
    }

    private List<Cidadao> retornaCidadaos(PreparedStatement preparedStatement) throws SQLException {
        List<Cidadao> cidadaos = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.getResultSet()){
            while (resultSet.next()){
                cidadaos.add(new Cidadao(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), new Logradouro(null, resultSet.getString(4),
                        resultSet.getInt(6), resultSet.getString(5))));
            }
        }

        return cidadaos;
    }
}
