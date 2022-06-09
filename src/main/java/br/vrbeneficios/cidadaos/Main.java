package br.vrbeneficios.cidadaos;

import br.vrbeneficios.cidadaos.controller.CidadaoController;
import br.vrbeneficios.cidadaos.model.Cidadao;
import br.vrbeneficios.cidadaos.service.LeitorDeArquivo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo();
        leitorDeArquivo.lerArquivo("arquivo.txt");

        CidadaoController controller = new CidadaoController();

        System.out.println("Cidadãos que residem na regiao sudeste");
        List<Cidadao> cidadaosSudeste = controller.buscarSomenteOsDaRegiaoSudeste();
        cidadaosSudeste.forEach(System.out::println);

        System.out.println("Cidadãos com idade maior que 30 anos");
        List<Cidadao> cidadaos30Mais = controller.buscarSomenteOsMaioresDe30();
        cidadaos30Mais.forEach(System.out::println);
    }
}
