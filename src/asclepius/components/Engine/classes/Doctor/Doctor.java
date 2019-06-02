package asclepius.components.Engine.classes.Doctor;
import asclepius.components.Engine.interfaces.Doctor.IDoctor;
import asclepius.components.Engine.interfaces.all.*;

public class Doctor implements IDoctor {
    private int patientN = 0;
    private String[] answer;
    private ITableProducer producer;
    private IResponder responder;
    private String[] diagnostic;
    private int num_sym;
    private String attributes[];

    public void connect(ITableProducer producer) {
        this.producer = producer;
        attributes = producer.requestAttributes();
        this.num_sym = attributes.length - 1;
    }

    public void connect(IResponder responder) {
        this.responder = responder;
    }

    public void verifyDiagnostic(String[] answer){
        int flag1 = 0;
        int size = 0;

        String inst[][] = producer.requestInstances();

        for(int i = 0; i < inst.length; i++){
            flag1 = 0;
            for(int j = 0; j < 7; j++){
                if(!inst[i][j].equals(answer[j])){
                    flag1 = 1;
                }
            }
            if(flag1 == 0){
                diagnostic[size] = inst[i][7];
                size++;
            }
        }
        if(size == 0){
            diagnostic[size] = "Doença não registrada";
        }
    }

    public void startInterview() {
        responder.ask("Vamos iniciar a consulta?");
    }

    public void getDiagnosis(){

        //String attributes[] = producer.requestAttributes();
        responder.ask(attributes[responder.getActualSym()]);
//        String resp;
//        String attributes[] = producer.requestAttributes();
//        String instances[][] = producer.requestInstances();
//        answer = new String[attributes.length - 1];
//        diagnostic = new String[attributes.length - 1];

//        for (int a = 0; a < attributes.length - 1; a++){
//            responder.ask(attributes[a]);
//            answer[a] = "t";
//            System.out.println("Question: " + attributes[a] + " - " + answer[a]);
//        }

//        this.verifyDiagnostic();
//        for(int i = 0; diagnostic[i] != null; i++){
//            System.out.println("Disease guess: " + diagnostic[i] );
//            boolean result = responder.finalAnswer(diagnostic[i] );
//            System.out.println("Result: " + ((result) ? "I am right =)" : "I am wrong =("));
//        }
    }

    public void getDiagnosis(String answer){
        responder.takeNote(answer);
        //aqui ficaria a condição de parada
        if(responder.getActualSym() < num_sym) {
            responder.ask(attributes[responder.getActualSym()]);
        }
        else{
            //hora de dar o diagnostico
            diagnostic = new String[attributes.length - 1];
            this.verifyDiagnostic(responder.getPatInst());
            for(int i = 0; diagnostic[i] != null; i++){
                responder.tellDisease(diagnostic[i]);
                System.out.println("Disease guess: " + diagnostic[i] );
            }
        }
    }

}
