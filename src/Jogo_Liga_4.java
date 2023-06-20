import java.util.Scanner;

public class Jogo_Liga_4 {
    private Jogo_Liga_4() {
        Scanner teclado = new Scanner(System.in);
        char tabuleiro[][] = new char[6][7];
        char computador;
        char jogador;
        char op;
        boolean verifica;
        do {
            jogador = ' ';
            computador = ' ';
            verifica = true;
            System.out.println("================Jogo Liga 4 Iniciado=================");
            do {
                verifica = true;
                System.out.println("jogador deve escolher a sua cor entre V (vermelho) ou A (azul)");
                jogador = teclado.next().charAt(0);
                jogador = Character.toUpperCase(jogador);
                if (jogador == 'V') {
                    computador = 'A';
                } else if (jogador == 'A') {
                    computador = 'V';
                } else {
                    System.out.println("Letra Informada Incorreta");
                    verifica = false;
                }
            } while (!verifica);
            jogar(tabuleiro, teclado, jogador, computador);

            System.out.println("Deseja Jogar Novamente? (S)Sim ou (N)Não");
            op = teclado.next().charAt(0);
            op = Character.toUpperCase(op);

        } while (op != 'N');

        teclado.close();
    }

    //Controle do Jogo
    private static void jogar(char tabuleiro[][], Scanner teclado, char jogador, char computador) {
        int jogadas = 0;
        boolean ganhou = false;

        tabuleiroLimpar(tabuleiro);

        var startPlayer = sortear(-1, 2);

        while (jogadas < 42) {
            jogadas++;
            if (startPlayer == 1 && jogadas <= 42) {
                ganhou = jogador(teclado, tabuleiro, jogador);
                startPlayer = 0;

            } else if (startPlayer == 0 && jogadas <= 42) {
                ganhou = computador(tabuleiro, computador);
                startPlayer = 1;
            }
            desenhaTabuleiro(tabuleiro, jogadas);
            if (ganhou) {
                break;
            }

        }
        if (!ganhou) {
            System.out.println("Empate!");
        }

    }

    //Controle Jogadas Jogador
    private static boolean jogador(Scanner teclado, char tabuleiro[][], char jogador) {
        int coluna;
        int linha = -1;
        boolean verifica = false;
        System.out.println("Jogador Jogando..");

        do {
            System.out.println("Insira coluna da jogada");
            coluna = teclado.nextInt();

            if (coluna > 6 && coluna < 0) {
                System.out.println("Valor Invalído, Informe Novamente");
            } else if (coluna <= 6 && coluna >= 0) {
                linha = encontrarUltimaPosicaoLivre(tabuleiro, coluna);
            }
            if (linha == -1) {
                System.out.println("Coluna Totalmente Preenchida");
            } else if (linha >= 0 || linha <= 5) {
                verifica = true;
                tabuleiro[linha][coluna] = jogador;
            }
        } while (!verifica);

        boolean ganhou = ganhou(tabuleiro);
        if (ganhou) {
            System.out.println("Jogador Venceu..");
        }
        return ganhou;
    }

    //Controle Jogadas Computador
    private static boolean computador(char tabuleiro[][], char computador) {
        boolean verifica = false;
        int linha;
        System.out.println("Computador Jogando..");
        do {
            int coluna = sortear(-1, 7);
            linha = encontrarUltimaPosicaoLivre(tabuleiro, coluna);
            if (linha != -1) {
                verifica = true;
                tabuleiro[linha][coluna] = computador;
            }

        } while (!verifica);
        boolean ganhou = ganhou(tabuleiro);
        if (ganhou) {
            System.out.println("Computador Venceu..");
        }
        return ganhou;
    }

