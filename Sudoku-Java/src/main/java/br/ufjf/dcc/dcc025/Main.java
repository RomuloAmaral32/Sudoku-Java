package br.ufjf.dcc.dcc025;
import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean geral = true;
        while (geral){
            Tabuleiro tabuleiro = new Tabuleiro();
            System.out.println("Bem-vindo ao Sudoku!");
            boolean key = true;
            while(key)
            {
                System.out.println("Escolha o modo de jogo:\n1 - Modo Aleatório\n2 - Modo Manual");
                String entrada = scanner.next();
                //Tabuleiro tabuleiro = new Tabuleiro();
                switch (entrada)
                {
                    case "1":
                        boolean key1 = true;
                        while (key1) {
                            System.out.println("Quantas entradas você deseja preencher?");
                            String entrada_1 = scanner.next();
                            try {
                                int iEntrada_1 = Integer.parseInt(entrada_1);

                                // Verifica se o número de entradas é válido
                                if (iEntrada_1 <= 0 || iEntrada_1 > 81) {
                                    throw new NumberFormatException("");
                                }

                                Random random = new Random();
                                int casasPreenchidas = 0;

                                // Preencher o tabuleiro automaticamente com o número de entradas desejado
                                while (casasPreenchidas < iEntrada_1) {
                                    int linha = random.nextInt(9); // Gera uma linha aleatória
                                    int coluna = random.nextInt(9); // Gera uma coluna aleatória
                                    int valor = random.nextInt(9) + 1; // Gera um valor aleatório de 1 a 9

                                    // Tenta inserir o valor na posição se for válida
                                    if (tabuleiro.getValor(linha, coluna) == 0 && tabuleiro.getElemento(linha, coluna).getPossibleValues().contains(valor)) {
                                        tabuleiro.setValor(linha, coluna, valor);
                                        tabuleiro.setValorImutavel(linha, coluna, valor);
                                        casasPreenchidas++;
                                    }
                                }

                                // Imprime o tabuleiro preenchido
                                System.out.println("Tabuleiro gerado automaticamente:");
                                tabuleiro.imprimirTabuleiro();

                                key1 = false;

                            } catch (NumberFormatException e) {
                                System.out.println("Erro: Insira um número inteiro positivo válido.");
                            }
                        }
                        key = false;
                        break;


                    // manual
                    case "2":
                        // Consome a quebra de linha remanescente
                        scanner.nextLine();

                        boolean inserirValores = true;

                        while (inserirValores) {
                            System.out.println("Insira os valores no formato ([linha],[coluna],[valor]) ou (-1,-1,-1) para terminar:");
                            String entradaManual = scanner.nextLine();

                            // Verifica se a entrada é para encerrar
                            if (entradaManual.equals("(-1,-1,-1)")) {
                                inserirValores = false;
                                System.out.println("Encerrando inserção de valores.");
                            } else {
                                // Verifica se a entrada tem o formato correto
                                if (entradaManual.matches("^(\\(\\d,\\d,\\d\\))+")) {
                                    // Separa as múltiplas entradas
                                    String[] entradas = entradaManual.split("\\)\\(");

                                    for (String item : entradas) {
                                        item = item.replace("(", "").replace(")", ""); // Remove os parênteses
                                        String[] valores = item.split(",");

                                        int linha = Integer.parseInt(valores[0]); // Ajusta para 0-indexado
                                        int coluna = Integer.parseInt(valores[1]); // Ajusta para 0-indexado
                                        int valor = Integer.parseInt(valores[2]);

                                        // Verifica se os valores estão dentro dos limites válidos
                                        if (linha >= 0 && linha < 9 && coluna >= 0 && coluna < 9 && valor > 0 && valor <= 9) {
                                           boolean veracidade = tabuleiro.setValor(linha, coluna, valor);
                                           if(veracidade)
                                               tabuleiro.setValorImutavel(linha, coluna, valor);

                                            System.out.println(tabuleiro.getElemento(linha,coluna).toString());         //teste para ver se e mutavel ou nao

                                        } else {
                                            System.out.println("Valores fora dos limites permitidos. Insira valores de 1 a 9 para linha, coluna e valor.");
                                        }
                                    }
                                } else {
                                    System.out.println("Formato inválido. Tente novamente.");
                                }
                            }
                        }
                        System.out.println("Tabuleiro manual :");
                        tabuleiro.imprimirTabuleiro();
                        key = false;
                        break;


                    default:
                        System.out.println("Entrada inválida, tente novamente!");
                        break;
                }
            }
             boolean key_jogadas = true;
                while (key_jogadas){
                    System.out.println("Escolha a jogada:\n1 - Adicionar elemento - (L,C,V)\n2 - Remover elemento - (L,C)\n3 - Dica - (L,C)\n4 - Sair do jogo");
                    String jogadas = scanner.next();
                        switch (jogadas){
                            case "1":
                                String jogadaAdd = scanner.next();
                                // Verifica se a entrada tem o formato correto
                                if (jogadaAdd.matches("^(\\(\\d,\\d,\\d\\))+")) {
                                    // Separa as múltiplas entradas
                                    String[] entradas = jogadaAdd.split("\\)\\(");

                                    for (String item : entradas) {
                                        item = item.replace("(", "").replace(")", ""); // Remove os parênteses
                                        String[] valores = item.split(",");

                                        int linha = Integer.parseInt(valores[0]); // Ajusta para 0-indexado
                                        int coluna = Integer.parseInt(valores[1]); // Ajusta para 0-indexado
                                        int valor = Integer.parseInt(valores[2]);

                                        // Verifica se os valores estão dentro dos limites válidos
                                        if (linha >= 0 && linha < 9 && coluna >= 0 && coluna < 9 && valor > 0 && valor <= 9) {
                                            tabuleiro.setValor(linha, coluna, valor);
                                            System.out.println(tabuleiro.getElemento(linha,coluna).toString());         //teste para ver se e mutavel ou nao

                                        } else {
                                            System.out.println("Valores fora dos limites permitidos. Insira valores de 1 a 9 para linha, coluna e valor.");
                                        }
                                    }
                                } else {
                                    System.out.println("Formato inválido. Tente novamente.");
                                }
                                tabuleiro.imprimirTabuleiro();
                                break;

                            case "2":
                                String jogadaRemove = scanner.next();
                                // Verifica se a entrada tem o formato correto
                                if (jogadaRemove.matches("^(\\(\\d,\\d\\))+")) {
                                    // Separa as múltiplas entradas
                                    String[] entradas = jogadaRemove.split("\\)\\(");

                                    for (String item : entradas) {
                                        item = item.replace("(", "").replace(")", ""); // Remove os parênteses
                                        String[] valores = item.split(",");

                                        int linha = Integer.parseInt(valores[0]); // Ajusta para 0-indexado
                                        int coluna = Integer.parseInt(valores[1]); // Ajusta para 0-indexado

                                        // Verifica se os valores estão dentro dos limites válidos
                                        if (linha >= 0 && linha < 9 && coluna >= 0 && coluna < 9 ) {
                                            tabuleiro.setValorRemove(linha, coluna, 0);                                       // *ERRO*  para 0, mas nao deixa, por que tem que estar na lista de valores possiveis
        //*ERRO*nao atualiza a lista de valores ao arredor
                                            System.out.println(tabuleiro.getElemento(linha,coluna).toString());         //teste para ver se e mutavel ou nao

                                        } else {
                                            System.out.println("Valores fora dos limites permitidos. Insira valores de 1 a 9 para linha, coluna.");
                                        }
                                    }
                                } else {
                                    System.out.println("Formato inválido. Tente novamente.");
                                }
                                tabuleiro.imprimirTabuleiro();
                                break;

                            case "3":
                                String jogadaDica = scanner.next();
                                if (jogadaDica.matches("^(\\(\\d,\\d\\))+")) {
                                    // Separa as múltiplas entradas
                                    String[] entradas = jogadaDica.split("\\)\\(");

                                    for (String item : entradas) {
                                        item = item.replace("(", "").replace(")", ""); // Remove os parênteses
                                        String[] valores = item.split(",");

                                        int linha = Integer.parseInt(valores[0]); // Ajusta para 0-indexado
                                        int coluna = Integer.parseInt(valores[1]); // Ajusta para 0-indexado


                                        // Verifica se os valores estão dentro dos limites válidos
                                        if (linha >= 0 && linha < 9 && coluna >= 0 && coluna < 9 ) {
                                            Elemento elemento = tabuleiro.getElemento(linha, coluna);

                                            System.out.println("Valores possíveis: " + elemento.getPossibleValues());

                                        }

                                        else {
                                            System.out.println("Valores fora dos limites permitidos. Insira valores de 1 a 9 para linha, coluna.");
                                        }
                                    }
                                } else {
                                    System.out.println("Formato inválido. Tente novamente.");
                                }
                                break;

                            case "4":
                                key_jogadas = false;
                                break;

                            default:
                                System.out.println("Entrada inválida, tente novamente.");

                        }
                }




        }
    }
}
