package asclepius.components.Hermes.classes;

import asclepius.components.Hermes.interfaces.IHermes;

public class Hermes implements IHermes {

    private String fromOut;
    private String fromIn;


    public void takeOut(String text){
        fromOut = text;
    }

    public void takeIn(String text){
        fromIn = text;
    }

    public String sendOut(){
        return fromOut;
    }


    public String sendIn(){
        return fromIn;
    }
}
