package bll;

import bo.DALException;
import bo.Todo;
import dal.DAOFactory;
import dal.jdbc.TodoDAO;

import java.util.List;

/**
 * Classe de la BLL qui contient les méthodes
 * pour gérer la DAO
 */
public class TodoManager {

    //création d'une instance de TodoManager (instance de classe)
    private static TodoManager instance;
    TodoDAO todoDAO;

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
     * @throws BLLException si souci SQL
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


}
