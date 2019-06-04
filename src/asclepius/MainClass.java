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
import asclepius.components.Engine.classes.DoctorPlus.DoctorPlus;
import asclepius.components.Engine.classes.PatientPlus.PatientPlus;
import asclepius.components.Engine.classes.TBC.DiagMatrix;
import asclepius.components.Engine.interfaces.DoctorPlus.IDoctorPlus;
import asclepius.components.Engine.interfaces.PatientPlus.IPatientPlus;

public class MainClass{
    public static void main(String[] args) {

        //Iniciando a interface do telegram
        StartTLG telegramStarter = new StartTLG();
        ITLG telegrambot = telegramStarter.start();

        //Instanciando o hermes
        Hermes hermes = new Hermes();

        //conecta telegram ao hermes
        telegrambot.connect(hermes);
        //conecta hermes ao telegram
        hermes.connect(telegrambot);

        // instanciando o componente DataSet
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource("resources/data/zombie-health-spreadsheet-ml-training.csv");

        // instanciando o componente paciente
        IPatient aPatient = new Patient();

        //conecta hermes ao paciente;
        hermes.connect(aPatient);

        // conectando-o no componente DataSet
        aPatient.connect(dataset);

        //conecta o paciente ao hermes
        aPatient.connect(hermes);

        // instanciando o componente doutor louco
        IDoctor cDoctor = new Doctor();

        // conectando-o ao componente DataSet
        cDoctor.connect(dataset);

        // conectando-o ao componente paciente
        cDoctor.connect(aPatient);

        //conectando o paciente ao doutor
        aPatient.connect(cDoctor);
//
//        IPatientPlus P1 = new PatientPlus();
//        IDoctorPlus D1 = new DoctorPlus(P1);
//        D1.startInterview();
    }
}