import javax.sound.sampled.Control;
import javax.sound.sampled.Port;
import javax.xml.stream.events.EndDocument;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.lang.String;
import static java.lang.System.exit;


public class Main{

    public static void main(String[] args) throws IOException {

        Produto produto = new Produto();
        ListaDeProdutos listaDeProdutos = new ListaDeProdutos();

        Scanner entrada = new Scanner(System.in).useDelimiter("\n");

        int validacao = 0;
        String tipo = "";

        File arquivoDeTexto = new File ("ListaDosProdutos.txt");

        if(!arquivoDeTexto.isFile()){
            arquivoDeTexto.createNewFile();
        }

        FileWriter arquivoTxt = new FileWriter(arquivoDeTexto, true);
        PrintWriter gravaArquivoTxt = new PrintWriter(arquivoTxt);
        gravaArquivoTxt.println(listaDeProdutos);

        FileReader arquivoTxt1 = new FileReader(arquivoDeTexto);
        BufferedReader lerArquivoTxt = new BufferedReader(arquivoTxt1);

        //começa o programa de fato
        while(true){

            int opcao = Menu(entrada);

            switch (opcao) {

                case 1:

                    while (true) {

                        ////instanciando o arquivo
                        registaOsProdutos(entrada, validacao, tipo, produto, listaDeProdutos);
                        ////

                        System.out.println("Deseja continuar?");
                        System.out.println("1 - Sim | 2 - Não");
                        int continuarOuNao = entrada.nextInt();

                        if (continuarOuNao == 2) {

                            for (int i = 0; i < listaDeProdutos.size(); i++) {

                                gravaArquivoTxt.println(listaDeProdutos.getLista().get(i));

                            }

                            //limpa a lista para nao salvar duplicado no arquivo
                            //devido ao menu ser navegavel
                            listaDeProdutos.clear();

                            gravaArquivoTxt.flush();
                            arquivoTxt.close();
                            gravaArquivoTxt.close();

                            break;
                        }
                    }
                    break;

                case 2:

                    //Lê o arquivo
                    Path path = Paths.get("ListaDosProdutos.txt");
                    List <String> listaDoArquivo = Files.readAllLines(path);
                    //

                    //Mostrando os produtos que estão no arquivo
                    int produtoEscolhido = mostraOsTodosOsProdutos(listaDoArquivo, entrada);
                    //

                    if(produtoEscolhido!=0){

                        String produtoParaEditar = listaDoArquivo.get(produtoEscolhido - 1);
                        String[] editaOsValores = produtoParaEditar.split(";");

                        Produto produtoEditar = new Produto(editaOsValores[0], editaOsValores[1], editaOsValores[2], editaOsValores[3], editaOsValores[4], editaOsValores[5]);

                        System.out.println("1 - Editar \n2 - Excluir \n3 - Sair");
                        int editOuExcl =  entrada.nextInt();

                        switch (editOuExcl){

                            case 1:

                                editaOsProdutos(listaDoArquivo, entrada, produtoEditar, produtoEscolhido);

                                ////salva o arquivo já editado
                                arquivoTxt = new FileWriter(arquivoDeTexto, false);
                                gravaArquivoTxt = new PrintWriter(arquivoTxt);

                                for(int p = 0; p < listaDoArquivo.size(); p++){
                                    gravaArquivoTxt.println(listaDoArquivo.get(p));
                                }

                                gravaArquivoTxt.flush();
                                arquivoTxt.close();
                                gravaArquivoTxt.close();
                                ////salva o arquivo

                                break;

                            case 2:

                                ////remove o item da lista antes de salvar no arquivo
                                listaDoArquivo.remove(produtoEscolhido - 1);
                                ////

                                ////salva o arquivo já editado
                                arquivoTxt = new FileWriter(arquivoDeTexto, false);
                                gravaArquivoTxt = new PrintWriter(arquivoTxt);

                                for(int p = 0; p < listaDoArquivo.size(); p++){
                                    gravaArquivoTxt.println(listaDoArquivo.get(p));
                                }

                                gravaArquivoTxt.flush();
                                arquivoTxt.close();
                                gravaArquivoTxt.close();
                                ////salva no arquivo

                                break;
                        }

                    }
                break;
                case 3:

                    System.out.println("Deseja mesmo sair?");
                    System.out.println("1 - Sim | 2 - Não");
                    int sair = entrada.nextInt();

                    if (sair == 1) {
                        exit(0);
                    }
                break;
            }
        }

    }

