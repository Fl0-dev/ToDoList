package toDO.dal.jdbc;

import toDO.bo.Todo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de la DAL qui gère la relation avec la DB Todo_table
 * @author Florian
 * @date 06/06/2021
 */
public class TodoDAOJdbcImpl implements TodoDAO{

    /**
     * Permet de créer une liste de tout les todo en DB
     * @return une liste de todo
     * @throws DALException si souci
     */
    @Override
    public List<Todo> selectAll()throws DALException{
        //déclaration d'une liste
        List<Todo> listTodo = new ArrayList<>();

        //Ouverture de la connection vers DB
        try (Connection connection = JdbcTools.recupConnection();
             Statement requete = connection.createStatement()) {

            //requête SQL pour selectionner tous les éléments de la DB
            String sql = "SELECT * FROM todo_table";
            ResultSet rs = requete.executeQuery(sql);
            //déclaration d'une instance de Todo
            Todo todo;
            //tant qu'il y a un row
            while(rs.next()){
                //récupération dans des variables des éléments de la ligne
                int id = rs.getInt("id");
                //LocaDate Date.valuesof(todo.date)
                LocalDate date = rs.getDate("dateDuJour").toLocalDate();
                String texte = rs.getString("texte");
                //création de l'instance Todo
                todo = new Todo(id,date,texte);
                //ajout de l'instance dans la liste
                listTodo.add(todo);
            }
        }catch (SQLException e){
            throw new DALException("Souci lors de la selection");
        }
        //retourne la liste de tous les todo
        return listTodo;
    }

    /**
     * Permet d'ajouter un todo dans la DB
     * @param todo à ajouter
     * @throws DALException si souci
     */
    @Override
    public void insert(Todo todo) throws DALException{
        //Constante pour la requête SQL
        String SQLINSERT = "INSERT INTO todo_table (dateDuJour, texte)VALUES (?,?);";
        //date du jour
        LocalDate dateJour = LocalDate.now();
        //Ouverture de la connexion vers la DB
        try (Connection connection = JdbcTools.recupConnection();
             PreparedStatement requete = connection.prepareStatement(SQLINSERT)){

            //Initialisation de la requête SQLINSERT
            requete.setDate(1,Date.valueOf(dateJour));
            requete.setString(2,todo.getTexte());
            //exécution
            requete.executeUpdate();
            //récupération de l'Id auto incrémenté
            ResultSet rs = requete.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                //changement de l'Id
                todo.setId(id);
            }


        } catch (SQLException e) {
            throw new DALException("Souci avec la méthode insert");
        }

    }
}
