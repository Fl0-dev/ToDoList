package toDO.dal;

import toDO.dal.jdbc.TodoDAO;
import toDO.dal.jdbc.TodoDAOJdbcImpl;

/**
 * Classe DAOFactory
 * permet de choisir selon le contexte l'instance à la DAO
 * Ici on a que SQLite
 *
 */
public class DAOFactory {
    /**
     * permet de créer une instance à partir
     * de TodoDAODAOJdbc
     * @return instance d'TodoDAO
     */
    public static TodoDAO getTodoDAO(){
        TodoDAO todoDAO = new TodoDAOJdbcImpl();
        return todoDAO;
    }
}
