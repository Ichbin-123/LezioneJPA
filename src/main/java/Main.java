import Util.HibernateUtil;
import entity.Corso;
import entity.Studente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

// Test di operazioni CRUD
// CREATE READ UPDATE DELETE
public class Main {

    private static final SessionFactory factory = HibernateUtil.buildSessionFactory();

    public static void main(String[] args) {
        // Test creazione studnete
        Long idStudente = createStudent();

        // Test recupero studente creato in precedenza
        getStudentById(idStudente);

        // Test update del cognome dello studente creato
        updateStudente(idStudente, "Verdi");
        getStudentById(idStudente);

        // Test eliminazione studente
        deleteStudente(idStudente);
        // getStudentById(idStudente);

        // Test inserimento studenti e corsi
        inserisciDati();
        getStudentById(2L);
        // getStudentById(3L);
        // getStudentById(4L);
    }

    // Esegue una quer select sul db puntando per id
    // SELECT * FROM studenti WHERE id=?
    private static Studente getStudentById(Long idStudente) {
        try(Session session = factory.openSession()){
            if(idStudente != null){
                Studente s = session.get(Studente.class, idStudente);
                System.out.println(s);
                return  s;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
            return null;
        }
    }

    // Metodo che aggiorno cognome di uno studente
    public static void updateStudente(Long idStudente, String nuovoCognome){
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();
            // recuperiamo lo studente che ha id passato in ingresso
            Studente studente = session.get(Studente.class, idStudente);
            if(studente != null){
                studente.setCognome(nuovoCognome);
            } else {
                System.out.println("Utente con id " + idStudente + " non trovato");
            }
            //studente = session.get(Studente.class, idStudente);
            // System.out.println(studente);
            transaction.commit();

        } catch (Exception ex){
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
        }
    }

    // Metodo che cancella studente
    public static void deleteStudente(Long idStudente){
        Transaction transaction= null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();
            Studente studente = session.get(Studente.class, idStudente);
            if(studente != null){
                session.remove(studente);
                transaction.commit();
                System.out.println("> Utente con id " + idStudente + " cancellato");
            } else {
                System.out.println("> Impossibile eliminare studente con id: " + idStudente);
                transaction.rollback();
            }
        } catch (Exception ex){
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
        }
    }

    // Inserisce degli studenti e dei corsi
    public static  void inserisciDati(){
        Transaction transaction = null;

        try(Session session = factory.openSession()){
            transaction = session.beginTransaction();

            // Creo 3 Studenti
            Studente s1 = new Studente("Mario", "Rossi", "MAT001");
            Studente s2 = new Studente("Laura", "Gialli", "MAT002");
            Studente s3 = new Studente("Giuseppe", "Verdi", "MAT003");

            // Creo 2 Corsi
            Corso c1 = new Corso("INF001", "Analisi Matematica 1", 12 );
            Corso c2 = new Corso("INF002", "Fondamenti di Informatica", 12 );
            Corso c3 = new Corso("INF003", "Sistemi Operativi", 9 );




            // Preparo le INSERT

            session.persist(s1);
            session.persist(s2);
            session.persist(s3);
            session.persist(c1);
            session.persist(c2);
            session.persist(c3);

            transaction.commit();

            session.refresh(s1);
            session.refresh(s2);
            session.refresh(s3);
            session.refresh(c1);
            session.refresh(c2);
            session.refresh(c3);


            // Iscriviamo gli studenti al corso
            s1.addCorso(c1); // Mario Rossi si iscrive ad analisi matematica
            s1.addCorso(c2);
            s1.addCorso(c3);

            s2.addCorso(c1);
            s2.addCorso(c2);
            s1.addCorso(c3);

            s3.addCorso(c1);
            s3.addCorso(c2);
            s3.addCorso(c3);

            transaction.commit();

        } catch (Exception ex){
            if(transaction != null) transaction.rollback();
            ex.printStackTrace();
        }


    }

}
