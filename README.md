# FEUP-IART

## Projeto 1 
**Temática 1: Métodos de Pesquisa Heurística para Resolução de Jogo do Tipo Solitário**

Um jogo do tipo solitário caracteriza-se pelo tipo de tabuleiro e de peças, pelas regras de movimentação das peças (operadores/jogadas possíveis) e pelas condições de terminação do jogo com derrota (impossibilidade de resolver, número máximo de movimentos foi atingido, tempo limite foi atingido) ou vitória (solitário resolvido) e com a respetiva pontuação. Tipicamente, no caso de vitória é atribuída uma pontuação dependendo do número de movimentos, recursos gastos, bónus recolhidos e/ou tempo
despendido.

Para além de implementar um jogo do tipo solitário para um jogador humano, o programa deve ser capaz de resolver diferentes versões/quadros desse jogo, utilizando métodos de pesquisa adequados, focando na comparação entre métodos de pesquisa não informada (pesquisa primeiro em largura, primeiro em profundidade, aprofundamento progressivo, custo uniforme) e métodos de pesquisa heurística (pesquisa gulosa, A*), com diferentes funções heurísticas. Os métodos aplicados devem ser comparados a diversos níveis, com ênfase para a qualidade da solução obtida, número de operações analisadas e tempo despendido para obter a solução.

A aplicação deve ter uma visualização em modo de texto ou gráfico para mostrar a evolução do tabuleiro e realizar a comunicação com o utilizador/jogador. Deve permitir um modo de jogo em que o PC resolve o solitário sozinho utilizando o método e sua configuração selecionado pelo utilizador. Poderá também, opcionalmente, permitir um modo de jogo Humano em que o utilizador pode resolver o jogo,
eventualmente pedindo “dicas” ao PC. 


## Checkpoint

Cada grupo deve submeter no Moodle uma breve apresentação (máx. 5 slides), em formato PDF, que será
utilizada na aula para analisar, em conjunto com o docente, o andamento do trabalho. A apresentação deve
conter:

1.  Especificação do trabalho a realizar (definição do jogo ou do problema de otimização a resolver);

2. Trabalho relacionado com referências a trabalhos encontrados na pesquisa (artigos, páginas web e/ou
código fonte);

3. Formulação do problema como um problema de pesquisa (representação do estado, estado
inicial, teste objetivo, operadores (nomes, pré-condições, efeitos e custos), heurísticas/função de avaliação)
ou de otimização (representação da solução/indivíduo, funções de vizinhança/mutação e de cruzamento,
restrições rígidas, funções de avaliação);

4. Trabalho de implementação já realizado (linguagem de programação, ambiente de desenvolvimento, estruturas de dados, estrutura 
de ficheiros, entre outros);


## Entrega Final

Cada grupo deve submeter no Moodle dois ficheiros: uma apresentação (máx. 10 slides), em formato PDF, e o código implementado, devidamente comentado, incluindo um ficheiro “readme.txt” com instruções sobre como o compilar, executar e utilizar. Com base na apresentação submetida, os estudantes devem realizar uma demonstração (cerca de 10 minutos) do trabalho, na aula prática, ou em outro período a designar pelos docentes da disciplina.
O ficheiro com a apresentação final deve incluir, para além do já referido para a entrega intercalar (checkpoint), detalhes sobre:

5. A abordagem (heurísticas, funções de avaliação, operadores, …);

6. Algoritmos implementados (algoritmos de pesquisa, minimax, metaheurísticas);

7. Resultados experimentais, recorrendo a tabelas/gráficos apropriados e comparando os diversos métodos, heurísticas, algoritmos e respetivas parametrizações para diferentes cenários/problemas;

**A apresentação deve incluir um slide de conclusões e outro de referências consultadas e materiais utilizados (software, sítios web, artigos científicos, …).**


## Compilar e correr o programa

Para compilar basta executar o seguinte comando:
```
gradle build
```
Para correr o jogo existem duas opções:
```
gradle run
```
```
gradle run --console=plain
```
Sendo que esta segunda opção com ``--console=pain`` desativa a barra de progresso que aparece na parte inferior da consola.


## Autores

Este trabalho foi desenvolvido pelos seguintes alunos da **FEUP** no âmbito da unidade curricular **Inteligência Artificial**
* Matheus Pereira Gonçalves (201405081)
* Miguel Rodrigues Pires (201406989)
* Ricardo França Domingues Cardoso (201604686)