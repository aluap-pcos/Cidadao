package br.vrbeneficios.cidadaos.service;

import br.vrbeneficios.cidadaos.controller.CidadaoController;
import br.vrbeneficios.cidadaos.model.Cidadao;
import br.vrbeneficios.cidadaos.model.Logradouro;
import br.vrbeneficios.cidadaos.utils.exceptions.ArquivoNaoEncontradoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorDeArquivo {

    public LeitorDeArquivo(){}

    public List<Cidadao> lerArquivo(String arquivo){
        List<Cidadao> cidadaosNoArquivo = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(arquivo);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = bufferedReader.readLine();

            while(linha != null) {
                if(!linha.contains("Nome")) {
                    String[] aux = linha.split("\\|");

                    System.out.println(aux);

                    for (int i = 0; i < aux.length; i++) {
                        aux[i] = aux[i].trim();
                        System.out.println(aux[i]);
                    }

                    Cidadao cidadao = new Cidadao(null, aux[0], Integer.parseInt(aux[1]),
                            new Logradouro(null, aux[2], Regiao.valueOf(aux[3]).regiaoId, aux[3]));

                    CidadaoController controller = new CidadaoController();
                    controller.inserir(cidadao);
                    cidadaosNoArquivo.add(cidadao);
                }
                linha = bufferedReader.readLine();
            }

            bufferedReader.close();
            return cidadaosNoArquivo;
        } catch (FileNotFoundException e) {
            throw new ArquivoNaoEncontradoException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
