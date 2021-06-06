package toDO.bll;

import toDO.dal.jdbc.DALException;
import toDO.bo.Todo;
import toDO.dal.DAOFactory;
import toDO.dal.jdbc.TodoDAO;

import java.util.List;

/**
 * Classe de la BLL qui contient les méthodes
 * pour gérer la DAO
 * @author Florian
 * @date 06/06/2021
 */
public class TodoManager {

    //création d'une instance de TodoManager (instance de classe)
    private static TodoManager instance;


    //constructeur Vide privé
    private TodoManager(){
    }

    //création d'une méthode getInstance
    public static TodoManager getInstance() {
        if (instance == null) {
            instance = new TodoManager();
        }
        return instance;
    }

    /**
     *récupère la liste de tous les todo
     * @return la liste des todo
     * @throws BLLException si souci SQL avec la méthode selectAll()
     */
    public List<Todo> todos() throws BLLException {
        List<Todo> list;
        try {
            TodoDAO todoDAO = DAOFactory.getTodoDAO();
            list = todoDAO.selectAll();
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
        return list;
    }

    /**
     * permet d'ajouter dans la DB un todo avec
     * @param todoTexte String donnée par l'user
     * @throws BLLException si souci SQL avec la méthode insert()
     */
    public void ajouterTodo (String todoTexte) throws BLLException{
        TodoDAO todoDAO = DAOFactory.getTodoDAO();
        Todo todo = new Todo(todoTexte);

        try {
            if (todos().size()>=5){
                throw new BLLException("Fait déjà ça et on reparle!");
            }
            todoDAO.insert(todo);
        } catch (DALException e) {
            throw new BLLException(e.getMessage());
        }
    }
}
