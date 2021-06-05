package toDO.dal.jdbc;

import toDO.bll.BLLException;
import toDO.bo.DALException;
import toDO.bo.Todo;

import java.util.List;

/**
 * Interface permettant le transtypage
 * entre les différentes implantations
 * contient les signatures des différentes méthodes
 */
public interface TodoDAO {

    //TODO signatures des méthodes
    List<Todo> selectAll() throws BLLException,DALException;
    void insert(Todo todo) throws BLLException,DALException;
}
