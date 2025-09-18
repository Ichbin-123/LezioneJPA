import Util.HibernateUtil;
import entity.Studente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

    private static final SessionFactory factory = HibernateUtil.buildSessionFactory();
    public static void main(String[] args) {

        Long idStudente = createStudent();
        getStudentById(idStudente);
    }

    private static void getStudentById(Long idStudente) {
        try(Session session = factory.openSession()){
            if(idStudente != null){
                Studente studente = session.get(Studente.class, idStudente);
                if(studente!= null)
                    System.out.println("> Studente recuperato con id: " + idStudente + " = " + studente) ;
                else
                    System.out.println("> Studente non trovato con id: " + idStudente) ;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Esegue una insert sul db creando un nuovo studente
    public static Long createStudent(){
        Transaction transaction = null; // Una transazione DB crea un contesto di query

        Studente nuovoStudente = new Studente("Mario", "Rossi", "MAT001");
        try(Session session = factory.openSession()){ // una sessione di interrogazione al db
            transaction = session.beginTransaction(); // inizia il contesto transazionale

            session.persist(nuovoStudente); // esegue insert nuovo studente su db


            transaction.commit(); // commit il contesto transazionale eseguendo tutte le query
            return  nuovoStudente.getId();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
