package asclepius;

import asclepius.components.Engine.classes.DSC.DataSetComponent;
import asclepius.components.Engine.classes.Doctor.Doctor;
import asclepius.components.Engine.classes.Patient.Patient;
import asclepius.components.Engine.interfaces.DSC.IDataSet;
import asclepius.components.Engine.interfaces.Doctor.IDoctor;
import asclepius.components.Engine.interfaces.Patient.IPatient;
import asclepius.components.TLG.classes.StartTLG;
import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.Hermes.interfaces.IHermes;
import asclepius.components.TLG.interfaces.ITLG;

public class MainClass{


    public static void main(String[] args) {

        Hermes postman = new Hermes();

        //Iniciando a interface do telegram
        StartTLG telegramStarter = new StartTLG();

        ITLG telegrambot = telegramStarter.start();

        //conecta telegram ao hermes
        telegrambot.setHermes(postman);


        //talvez seja melhor criar um método start na classe TLG do que criar uma classe startTLG, pq se não teremos que
        //dar request toda hora de coisas do verdadeiro objeto TLG por meio da StartTLG. Esse abaixo é só um caso, mas acho que
        //varias outras coisas que a gente for fazer vão precisar disso
        //conecta hermes ao telegram
        postman.connect(telegrambot);




        // instanciando o componente DataSet
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource("resources/zombie-health-spreadsheet-ml-training.csv");

        // instanciando o componente paciente
        IPatient aPatient = new Patient();

        //conecta hermes ao paciente;
        postman.connect(aPatient);

        // conectando-o no componente DataSet
        aPatient.connect(dataset);

        // instanciando o componente doutor louco
        IDoctor cDoctor = new Doctor();

        // conectando-o ao componente DataSet
        cDoctor.connect(dataset);

        // conectando-o ao componente paciente
        cDoctor.connect(aPatient);

        // executando a entrevista
        cDoctor.startInterview();

        //testes de funções, depois poderiamos criar uma pasta examples ou testes pra colocar funções de teste pra gente executar
        /*while(true){
            System.out.println(messenger.sendOut());
        }*/

    }
}