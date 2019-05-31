package asclepius.components.Engine.classes.Doctor;
import asclepius.components.Engine.interfaces.Doctor.IDoctor;
import asclepius.components.Engine.interfaces.all.*;

public class Doctor implements IDoctor {
    private int patientN = 0;
    private String[] answrs;
    private ITableProducer producer;
    private IResponder responder;
    private String[] diagnostic;

    public void connect(ITableProducer producer) {
        this.producer = producer;
    }

    public void connect(IResponder responder) {
        this.responder = responder;
    }

    public void verifyDiagnostic(){
        int flag1 = 0;
        int size = 0;

        String inst[][] = producer.requestInstances();

        for(int i = 0; i < inst.length; i++){
            flag1 = 0;
            for(int j = 0; j < 7; j++){
                if(!inst[i][j].equals(answrs[j])){
                    flag1 = 1;
                }
            }
            if(flag1 == 0){
                diagnostic[size] = inst[i][7];
                size++;
            }
        }
        if(size == 0){
            diagnostic[size] = "not found";
        }
    }

    public void startInterview() {
        String resp;
        String attributes[] = producer.requestAttributes();
        String instances[][] = producer.requestInstances();
        answrs = new String[attributes.length-1];
        diagnostic = new String[attributes.length -1];

        for (int a = 0; a < attributes.length - 1; a++){
            answrs[a] = responder.ask(attributes[a]);
            System.out.println("Question: " + attributes[a] + " - " + responder.ask(attributes[a]));
        }

        this.verifyDiagnostic();
        for(int i = 0; diagnostic[i] != null; i++){
            System.out.println("Disease guess: " + diagnostic[i] );
            boolean result = responder.finalAnswer(diagnostic[i] );
            System.out.println("Result: " + ((result) ? "I am right =)" : "I am wrong =("));
        }
    }

}
