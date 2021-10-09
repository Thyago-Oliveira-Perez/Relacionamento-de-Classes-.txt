import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Main{

    public static void main(String[] args) throws IOException {

        Produto produto = new Produto();
        TipoDeProduto tipo = new TipoDeProduto();
        ListaDeProdutos listaDeProdutos = new ListaDeProdutos();

        Scanner entrada = new Scanner(System.in);
        int validacao = 0;
        int validacao1 = 0;

        FileWriter arquivo = new FileWriter("C:\\Users\\Thyago\\Desktop\\Panorama - ERP\\IdeaProjects\\ListaDosProdutos.txt");
        PrintWriter gravarArq = new PrintWriter(arquivo);

        System.out.println("----------------");
        System.out.println("---Bem vindo!---");
        System.out.println("----------------");

        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Editar Produto");
        System.out.println("3 - Sair");

        switch(entrada.nextInt()){

            case 1:

                while(validacao1 == 0){

                    System.out.println("Id:");
                    long id = entrada.nextLong();

                    System.out.println("Nome:");
                    String nome = entrada.next();

                    System.out.println("Numero Serial:");
                    String numSerial = entrada.next();

                    System.out.println("Tipo de produto:");
                    System.out.println("1 - Eletronico  2 - Não Eletronico");
                    int tipoP = entrada.nextInt();

                    do {
                        if (tipoP == 1) {

                            tipo.setTipo("Eletronico");
                            validacao = 1;

                        } else if (tipoP == 2) {

                            tipo.setTipo("Não Eletronico");
                            validacao = 1;

                        } else {

                            System.out.println("Opção inválida. Tente novamente");

                        }
                    } while (validacao == 0);

                    System.out.printf("Quantidade:");
                    int quant = entrada.nextInt();

                    System.out.printf("Estado de consevação:");
                    String estConservacao = entrada.next();

                    produto = new Produto(id, nome, numSerial, tipo, quant, estConservacao);

                    listaDeProdutos.getLista().add(produto);

                    System.out.println("Deseja continuer?");
                    System.out.println("1 - Sim | 2 - Não");
                    int continuarOuNao = entrada.nextInt();

                    if (continuarOuNao == 2) {

                        gravarArq.printf(String.valueOf(listaDeProdutos));
                        arquivo.close();                                   
                        break;
                    }
                }

            break;

            case 2:



            break;
        }

    }

}