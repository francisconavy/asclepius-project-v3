package asclepius.components.Hermes.interfaces;

public interface IHermes {
    //acho que não precisamos colocar os connects do telegram na interface do hermes, já que o hermes não é especifico do telegram
    void takeOut(String text);
    void takeIn(String text);
}
