![logo](https://i.ibb.co/bRCKZQf/logoV3.png)

<b>Trabalho de MC322 - Projeto de Diagnóstico de Doenças</b>

<b><i>Introdução</i></b>

O projeto consiste em uma versão otimizada de um laboratório já realizado em sala (ZombieHealth), no qual um médico deve diagnosticar a doença de um paciente com base em um acervo de dados (arquivo CSV). Porém, nesta nova edição, serão consideradas novas especificações propostas, relacionando, por exemplo, mecânicas de Interface em Java. 

<i>(Todas as novas especificações e detalhes estão disponíveis em https://drive.google.com/file/d/1l_L2ibwlqxWrOAWWGOOSs8f-C83Ywbl_/view, apenas para alunos de MC322 2019 1S, na sala do professor André Santanché)</i>

(Lembrete: Usar UML para representar as classes durante o desenvolvimento)

<b><i>Resultados do Brainstorm</i></b>

<b>Ideia 1: Sistema de diagnóstico por exclusão (abordagem simples/didática)</b>

Dada uma lista com todos os casos do acervo de dados do trabalho, para cada pergunta feita pelo médico, o código deve <b>excluir</b> os casos nessa lista que já não combinem com a resposta do paciente. Se durante as perguntas o tamanho da lista se tornar 1, isso significa que apenas 1 retorno diagnóstico é possível, e então, as perguntas devem parar e o retorno deve ocorrer logo em seguida. Se as perguntas chegarem ao fim, mesmo com o tamanho da lista <b>maior que 1</b>, o programa deve retornar todas os diagnósticos ainda presentes na lista, sem repetições.

<b>Ideia 2: Sistema de diagnóstico usando Machine Learning (abordagem realista/experimental)</b>

A ideia 2 consiste em transferir os dados do arquivo .CSV para um arquivo .ARFFF, para que o código, com o auxílio da biblioteca <b>Weka</b>, faça as análises de padrões com base no acervo de dados, e retorne, para uma lista e na ordem em que aparecem no arquivo .CSV, as probabilidades de cada doença possível no caso do paciente em questão. Dessa lista, o(s) diagnóstico(s) da(s) doença(s) mais provável(veis) deve(m) ser retornado(s). <i>(Retornar um número N de diagnósticos de doenças com X% ou mais de probabilidade, com <b>N</b> e <b>X</b> a serem decididos depois)</i>

<b>Ideia 3: Sistema de diagnóstico usando aproximação por árvore (ideia escolhida)</b>

A ideia 3 consiste em gerar uma árvore construída a partir dos dados fornecidos no arquivo .CSV, com cada andar da árvore funcionando como uma pergunta, formando um caminho de <i>trues</i> e <i>falses</i> até um vetor de diagnósticos possíveis para o caso em questão. O médico seguirá esse caminho de acordo com o caso do paciente que estiver diagnosticando, assim alcançando o vetor de diagnósticos possíveis e retornando-os como resultados.
