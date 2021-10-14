import javax.xml.stream.events.EndDocument;
import java.io.*;
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
        List<String> listaDoArquivo = new ArrayList<>();

        Scanner entrada = new Scanner(System.in).useDelimiter("\n");

        int validacao = 0;
        int validacao1 = 0;
        String tipo = "";

        File arquivoDeTexto = new File ("ListaDosProdutos.txt");

        if(!arquivoDeTexto.isFile()){
            arquivoDeTexto.createNewFile();
        }

        FileWriter arquivoTxt = new FileWriter(arquivoDeTexto, true);
        PrintWriter gravaArquivoTxt = new PrintWriter(arquivoTxt);

        FileReader arquivoTxt1 = new FileReader(arquivoDeTexto);
        BufferedReader lerArquivoTxt = new BufferedReader(arquivoTxt1);

        while(true){

            int opcao = Menu(entrada);

            switch (opcao) {

                case 1:

                    while (true) {

                        arquivoTxt = new FileWriter(arquivoDeTexto, true);
                        gravaArquivoTxt = new PrintWriter(arquivoTxt);

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

                    String lendo = "";
                    int i = 0;
                    lendo = "A";

                    while (lendo != null) {

                        lendo = lerArquivoTxt.readLine();

                        if (lendo != null && !lendo.isEmpty()) {
                            listaDoArquivo.add(i, lendo);
                        }
                        i++;
                    }

                    System.out.println("--------------------------------------------------------------------------------------------------------");
                    System.out.println("     ID           NOME            N° Serial            Tipo            Quantidade            Conservação");
                    System.out.println("--------------------------------------------------------------------------------------------------------");

//                    String arroba = "";
//                    arroba = listaDoArquivo.get(0);
//
//                    System.out.println(arroba);

                    for (int j = 0; j < listaDoArquivo.size(); j++) {

                        String produtoDalista = listaDoArquivo.get(j);
                        String[] valorEditar = produtoDalista.split(";");

                        produto.setId(valorEditar[0]);
                        produto.setNome(valorEditar[1]);
                        produto.setNumSerial(valorEditar[2]);
                        produto.setTipo(valorEditar[3]);
                        produto.setQuantidade(valorEditar[4]);
                        produto.setEstadoConservacao(valorEditar[5]);

                        System.out.println(j + 1 + "   " + produto.Mostrar());
                    }

                    System.out.println("Escolha o produto que deseja editar:");
                    int produtoEscolhido = entrada.nextInt();

                    String produtoParaEditar = listaDoArquivo.get(produtoEscolhido - 1);
                    String[] editaOsValores = produtoParaEditar.split(";");

                    produto.setId(editaOsValores[0]);
                    produto.setNome(editaOsValores[1]);
                    produto.setNumSerial(editaOsValores[2]);
                    produto.setTipo(editaOsValores[3]);
                    produto.setQuantidade(editaOsValores[4]);
                    produto.setEstadoConservacao(editaOsValores[5]);

                    System.out.println("Editar:");
                    System.out.println("1 - Id:     4 - Tipo:");
                    System.out.println("2 - Nome:       5 - Quantidade:");
                    System.out.println("3 - N° Serial:      6 - Estado de Conservação:");
                    int infoParaEditar = entrada.nextInt();

                    System.out.println("Digite o novo valor:");
                    String novaInfo = entrada.next();

                    switch(infoParaEditar - 1){

                        case 0:
                            produto.setId(novaInfo);
                        break;
                        case 1:
                            produto.setNome(novaInfo);
                        break;
                        case 2:
                            produto.setNumSerial(novaInfo);
                        break;
                        case 3:
                            produto.setTipo(novaInfo);
                        break;
                        case 4:
                            produto.setQuantidade(novaInfo);
                        break;
                        case 5:
                            produto.setEstadoConservacao(novaInfo);
                        break;
                    }

                    listaDoArquivo.remove(produtoEscolhido - 1);

                    listaDoArquivo.add(produtoEscolhido - 1, produto.toString());

                    arquivoTxt = new FileWriter(arquivoDeTexto, false);
                    gravaArquivoTxt = new PrintWriter(arquivoTxt);

                    for(int p = 0; p < listaDoArquivo.size(); p++){
                        gravaArquivoTxt.println(listaDoArquivo.get(p));
                    }

                    gravaArquivoTxt.flush();
                    arquivoTxt.close();
                    gravaArquivoTxt.close();

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

    public static int Menu(Scanner entrada) {

        System.out.println("----------------");
        System.out.println("---Bem vindo!---");
        System.out.println("----------------");

        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Editar Produto");
        System.out.println("3 - Sair");
        int opcao = entrada.nextInt();

        return opcao;

    }

}