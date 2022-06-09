package br.vrbeneficios.cidadaos.factory;

import br.vrbeneficios.cidadaos.utils.Credenciais;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexao {
    public DataSource dataSource;

    public Conexao(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(Credenciais.getUrl());
        comboPooledDataSource.setUser(Credenciais.getUsuario());
        comboPooledDataSource.setPassword(Credenciais.getSenha());

        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperaConexao(){
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível se conectar ao banco de dados");
        }
    }
}
