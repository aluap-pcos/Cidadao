package br.vrbeneficios.cidadaos.controller;

import br.vrbeneficios.cidadaos.dao.CidadaoDao;
import br.vrbeneficios.cidadaos.factory.Conexao;
import br.vrbeneficios.cidadaos.model.Cidadao;

import java.sql.Connection;
import java.util.List;

public class CidadaoController {
    private Connection conexao;
    private CidadaoDao cidadaoDao;

    public CidadaoController(){
        this.conexao = new Conexao().recuperaConexao();
        this.cidadaoDao = new CidadaoDao(conexao);
    }

    public void inserir(Cidadao cidadao){
        cidadaoDao.inserirPessoa(cidadao);
    }

    public List<Cidadao> buscarSomenteOsDaRegiaoSudeste(){
        return cidadaoDao.buscarSomenteOsDaRegiaoSudeste();
    }

    public List<Cidadao> buscarSomenteOsMaioresDe30(){
        return cidadaoDao.buscarSomenteOsMaioresDe30();
    }
}
