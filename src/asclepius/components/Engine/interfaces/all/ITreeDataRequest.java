package asclepius.components.Engine.interfaces.all;

import java.util.ArrayList;
import asclepius.components.Engine.classes.TBC.TreeNode;

public interface ITreeDataRequest {
    public TreeNode getTreeHead(); //Retorna o nó cabeça da árvore.
    public ArrayList diagCheck(); //Retorna um array composto dos arrays de cada um dos nós do útimo andar da árvore (dinamicamente).
    public String[] requestAttributes(); //Retorna o vetor de sintomas.
    public String[][] requestInstances(); //Retorna a matriz de casos.
}
