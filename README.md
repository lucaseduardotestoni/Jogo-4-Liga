# Jogo-4-Liga

O jogo "Liga de 4" foi desenvolvido como parte do trabalho de conclusão do primeiro semestre na FURB. A proposta do jogo é desafiar os jogadores a resolverem um desafio estratégico. 

Ao iniciar uma partida, o jogador pode escolher sua cor e, em seguida, selecionar a posição em que deseja fazer sua jogada. O sistema verifica se a posição selecionada está disponível e reconhece se está ocupada ou livre.

 proporciona uma experiência interativa em que os jogadores enfrentam um desafio estratégico, jogando contra o computador. O sistema utiliza o objeto "math.random" para determinar as jogadas do computador, permitindo uma jogabilidade variada. foi incluída a opção de reiniciar o jogo, permitindo ao jogador jogar novamente.

## Requisitos Funcionais

- o único import que deve ser usado é java.util.Scanner;
- o construtor declara a única ocorrência do objeto teclado da classe Scanner para permitir ler dados do console usando o teclado;
- o método main só instância o construtor desta classe;
- o construtor declara a matriz do tipo char que representa o *tabuleiro
- a matriz tabuleiro tem o tamanho 6 por 7;
- o jogo será implementado para ser jogado entre um jogador e o computador;
- devem ser implementados métodos que por sua vez serão chamados no construtor. Esses métodos serão a sua escolha, bem como os tipos de retorno ou parâmetros;
- não poderá ser declarados atributos de classe.
 
RF01 - o tabuleiro deve começar com todas as casas em branco representando pelo valor "B" **(0,5)** <br />
RF02 - o jogador deve escolher a sua cor entre V (vermelho) ou A (azul)  **(0,5)** <br />
RF03 - na sua vez, o jogador deve informar uma posição de coluna para jogar. A peça ficará posicionada na primeira linha disponível daquela coluna (considerar a linha mais abaixo possível) **(1,0)** <br />
RF04 - após o jogador executar sua jogada, o computador deve executar sua jogada de forma aleatória (utilizar o método Math.random para sortear a coluna) **(1,0)** <br />
RF05 - deve ser possível imprimir o tabuleiro a qualquer momento **(1,0)** <br />
RF06 - quando o jogador ou o computador ganhar, deve-se imprimir quem foi o vencedor (considerar vitória para 4 cores posicionadas linearmente na horizontal, vertical ou diagonal). **(4,0 -- 1,0 para cada posição - horizontal, vertical, diagonal para direita, diagonal para esquerda)** -  <br /> 
RF07 - se o jogador ou o computador informar uma coluna que está com todas as linhas preenchidas, deve-se solicitar novamente que faça sua jogada. **(0,5)** <br />
RF08 - se o tabuleiro estiver completo e não houver vitória deve-se imprimir EMPATE **(0,5)** <br />
RF09 - quando um jogador vence ou há empate, deve-se perguntar se deseja jogar novamente e, caso afirmativo, deve-se reiniciar a partida. **(1,0)** <br />

