import br.vrbeneficios.cidadaos.controller.CidadaoController;
import br.vrbeneficios.cidadaos.model.Cidadao;
import br.vrbeneficios.cidadaos.service.LeitorDeArquivo;
import br.vrbeneficios.cidadaos.utils.exceptions.ArquivoNaoEncontradoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LeitorDeArquivoTest {
    private LeitorDeArquivo leitor;
    private CidadaoController controller;

    @BeforeAll
    public void inicializa(){
        this.leitor = new LeitorDeArquivo();
        this.controller = new CidadaoController();
    }

    @Test
    public void AoLerUmArquivoVazioOLeitorRetornaraUmaCollectionVazia(){
        List<Cidadao> cidadaos = leitor.lerArquivo("vazio.txt");
        Assertions.assertEquals(true, cidadaos.isEmpty());
    }

    @Test
    public void aoLerUmArquivoQueNaoExisteDeveLancarUmaException(){
        Assertions.assertThrows(ArquivoNaoEncontradoException.class, () -> leitor.lerArquivo("inexistente.txt"));
    }

    @Test
    public void aoLerUmArquivoComUmaLinhaDeveRetornar1Cidadao(){
        List<Cidadao> cidadaos = leitor.lerArquivo("vazio.txt");
        Assertions.assertEquals(1, cidadaos.size());
    }

    @Test
    public void aoLerUmArquivoComNLinhasDeveRetornarUmaCollectionComNCidadaos() {
        List<Cidadao> cidadaos = leitor.lerArquivo("arquivo.txt");
        int cont = -1;
        try {
            FileInputStream fileInputStream = new FileInputStream("arquivo.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = bufferedReader.readLine();

            while (linha != null) {
                cont++;
                linha = bufferedReader.readLine();
            }

            bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        Assertions.assertEquals(cont, cidadaos.size());
    }
}
