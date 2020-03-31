==================================
= ZHED - FEUP - IART - 2019/2020 =
==================================

====================================================================
INSTRUCOES DE COMPILACAO/EXECUCAO DO PROGRAMA

Este programa foi feito com integracao do sistema Gradle de 
forma a automatizar o processo de compilacao do programa

Para compilar basta abrir o terminal na pasta do projeto e executar:
    >gradle build

Para correr o programa basta executar em seguida:
    >gradle run

Uma alternativa que elimina a barra (verde) de progresso na parte 
inferior do terminal seria executar com a seguinte opcao ativada:
    >gradle run --console=plain
====================================================================

====================================================================
INSTRUCOES DE UTILIZACAO DO PROGRAMA

Depois de iniciar o programa aparece o seguinte menu inicial:

>> ::::::::::::::::::::::::::::::::::
>> ::             ZHED             ::
>> ::::::::::::::::::::::::::::::::::
>>
>> 1. Utilizador joga.
>> 2. Computador joga 'pesquisa primeiro em largura'.
>> 3. Computador joga 'pesquisa primeiro em profundidade'.
>> 4. Computador joga 'aprofundamento progressivo'.
>> 5. Computador joga 'heuristica: pesquisa gulosa'.
>> 6. Computador joga 'heuristica: A* com fator 3'.
>> 7. Computador joga 'heuristica: A* com fator 4'.
>> 8. Computador joga 'heuristica: A* com fator 5'.
>> 9. Ativar/Desativar mensagens debug.
>> 0. Sair do jogo.
>>
>> Intruduza a sua opção:

A partir daqui basta escrever o numero da opcao pretendida seguido
de 'ENTER'.

    A opcao '1' permite que o utilizador jogue o nivel selecionado de uma 
    forma rudimentar (ou seja, nao permite voltar atras e o jogo apenas 
    termina se vencer, por isso pense bem antes de jogar!)

    As opcoes '2' a '8' resolvem o nivel selecionado de forma automatica
    utilizando o algoritmo selecionado.

    A opcao '9' serve para ativar informacoes relevantes para fazer 
    debug do programa. 

    
De seguida é encaminhado para o seguinte menu:

>> Selecione o nivel que deseja jogar (1-7):

Aqui deve intrduzir um número entre 1 a 7 que corresponde ao nivel que
deseja resolver.

        .....................................
        :Informacao sobre os niveis:        :
        :nivel 1 -> ZHED nivel 6 (4 Pecas)  :
        :nivel 2 -> ZHED nivel 7 (5 Pecas)  :
        :nivel 3 -> ZHED nivel 12 (6 Pecas) :
        :nivel 4 -> ZHED nivel 40 (7 Pecas) :
        :nivel 5 -> ZHED nivel 44 (9 Pecas) :
        :nivel 6 -> ZHED nivel 77 (20 Pecas):
        :nivel 7 -> ZHED nivel 98 (39 Pecas):
        :...................................:
        

Apos terminado o nivel volta ao menu inicial.
====================================================================

====================================================================
Trabalho realizado por:
Matheus Pereira Gonçalves (201405081)
Miguel Rodrigues Pires (201406989)
Ricardo França Domingues Cardoso (201604686)
====================================================================