    //Cores por Unicode
    public static String setarCores(int Ncor){
        String cor = " ";

     switch (Ncor){
         case 1://Vermelho
             return "\033[0;31m";
         case 2://Azul
             return  "\033[0;34m";
             //Padrão
         default:  return "\033[0m";
     }

    }
    //Desenha o tabuleiro
    public static void desenhaTabuleiro(char tabuleiro[][], int jogada) {
        //Barra Superior
        for (int j = 0; j < tabuleiro.length; j++) {
            System.out.print("----");
        }
        System.out.println("----. Jogada " + (jogada));
        System.out.print(" ");
        for (int j = 0; j < tabuleiro.length + 1; j++) {
            System.out.print("| " + j + " ");
        }
        System.out.println("|");
        //Cria os Elementos do Tabuleiro
        for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
            System.out.print(coluna);
            for (int linha = 0; linha < (tabuleiro.length + 1); linha++) {
                if (tabuleiro[coluna][linha] == 'A') {
                    System.out.print("| " + setarCores(2) + tabuleiro[coluna][linha] + setarCores(3)  + " ");
                } else if (tabuleiro[coluna][linha] == 'V') {
                    System.out.print("| " + setarCores(1) + tabuleiro[coluna][linha] + setarCores(3) + " ");
                } else {
                    System.out.print("| " + setarCores(3) + tabuleiro[coluna][linha] + " ");
                }

            }
            //Separa os Elementos um dos Outros
            System.out.println("|");
        }
        //Barra Inferior
        System.out.print(" ");
        for (int j = 0; j < tabuleiro.length; j++) {
            System.out.print("----");
        }
        System.out.println("-----");
    }

    //Define Valor B para todos os Campos da matriz do Tabuleiro
    public static void tabuleiroLimpar(char tabuleiro[][]) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length + 1; j++) {
                tabuleiro[i][j] = 'B';
            }
        }
    }

    /*Sorteia um número aleatório, a partir do math.random efetua um calculo a partir dos numeros definidos
    na variavel fim e inicio*/
    public static int sortear(int inicio, int fim) {
        return (int) ((Math.random() * (fim - inicio)) + inicio);
    }

    //Procura a maior linha disponível
    public static int encontrarUltimaPosicaoLivre(char[][] tabuleiro, int coluna) {
        int posicao = -1;
        for (int linha = tabuleiro.length - 1; linha >= 0; linha--) {
            if (tabuleiro[linha][coluna] == 'B') { // Verifica se a posição está livre
                if (posicao < linha) {//Verifica maior posição livre
                    posicao = linha;
                }
            }
        }
        return posicao;
    }

    //Verifica se ganhou
    private static boolean ganhou(char tabuleiro[][]) {
        // Verificar linhas
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length + 1; j++) {
                if (j == 4) {
                    break;
                } else if (tabuleiro[i][j] == tabuleiro[i][j + 1] && tabuleiro[i][j] == tabuleiro[i][j + 2] && tabuleiro[i][j] == tabuleiro[i][j + 3] && tabuleiro[i][j] != 'B') {
                    return true;
                }
            }
        }
        //Verificar Diagonais
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length + 1; j++) {
                if (j < 4 && i < 3) {
                    if (tabuleiro[i][j] == tabuleiro[i + 1][j + 1] && tabuleiro[i][j] == tabuleiro[i + 2][j + 2] && tabuleiro[i][j] == tabuleiro[i + 3][j + 3] && tabuleiro[i][j] != 'B') {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        //Verificar Diagonais Contrárias
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length + 1; j = j + 1) {
                if (j > 2 && i < 3) {
                    if (tabuleiro[i][j] == tabuleiro[i + 1][j - 1] && tabuleiro[i][j] == tabuleiro[i + 2][j - 2] && tabuleiro[i][j] == tabuleiro[i + 3][j - 3] && tabuleiro[i][j] != 'B') {
                        return true;
                    }
                }
            }
        }
        //Verificar Colunas
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length + 1; j = j + 1) {
                if (i > 2) {
                    break;
                } else {
                    if (tabuleiro[i][j] == tabuleiro[i + 1][j] && tabuleiro[i][j] == tabuleiro[i + 2][j] && tabuleiro[i][j] == tabuleiro[i + 3][j] && tabuleiro[i][j] != 'B') {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        new Jogo_Liga_4();
    }
}