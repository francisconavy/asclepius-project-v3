package asclepius.components.GraficosComplemento;

//-------------------------------------------------------------------------------------------------//
class Doencas{
    private String nome;
    private int frequencia;

    public Doencas(String nomeNovo){
        this.nome = nomeNovo;
        this.frequencia = 1;
    }

    public void aumentaFrequencia(){
        this.frequencia++;
    }

    public int getFrequencia(){
        return this.frequencia;
    }

    public String getNome(){
        return this.nome;
    }
}
