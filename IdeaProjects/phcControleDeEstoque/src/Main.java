import javax.xml.stream.events.EndDocument;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import static java.lang.System.exit;



public class Main{

    public static void main(String[] args) throws IOException {

        Produto produto = new Produto();
        ListaDeProdutos listaDeProdutos = new ListaDeProdutos();
        List<String> listaDoArquivo = new ArrayList<>();

        Scanner entrada = new Scanner(System.in);
        int validacao = 0;
        int validacao1 = 0;
        String tipo = "";

        String arquivoDosProdutos = "C:\\Users\\Thyago\\Desktop\\ERP_inventory\\IdeaProjects\\ListaDosProdutos.txt";

        FileWriter arquivoTxt = new FileWriter(arquivoDosProdutos, true);
        PrintWriter gravaArquivoTxt = new PrintWriter(arquivoTxt);

        FileReader arquivoTxt1 = new FileReader(arquivoDosProdutos);
        BufferedReader lerArquivoTxt = new BufferedReader(arquivoTxt1);

        while(true){

            int opcao = Menu(entrada);

            switch (opcao) {

                case 1:

                    while (validacao1 == 0) {

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

                        System.out.printf("Quantidade:");
                        String quant = entrada.next();

                        System.out.printf("Estado de consevação:");
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

                    while(lendo != null){

                        lendo = lerArquivoTxt.readLine();
                        listaDoArquivo.add(i, lendo + "\n");
                        i++;
                    }

                    System.out.println(listaDoArquivo);

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