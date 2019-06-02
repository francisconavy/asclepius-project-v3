package asclepius.components.Engine.interfaces.all;

public interface IResponder {
    public void ask(String question);
    public boolean finalAnswer(String answer);

    int getActualSym();

    void takeNote(String answer);

    String[] getPatInst();

    void tellDisease(String disease);
}
