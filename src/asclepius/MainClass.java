package asclepius;

import asclepius.components.Engine.classes.DoctorPlus.DoctorPlus;
import asclepius.components.Engine.classes.PatientPlus.PatientPlus;
import asclepius.components.Engine.classes.TBC.DiagMatrix;
import asclepius.components.Engine.interfaces.DoctorPlus.IDoctorPlus;
import asclepius.components.Engine.interfaces.PatientPlus.IPatientPlus;

public class MainClass{
    public static void main(String[] args) {
        IPatientPlus P1 = new PatientPlus();
        IDoctorPlus D1 = new DoctorPlus(P1);
        D1.startInterview();
    }
}