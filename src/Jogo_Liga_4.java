import java.util.Scanner;

public class Jogo_Liga_4 {
    private Jogo_Liga_4() {
        Scanner teclado = new Scanner(System.in);
        char tabuleiro[][] = new char[6][7];
        char jogador = ' ';
        char computador = ' ';
        boolean verifica;
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


    }

    private static void jogar(char tabuleiro[][], Scanner teclado, char jogador, char computador) {
        char verifica;
        int jogadas;
        boolean ganhou = false;

        do {
            jogadas = 0;
            verifica = ' ';
            tabuleiroLimpar(tabuleiro);

            var startPlayer = sortear(-1, 2);

            while (jogadas != 42) {
                jogadas++;
                if (startPlayer == 1 && jogadas < 42) {
                    ganhou = jogador(teclado, tabuleiro, jogador);
                    startPlayer = 0;

                } else if (startPlayer == 0 && jogadas < 42) {
                    ganhou = computador(tabuleiro, computador);
                    startPlayer = 1;
                }
                if (ganhou) {
                    break;
                }

                desenhaTabuleiro(tabuleiro, jogadas);
            }

            if (jogadas == 42 && ganhou == false) {
                System.out.println("Empate");
                System.out.println("Deseja Jogar Novamente? (S)Sim ou (N)Não");
                verifica = teclado.next().charAt(0);
                verifica = Character.toUpperCase(verifica);
            }
        } while (verifica != 'N');
    }

    //Controle Jogadas Jogador
    private static boolean jogador(Scanner teclado, char tabuleiro[][], char jogador) {
        int coluna;
        int linha = -1;
        boolean verifica = false;
        System.out.println("Jogador Jogando..");

        do {
            System.out.println("Insira coluna da jogada");
            coluna = teclado.nextInt();//sortear(-1,5);
            if (coluna > 6 && coluna < 0) {
                System.out.println("Valor Invalído, Informe Novamente");
            }
            if (coluna <= 6 && coluna >= 0) {
                linha = encontrarUltimaPosicaoLivre(tabuleiro,coluna);
            }
             if (linha == -1){
                System.out.println("Coluna Totalmente Preenchida");
            }
            else if(linha >= 0 || linha <= 5){
                verifica = true;
                tabuleiro[linha][coluna] = jogador;
            }
        } while (!verifica);

        boolean ganhou = ganhou(tabuleiro, jogador);
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
            int coluna = sortear(-1, 6);
            linha = encontrarUltimaPosicaoLivre(tabuleiro,coluna);
            if (linha != -1){
                verifica = true;
                tabuleiro[linha][coluna] = computador;
            }

        } while (!verifica);
        boolean ganhou = ganhou(tabuleiro, computador);
        if (ganhou) {
            System.out.println("Computador Venceu..");
        }
        return ganhou;
    }

    //Desenha o tabuleiro
    public static void desenhaTabuleiro(char tabuleiro[][], int jogada) {
        //Barra Superior
        for (int j = 0; j < tabuleiro.length; j++) {
            System.out.print("----");
        }
        System.out.println("----. Jogada " + (jogada + 1));
        System.out.print(" ");
        for (int j = 0; j < tabuleiro.length + 1; j++) {
            System.out.print("| " + j + " ");
        }
        System.out.println("|");
        //Cria os Elementos do Tabuleiro
        for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
            System.out.print(coluna);
            for (int linha = 0; linha < (tabuleiro.length + 1); linha++) {
                System.out.print("| " + tabuleiro[coluna][linha] + " ");
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
        for (int i = 0; i < 6; i++) {
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

    private static boolean ganhou(char tabuleiro[][], char letra) {
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


        return false;
    }

    private static void posicionaPeca() {

    }

    public static void main(String[] args) {
        new Jogo_Liga_4();
    }
}