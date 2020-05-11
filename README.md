# ZHED - FEUP - IART - 2019/2020

## Trabalho 2 - Aprendizagem Computacional

Pretende-se neste trabalho usar aprendizagem computacional para, no nosso tema, ensinar um agente a solucionar um puzzle com base em **Aprendizagem por Reforço**.

Devem  ser  experimentados  pelo  menos  dois  algoritmos  de  Aprendizagem  por  reforço  (Q-Learning, SARSA, Proximal Policy Optimization – PPO, Soft Actor-Critic – SAC, etc) e diferentes parametrizações dos algoritmos e processo de aprendizagem. Deve ser analisado o processo de aprendizagem ao longo do tempo e construídos gráficos da recompensa obtida ao longo do tempo e de outros parâmetros do processode aprendizagem. 

### Checkpoint
------

Breve apresentação (máx. 5 slides), em formato PDF, que deve conter:

1. Especificação   do   trabalho   a   realizar   (definição   do(s)   problema(s)   de   aprendizagem computacional a resolver);

2. Trabalho relacionado com referências a trabalhos encontrados na pesquisa (artigos, páginas web e/ou código fonte);

3. Descrição das ferramentas e algoritmos a utilizar no trabalho;

4. Trabalho de implementação já realizado.

## INSTRUÇÕES DE COMPILAÇÃO/EXECUÇÃO DO PROGRAMA

Este programa foi feito com integracao do sistema Gradle de 
forma a automatizar o processo de compilação do programa.

Para compilar basta abrir o terminal na pasta do projeto e executar:
```
gradle build
```
Para correr o programa basta executar em seguida:
```
gradle run
```
Uma alternativa que elimina a barra (verde) de progresso na parte 
inferior do terminal seria executar com a seguinte opção ativada:
```
gradle run --console=plain
```

## INSTRUÇÕES DE UTILIZAÇÃO DO PROGRAMA

Depois de iniciar o programa aparece o seguinte menu inicial:

> ::::::::::::::::::::::::::::::::::  
> ::             ZHED             ::  
> ::::::::::::::::::::::::::::::::::  
>  
> 1\. Utilizador joga.  
> 2\. Computador joga 'pesquisa primeiro em largura'.  
> 3\. Computador joga 'pesquisa primeiro em profundidade'.  
> 4\. Computador joga 'aprofundamento progressivo'.  
> 5\. Computador joga 'heuristica: pesquisa gulosa'.  
> 6\. Computador joga 'heuristica: A* com fator 3'.  
> 7\. Computador joga 'heuristica: A* com fator 4'.  
> 8\. Computador joga 'heuristica: A* com fator 5'.  
> 9\. Ativar/Desativar mensagens debug.    
> 0\. Sair do jogo.  
>  
> Intruduza a sua opção:  

A partir daqui basta escrever o número da opção pretendida seguido
de 'ENTER'.

    A opção '1' permite que o utilizador jogue o nível selecionado de uma 
    forma rudimentar (ou seja, não permite voltar atrás e o jogo apenas 
    termina se vencer, por isso pense bem antes de jogar!)

    As opções '2' a '8' resolvem o nível selecionado de forma automatica
    utilizando o algoritmo selecionado.

    A opcao '9' serve para ativar informacoes relevantes para fazer 
    debug do programa. 

    
De seguida é encaminhado para o seguinte menu:

> Selecione o nivel que deseja jogar (1-7):

Aqui deve intrduzir um número entre 1 a 7 que corresponde ao nivel que
deseja resolver.

        .....................................
        :Informação sobre os níveis:        :
        :nível 1 -> ZHED nível 6 (4 Peças)  :
        :nível 2 -> ZHED nível 7 (5 Peças)  :
        :nível 3 -> ZHED nível 12 (6 Peças) :
        :nível 4 -> ZHED nível 40 (7 Peças) :
        :nível 5 -> ZHED nível 44 (9 Peças) :
        :nível 6 -> ZHED nível 77 (20 Peças):
        :nível 7 -> ZHED nível 98 (39 Peças):
        :...................................:
        

Após terminado o nível volta ao menu inicial.

## Autores

Este trabalho foi desenvolvido pelos seguintes alunos da **FEUP** no âmbito da unidade curricular **Inteligência Artificial**
* Matheus Pereira Gonçalves (201405081)
* Miguel Rodrigues Pires (201406989)
* Ricardo França Domingues Cardoso (201604686)