    ///////functions

    public static int Menu(Scanner entrada) {

        System.out.println("----------------");
        System.out.println("---Bem vindo!---");
        System.out.println("----------------");

        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Mostrar produtos");
        System.out.println("3 - Sair");
        int opcao = entrada.nextInt();

        return opcao;

    }

    public static int mostraOsTodosOsProdutos(List<String> listaDoArquivo, Scanner entrada){

        int produtoEscolhido = 0;
        
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.println("     ID           NOME            N° Serial            Tipo            Quantidade            Conservação");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        if(listaDoArquivo.isEmpty()){

            System.out.println("Lista vazia. Por favor adicione items primeiro");

        }else{

            for (int j = 0; j < listaDoArquivo.size(); j++) {

                String produtoDalista = listaDoArquivo.get(j);

                String[] valorEditar = produtoDalista.split(";");

                Produto p = new Produto(valorEditar[0], valorEditar[1], valorEditar[2], valorEditar[3], valorEditar[4], valorEditar[5]);

                System.out.println((j+1) + " " +p.Mostrar());

                System.out.println("Escolha o produto:");
                produtoEscolhido = entrada.nextInt();

            }

        }
        return produtoEscolhido;
    }

    public static void editaOsProdutos(List<String> listaDoArquivo, Scanner entrada, Produto produtoEditar, int produtoEscolhido){

        System.out.println("Editar:");
        System.out.println("1 - Id:     4 - Tipo:");
        System.out.println("2 - Nome:       5 - Quantidade:");
        System.out.println("3 - N° Serial:      6 - Estado de Conservação:");
        int infoParaEditar = entrada.nextInt();

        System.out.println("Digite o novo valor:");
        String novaInfo = entrada.next();

        switch(infoParaEditar - 1){

            case 0:
                produtoEditar.setId(novaInfo);
                break;
            case 1:
                produtoEditar.setNome(novaInfo);
                break;
            case 2:
                produtoEditar.setNumSerial(novaInfo);
                break;
            case 3:
                produtoEditar.setTipo(novaInfo);
                break;
            case 4:
                produtoEditar.setQuantidade(novaInfo);
                break;
            case 5:
                produtoEditar.setEstadoConservacao(novaInfo);
                break;
        }

        listaDoArquivo.remove(produtoEscolhido - 1);

        listaDoArquivo.add(produtoEscolhido - 1, produtoEditar.toString());

    }

    public static void registaOsProdutos(Scanner entrada, int validacao, String tipo, Produto produto, ListaDeProdutos listaDeProdutos){

        System.out.println("Id:");
        String id = entrada.next();

        System.out.println("Nome:");
        String nome = entrada.next();

        System.out.println("Numero Serial:");
        String numSerial = entrada.next();

        do {

            System.out.println("Tipo de produto:");
            System.out.println("1 - Eletronico  2 - Não Eletronico");
            int tipoP = entrada.nextInt();

            if (tipoP == 1) {

                tipo = "Eletronico";
                validacao = 1;

            } else if (tipoP == 2) {

                tipo = "Não Eletronico";
                validacao = 1;

            } else {

                System.out.println("Opção inválida. Tente novamente");

            }
        } while (validacao == 0);

        System.out.print("Quantidade:");
        String quant = entrada.next();

        System.out.print("Estado de consevação:");
        String estConservacao = entrada.next();

        produto = new Produto(id, nome, numSerial, tipo, quant, estConservacao);

        listaDeProdutos.getLista().add(produto);


    }

}