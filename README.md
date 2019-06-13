![logo](https://i.ibb.co/bRCKZQf/logoV3.png)

# Trabalho de MC322 - Projeto Asclepius de Diagnóstico de Doenças

## Introdução

> Asclepius, na mitologia grega e na mitologia romana, é o deus da medicina e da cura. Aprendeu o poder curativo das ervas e a cirurgia, e adquiriu tão grande habilidade que podia trazer os mortos de volta à vida, pelo que Zeus o puniu, matando-o com um raio.
>-Wikipédia

O projeto consiste em uma versão otimizada de um laboratório já realizado em sala (ZombieHealth), no qual um médico deve diagnosticar a doença de um paciente com base em um acervo de dados (arquivo CSV). Porém, nesta nova edição, serão consideradas novas especificações propostas, relacionando, por exemplo, mecânicas de Interface em Java. 

<i>(Todas as novas especificações e detalhes estão disponíveis em https://drive.google.com/file/d/1l_L2ibwlqxWrOAWWGOOSs8f-C83Ywbl_/view, apenas para alunos de MC322 2019 1S, na sala do professor André Santanché)</i>

<b><i>Resultados do Brainstorm</i></b>

<b>Ideia 1: Sistema de diagnóstico por exclusão (abordagem simples/didática)</b>

Dada uma lista com todos os casos do acervo de dados do trabalho, para cada pergunta feita pelo médico, o código deve <b>excluir</b> os casos nessa lista que já não combinem com a resposta do paciente. Se durante as perguntas o tamanho da lista se tornar 1, isso significa que apenas 1 retorno diagnóstico é possível, e então, as perguntas devem parar e o retorno deve ocorrer logo em seguida. Se as perguntas chegarem ao fim, mesmo com o tamanho da lista <b>maior que 1</b>, o programa deve retornar todas os diagnósticos ainda presentes na lista, sem repetições.

<b>Ideia 2: Sistema de diagnóstico usando Machine Learning (abordagem realista/experimental)</b>

A ideia 2 consiste em transferir os dados do arquivo .CSV para um arquivo .ARFFF, para que o código, com o auxílio da biblioteca <b>Weka</b>, faça as análises de padrões com base no acervo de dados, e retorne, para uma lista e na ordem em que aparecem no arquivo .CSV, as probabilidades de cada doença possível no caso do paciente em questão. Dessa lista, o(s) diagnóstico(s) da(s) doença(s) mais provável(veis) deve(m) ser retornado(s). <i>(Retornar um número N de diagnósticos de doenças com X% ou mais de probabilidade, com <b>N</b> e <b>X</b> a serem decididos depois)</i>

<b>Ideia 3: Sistema de diagnóstico usando aproximação por árvore (ideia escolhida)</b>

A ideia 3 consiste em gerar uma árvore construída a partir dos dados fornecidos no arquivo .CSV, com cada andar da árvore funcionando como uma pergunta, formando um caminho de <i>trues</i> e <i>falses</i> até um vetor de diagnósticos possíveis para o caso em questão. O médico seguirá esse caminho de acordo com o caso do paciente que estiver diagnosticando, assim alcançando o vetor de diagnósticos possíveis e retornando-os como resultados.

## Componente: `Tree Builder Component`

Campo | Valor
----- | -----
Classe | `src.asclepius.TBC.TreeBuilderComponent.java`
Autores | `SatouYuri`
Objetivo | `Construir uma árvore de dados a partir do arquivo CSV fornecido.`
Interface | `ITree <br> ITreeDataRequest`

Campo | Valor
----- | -----
Classe | `src.asclepius.TBC.TreeNode.java`
Autores | `SatouYuri`
Objetivo | `Instanciar os nós da árvore de busca.`
Interface | `none`

## Detalhamento das Interfaces

### Interface `ITree`
O objetivo da interface em si é unificar as interfaces do Tree Builder Component: ITreeDataRequest e ITreeTestTools.

### Interface `ITreeDataRequest`
Os métodos dessa interface tem como objetivo retornar informações sobre a árvore.

Método | Objetivo
-------| --------
`getTreeHead` | `Retorna o nó cabeça da árvore de busca.`
`diaCheck` | `Retorna um array composto dos arrays de cada um dos nós do último andar da árvore.`
`requestAttributes` | `Retorna o vetor de sintomas.`
`requestInstances` | `Retorna a matriz de casos.`

### Interface `ITreeTestTools`
Os métodos dessa inteface tem como obejtivo testar o pleno funcionamento da árvore.

Método | Objetivo
-------| --------
`treePrint` | `Imprime a árvore. Funcionalidade para testes.`

## Componente: ClownCare

Campo | Valor
----- | -----
Classe | `asclepius.components.ClownCare.classes.ClownCare`
Autores | `Francisco Vicente`
Objetivo | `Banco de dados de piadas, que tem como objetivo deixar a consulta mais descontraida`
Interface | `IClownCare`

### Interface `IClownCare`

Método | Objetivo
-------|---------
`addJoke` | Adiciona uma piada no txt
`randJoke` | Dependendo da porcentagem setada, retorna uma das piadas que está no txt

## Componente: Hermes

Campo | Valor
----- | -----
Classe | `asclepius.components.Hermes.classes.Hermes`
Autores | `Francisco Vicente & Hitalo Cesar`
Objetivo | `Fazer a comunicação entre os pacientes e um mensageiro (como o Telegram)`
Interface | `IHermes`

### Interface `IHermes`

Método | Objetivo
-------|---------
`disconnect` | Desconectar pacientes que não estão mais em consulta
`connect` | Conectar novos pacientes que entrarão em consulta
`takeOut` | Recebe como parâmetro uma string e um id, e deve levar uma mensagem para o paciente com o id
`takeIn` | Recebe como parâmetro uma string e um id, e deve levar uma mensagem para o mensageiro.
`takeIn` | Recebe como parâmetro uma string, um vetor de strings e um id. Deve levar uma mensagem para o mensageiro, além de setar quais serão os botões que o mensageiro deve criar.
`takeInAt`| Recebe como parâmetro uma string e um id. Cria um atestado e envia para o mensageiro.

## Componente: Telegram

Campo | Valor
----- | -----
Classe | `asclepius.components.TLG.classes.TLG & asclepius.components.TLG.classes.StartTLG`
Autores | `Francisco Vicente & Hitalo Cesar`
Objetivo | `Fazer a comunicação entre usuários e o hermes`
Interface | `asclepius.components.TLG.interfaces.ITLG & asclepius.components.TLG.interfaces.IStartTLG`

### Interface `ITLG`

Método | Objetivo
-------|---------
`connect` | Recebe um hermes e o conecta com o Telegram.
`getName` | Retorna o nome do ultimo paciente que mandou uma mensagem.
`getChatID` | Retorna o ID do ultimo paciente que mandou mensagem.
`sendText` | Recebe como parametro uma string e um ID e manda uma mensagem pro usuário certo.
`sendText` | Recebe como parâmetro uma string, um vetor de Strings e um ID, e manda uma mensagem pro usuário certo, além de setar o teclado com os botões.
`sendImage` | Recebe como parametro o path de uma imagem e o ID do usuario, e envia a imagem para o devido usuário
`sendDocuments` | Recebe como parametro o path de um documento, uma string que é o nome do arquivo e o ID do usuário, para enviar o documento para o usuário correto

### Interface `IStartTLG`

Método | Objetivo
-------|---------
`start` | Inicia o bot
