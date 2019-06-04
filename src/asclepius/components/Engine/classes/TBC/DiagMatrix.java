package asclepius.components.Engine.classes.TBC;

import java.util.ArrayList;

public class DiagMatrix {
    public static int verify(ArrayList<ArrayList<String>> diag){ //Esse método estático verifica se uma das condições de parada já foi alcançada.
        int emptyCounter = 0;

        for(int x = 0; x < diag.size(); x++) { //Conta quantos arrays vazios há em diag...
            if (diag.get(x).isEmpty() == true) {
                emptyCounter++;
            }
        }

        if(emptyCounter == (diag.size() - 1)){ //Se há apenas 1 array não-vazio...
            return 0;
        }else if((diag.size() - emptyCounter) > 1){ //Se há 2 ou mais arrays não-vazios...
            return 1;
        }else if(emptyCounter == diag.size()) { //Se todos os arrays são vazios...
            return 2;
        }

        return 3;
    }
}
