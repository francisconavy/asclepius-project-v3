package asclepius.components.GraficosComplemento;

//Essa interface define os metodos que geram a tabela de atributos e instancias do Data Set
interface ITableProducer {
    String[] requestAttributes();
    String[][] requestInstances();
}
