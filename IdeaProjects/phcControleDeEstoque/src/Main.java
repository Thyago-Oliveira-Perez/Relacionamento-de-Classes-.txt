import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.lang.String;
import static java.lang.System.exit;


public class Main{

    public static void main(String[] args) throws IOException {

        Produto produto = new Produto();
        ListaDeProdutos listaDeProdutos = new ListaDeProdutos();
        ListaDeCategorias listaDeCategorias = new ListaDeCategorias();
        Categoria categoria = new Categoria();


        List<String> listaDoArquivoProdutos = new ArrayList();
        List<String> listaDoArquivoCategorias = new ArrayList<>();

        Scanner entrada = new Scanner(System.in).useDelimiter("\n");

        int validacao = 0;
        String tipo = "";

        /////////////////// arquivo das categorias //////////////////////
        File arquivoDeTextoCat = new File("ListaDasCategorias.txt");

        if(!arquivoDeTextoCat.isFile()){
            arquivoDeTextoCat.createNewFile();
        }

        FileWriter arquivoTxtCat = new FileWriter(arquivoDeTextoCat, true);
        PrintWriter gravaTxtCat = new PrintWriter(arquivoTxtCat);

        FileReader arquivoDeTextoCat1 = new FileReader(arquivoDeTextoCat);
        BufferedReader leArquivoTxtCat = new BufferedReader(arquivoDeTextoCat1);
        /////////////////// arquivo das categorias //////////////////////

        //////////////////// arquivo dos produtos ///////////////////////
        File arquivoDeTexto = new File ("ListaDosProdutos.txt");

        if(!arquivoDeTexto.isFile()){
            arquivoDeTexto.createNewFile();
        }

        FileWriter arquivoTxt = new FileWriter(arquivoDeTexto, true);
        PrintWriter gravaArquivoTxt = new PrintWriter(arquivoTxt);

        FileReader arquivoTxt1 = new FileReader(arquivoDeTexto);
        BufferedReader lerArquivoTxt = new BufferedReader(arquivoTxt1);
        //////////////////// arquivo dos produtos ///////////////////////

        //começa o programa de fato
        while(true){

            int opcao = Menu(entrada);

            switch (opcao) {

                case 1:

                    //Lê o arquivo
                    Path path2 = Paths.get("ListaDasCategorias.txt");
                    listaDoArquivoCategorias = Files.readAllLines(path2);
                    //

                    mostraOsTodosAsCategorias(listaDoArquivoCategorias, entrada);

                    System.out.println("Para registar novos produtos\npressione 1, para sair pressiona qualquer número.");
                    String registrar = entrada.next();

                    int registrarOuNãoCat = Integer.parseInt(registrar);

                    while(true) {
                        
                        if (isNumeric(registrar)) {

                            if (registrarOuNãoCat == 1) {

                                System.out.println("Escolha a Categoria:");
                                int catEscolhida = entrada.nextInt();

                                while(true) {

                                    ////instanciando o arquivo
                                    registaOsProdutos(entrada, validacao, tipo, produto, listaDeProdutos, catEscolhida);
                                    ////

                                    System.out.println("Deseja continuar?");
                                    System.out.println("1 - Sim | 2 - Não");
                                    int continuarOuNao = entrada.nextInt();

                                    salvaProdutosNoArquivo(continuarOuNao, listaDeProdutos, gravaArquivoTxt, arquivoTxt);
                                    break;
                                }
                                break;
                            } else {

                                break;

                            }
                        } else {

                            System.out.println("Digite um valor válido, por favor.");

                        }
                    }

                break;

                case 2:

                    //Lê o arquivo produtos
                    Path path = Paths.get("ListaDosProdutos.txt");
                    listaDoArquivoProdutos = Files.readAllLines(path);
                    //

                    //Mostrando os produtos que estão no arquivo
                    mostraOsTodosOsProdutos(listaDoArquivoProdutos, entrada);
                    //

                    System.out.println("Para registar novos produtos\nPressione 1, para sair pressiona qualquer número.");
                    String registrarOuNãoProd = entrada.next();

                    int registrarOuNÃOProd = Integer.parseInt(registrarOuNãoProd);

                    while(true){

                        if(isNumeric(registrarOuNãoProd)){

                            if(registrarOuNÃOProd == 1){

                                System.out.println("Escolha o produto: ");
                                int produtoEscolhido = entrada.nextInt();

                                if(produtoEscolhido!=0){

                                    String produtoParaEditar = listaDoArquivoProdutos.get(produtoEscolhido - 1);
                                    String[] editaOsValores = produtoParaEditar.split(";");

                                    Produto produtoEditar = new Produto(editaOsValores[0], editaOsValores[1], editaOsValores[2], editaOsValores[3], editaOsValores[4], editaOsValores[5]);

                                    System.out.println("1 - Editar \n2 - Excluir \n3 - Sair");
                                    int editOuExcl =  entrada.nextInt();

                                    switch (editOuExcl){

                                        case 1:

                                            editaOsProdutos(listaDoArquivoProdutos, entrada, produtoEditar, produtoEscolhido, listaDoArquivoCategorias, categoria, listaDoArquivoCategorias);

                                            ////salva o arquivo já editado
                                            arquivoTxt = new FileWriter(arquivoDeTexto, false);
                                            gravaArquivoTxt = new PrintWriter(arquivoTxt);

                                            for(int p = 0; p < listaDoArquivoProdutos.size(); p++){
                                                gravaArquivoTxt.println(listaDoArquivoProdutos.get(p));
                                            }

                                            gravaArquivoTxt.flush();
                                            arquivoTxt.close();
                                            gravaArquivoTxt.close();
                                            ////salva o arquivo

                                            break;

                                        case 2:

                                            ////remove o item da lista antes de salvar no arquivo
                                            listaDoArquivoProdutos.remove(produtoEscolhido - 1);
                                            ////

                                            salvaArquivoProdutosEditado(arquivoTxt, arquivoDeTexto, gravaArquivoTxt, listaDoArquivoProdutos);

                                            break;
                                    }
                                    break;
                                }
                            }else{

                                break;

                            }

                        }else{

                            System.out.println("Digite um valor válido, por favor.");

                        }
                    }
                break;

                case 3:

                    registrarCategorias(entrada, categoria, listaDeCategorias);

                    System.out.println("Deseja continuar?");
                    System.out.println("1 - Sim | 2 - Não");
                    int continuarOuNao = entrada.nextInt();

                    salvaCategoriasNoArquivo(continuarOuNao, listaDeCategorias, gravaTxtCat, arquivoTxtCat);

                break;

                case 0:

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

        System.out.println("1 - Mostrar Categorias");
        System.out.println("2 - Mostrar produtos");
        System.out.println("3 - Cadastrar Categorias");
        System.out.println("0 - Sair");
        int opcao = entrada.nextInt();

        return opcao;

    }

    public static void mostraOsTodosOsProdutos(List<String> listaDoArquivo, Scanner entrada){

        System.out.println("---------");
        System.out.println("Produtos:");
        System.out.println("---------");

        if(listaDoArquivo.isEmpty()){

            System.out.println("Lista vazia. Por favor adicione items primeiro");

        }else{

            for (int j = 0; j < listaDoArquivo.size(); j++) {

                String produtoDalista = listaDoArquivo.get(j);

                String[] valorEditar = produtoDalista.split(";");

                int idCategoria = Integer.parseInt(valorEditar[6]);

                Produto p = new Produto(valorEditar[0], valorEditar[1], valorEditar[2], valorEditar[3], valorEditar[4], valorEditar[5], idCategoria);

                System.out.println((j+1)+"   "+p.Mostrar());

            }
        }
    }

    public static void mostraOsTodosAsCategorias(List<String> listaDoArquivoCategorias, Scanner entrada){

        System.out.println("------------------");
        System.out.println("Categorias:");
        System.out.println("------------------");

        if(listaDoArquivoCategorias.isEmpty()){

            System.out.println("Lista vazia. Por favor adicione items primeiro");

        }else{

            for (int j = 0; j < listaDoArquivoCategorias.size(); j++) {

                String produtoDalista = listaDoArquivoCategorias.get(j);

                Categoria categoria = new Categoria(produtoDalista);

                System.out.println((j+1) + " - "+ " id: " + j + " Categoria: " + categoria.mostrar());

            }
        }
    }

    public static void registrarCategorias(Scanner entrada, Categoria categoria, ListaDeCategorias listaDeCategorias){

        System.out.println("Nome da Categoria: ");
        String nomeDaCategoria = entrada.next();

        categoria.setNome(nomeDaCategoria);

        listaDeCategorias.getLista().add(categoria.mostrar());

    }

    public static void editaOsProdutos(List<String> listaDoArquivo, Scanner entrada, Produto produtoEditar, int produtoEscolhido, List listaDoArquivoCategoria, Categoria categoria, List listaDoArquivoCategorias) throws IOException {

        String novaInfo = "";

        System.out.println("Escolha o que editar:");
        System.out.println("1 - Id:     4 - Tipo:");
        System.out.println("2 - Nome:       5 - Quantidade:");
        System.out.println("3 - N° Serial:      6 - Estado de Conservação:");
        System.out.println("7 - Categoria:");

        int infoParaEditar = entrada.nextInt();

        switch(infoParaEditar - 1){

            case 0:
                System.out.println("Digite o novo valor:");
                novaInfo = entrada.next();
                produtoEditar.setId(novaInfo);
                break;
            case 1:
                System.out.println("Digite o novo valor:");
                novaInfo = entrada.next();
                produtoEditar.setNome(novaInfo);
                break;
            case 2:
                System.out.println("Digite o novo valor:");
                novaInfo = entrada.next();
                produtoEditar.setNumSerial(novaInfo);
                break;
            case 3:
                System.out.println("Digite o novo valor:");
                novaInfo = entrada.next();
                produtoEditar.setTipo(novaInfo);
                break;
            case 4:
                System.out.println("Digite o novo valor:");
                novaInfo = entrada.next();
                produtoEditar.setQuantidade(novaInfo);
                break;
            case 5:
                System.out.println("Digite o novo valor:");
                novaInfo = entrada.next();
                produtoEditar.setEstadoConservacao(novaInfo);
                break;

            case 6:

                Path path2 = Paths.get("ListaDasCategorias.txt");
                listaDoArquivoCategorias = Files.readAllLines(path2);

                int catDisponiveis = listaDoArquivoCategorias.size();

                for (int j = 0; j < catDisponiveis; j++) {

                    String produtoDalista = String.valueOf(listaDoArquivoCategorias.get(j));

                    Categoria categoria1 = new Categoria(produtoDalista);

                    System.out.println(j + " " + categoria1.getNome());

                }

                while(true){

                    System.out.println("Digite o novo valor:");
                    novaInfo = entrada.next();

                    int idCat = Integer.parseInt(novaInfo);

                    if(idCat < catDisponiveis && idCat > (-1)){
                        produtoEditar.setIdCategoria(idCat);
                        break;
                    }else{
                        System.out.println("Digite uma categoria válida.");
                    }
                }

                break;
        }

        listaDoArquivo.remove(produtoEscolhido - 1);

        listaDoArquivo.add(produtoEscolhido - 1, produtoEditar.toString());

    }

    public static void registaOsProdutos(Scanner entrada, int validacao, String tipo, Produto produto, ListaDeProdutos listaDeProdutos, int catEscolhida){

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

        produto = new Produto(id, nome, numSerial, tipo, quant, estConservacao, (catEscolhida - 1));
        System.out.println(produto);

        listaDeProdutos.getLista().add(produto);

    }

    public static void salvaCategoriasNoArquivo(int continuarOuNao, ListaDeCategorias listaDeCategorias, PrintWriter gravaTxtCat, FileWriter arquivoTxtCat) throws IOException {

        if (continuarOuNao == 2) {

            for (int i = 0; i < listaDeCategorias.size(); i++) {

                gravaTxtCat.println(listaDeCategorias.getLista().get(i));

            }

            //limpa a lista para nao salvar duplicado no arquivo
            //devido ao menu ser navegavel
            listaDeCategorias.clear();

            gravaTxtCat.flush();
            arquivoTxtCat.close();
            gravaTxtCat.close();

        }

    }

    public static void salvaProdutosNoArquivo(int continuarOuNao, ListaDeProdutos listaDeProdutos, PrintWriter gravaArquivoTxt, FileWriter arquivoTxt) throws IOException {

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

        }

    }

    public static void salvaArquivoProdutosEditado(FileWriter arquivoTxt, File arquivoDeTexto, PrintWriter gravaArquivoTxt, List listaDoArquivoProdutos) throws IOException {

        ////salva o arquivo já editado
        arquivoTxt = new FileWriter(arquivoDeTexto, false);
        gravaArquivoTxt = new PrintWriter(arquivoTxt);

        for(int p = 0; p < listaDoArquivoProdutos.size(); p++){
            gravaArquivoTxt.println(listaDoArquivoProdutos.get(p));
        }

        gravaArquivoTxt.flush();
        arquivoTxt.close();
        gravaArquivoTxt.close();
        ////salva o arquivo

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}