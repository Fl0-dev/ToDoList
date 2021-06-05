package toDO.ihm;

import toDO.bll.BLLException;
import toDO.bll.TodoManager;
import toDO.bo.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Création de l'affichage
 */
public class GUI extends JFrame { //TODO

    //création des attributs
    private List<Todo> listDesTodo;

    //création des éléments graphiques
    private JPanel panneauPrincipal;
    private JLabel labelDate;
    private JTextField todoTexte;
    private JButton btnAjouter;
    private JLabel labelAffichage;
    private JPanel panneauTodos;

    /////////////////////////Singleton Composants//////////////////////


    public JLabel getLabelDate() {
        if (labelDate== null) {
            labelDate = new JLabel("Nous sommes le : "+ LocalDate.now());
        }
        return labelDate;
    }
    public JTextField getTodoTexte(){
        if (todoTexte== null) {
            todoTexte = new JTextField(50);
        }
        return todoTexte;
    }
    public JButton getBtnAjouter(){
        if (btnAjouter == null){
            btnAjouter = new JButton("Ajouter");

            //Action du bouton Ajouter
            btnAjouter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Appel du manager
                    TodoManager tm = TodoManager.getInstance();
                    try {
                        tm.ajouterTodo(getTodoTexte().getText());
                        panneauTodos.add(new JLabel(getTodoTexte().getText()));
                    } catch (BLLException bllException) {
                        getLabelAffichage().setText("Souci lors de l'ajout. Ajout Annulé !");
                        getLabelAffichage().setForeground(new Color (0xDE1717));
                    }

                }
            });
        }
        return btnAjouter;
    }
    public JLabel getLabelAffichage(){
        if (labelAffichage== null) {
            labelAffichage = new JLabel("");
        }
        return labelAffichage;
    }

    private JPanel getPanneauPrincipal(){
        //si pas de panneau
        if (panneauPrincipal == null) {
            // création du panneau principal
            panneauPrincipal = new JPanel();
            //mise en place de la grille de placement
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10,0,5,0);
            panneauPrincipal.add(getLabelDate(),gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.insets = new Insets(5,0,5,0);
            panneauPrincipal.add(getTodoTexte(),gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.insets = new Insets(5,0,5,0);
            panneauPrincipal.add(getBtnAjouter(),gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.insets = new Insets(5,0,5,0);
            panneauPrincipal.add(getLabelAffichage(),gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.insets = new Insets(5,0,5,0);
            panneauPrincipal.add(getPanneauTodos(),gbc);
        }
        return panneauPrincipal;
    }

    private JPanel getPanneauTodos(){
        //si pas de panneau
        if (panneauTodos == null) {
            // création du panneauCouleur
            panneauTodos = new JPanel();
            //mise en place de la grille de placement
            panneauTodos.setLayout(new BoxLayout(panneauTodos, BoxLayout.Y_AXIS));
        }
        return panneauTodos;
    }

    /**
     * Méthode pour l'affichage complet
     */
    public GUI(){
        //donne un titre à la fenêtre
        this.setTitle("Ma ToDo Liste");
        //Donne une taille à la fenêtre
        this.setSize(600, 300);
        //permet de centrer la fenêtre si null
        this.setLocationRelativeTo(null);
        //permet de fermer le programme quand on ferme la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //évite que la fenêtre soit modifiable
        this.setResizable(false);
        //taille la fenêtre selon les éléments
        //this.pack();
        // mise en place du panneau principale
        this.setContentPane(getPanneauPrincipal());
        //Appel de TodoManager
        TodoManager tm = TodoManager.getInstance();
        //Création d'une liste de tous les todo
        listDesTodo = new ArrayList<>();
        try {
            listDesTodo = tm.todos();
        } catch (BLLException e) {
            e.printStackTrace();
        }
        //affichage des todos déjà enregistrés dans la DB
        if(!listDesTodo.isEmpty()){
            getLabelAffichage().setText("TodoList à jour");
            for (Todo todo: listDesTodo) {
                //création d'un label pour chaque todo
                panneauTodos.add(new JLabel(todo.getTexte()));
            }
        //si aucun todo dans la DB
        }else{
            getLabelAffichage().setText("Aucun Todo dans la DB");
            //couleur orange
            getLabelAffichage().setForeground(new Color(0xFFE76D10, true));
        }

        //permet l'affichage de l'écran
        this.setVisible(true);
    }

    /**
     * Permet de lancer l'affichage créé
     */
    public static void main(String[] args) {
        //création d'un écran principal
        SwingUtilities.invokeLater(
                //on pourrait faire une lambda (peu importe le nom lance)
                new Runnable() {
                    @Override
                    public void run() {
                        JFrame app =new GUI();
                    }
                }
        );
    }
}
