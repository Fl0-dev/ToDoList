package toDO.ihm;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Création de l'affichage
 */
public class GUI extends JFrame { //TODO

    private JPanel panneauPrincipal;
    private JLabel labelDate;
    private JTextField todoTexte;
    private JButton btnAjouter;
    private JLabel labelAffichage;
    private JLabel todo1;
    private JLabel todo2;
    private JLabel todo3;
    private JLabel todo4;
    private JLabel todo5;
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
        }
        return btnAjouter;
    }
    public JLabel getLabelAffichage(){
        if (labelAffichage== null) {
            labelAffichage = new JLabel("");
        }
        return labelAffichage;
    }
    public JLabel getTodo1(){
        if (todo1== null) {
            todo1 = new JLabel("");
        }
        return todo1;
    }
    public JLabel getTodo2(){
        if (todo2== null) {
            todo2 = new JLabel("");
        }
        return todo2;
    }
    public JLabel getTodo3(){
        if (todo3== null) {
            todo3 = new JLabel("");
        }
        return todo3;
    }
    public JLabel getTodo4(){
        if (todo4== null) {
            todo4 = new JLabel("");
        }
        return todo4;
    }
    public JLabel getTodo5(){
        if (todo5== null) {
            todo5 = new JLabel("");
        }
        return todo5;
    }
    private JPanel getPanneauPrincipal(){
        //si pas de panneau
        if (panneauPrincipal == null) {
            // création du panneau principal
            panneauPrincipal = new JPanel();
            //mise en place de la grille de placement
            panneauPrincipal.setLayout(new BoxLayout(panneauPrincipal, BoxLayout.Y_AXIS));
            panneauPrincipal.add(getLabelDate());
            panneauPrincipal.add(getTodoTexte());
            panneauPrincipal.add(getBtnAjouter());
            panneauPrincipal.add(getLabelAffichage());
            panneauPrincipal.add(getPanneauTodos());
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
            panneauTodos.add(getTodo1());
            panneauTodos.add(getTodo2());
            panneauTodos.add(getTodo3());
            panneauTodos.add(getTodo4());
            panneauTodos.add(getTodo5());
        }
        return panneauTodos;
    }

    public GUI(){
        //donne un titre à la fenêtre
        this.setTitle("Ma ToDo Liste");
        //Donne une taille à la fenêtre
        this.setSize(500, 500);
        //permet de centrer la fenêtre si null
        this.setLocationRelativeTo(null);
        //permet de fermer le programme quand on ferme la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //évite que la fenêtre soit modifiable
        //this.setResizable(false);
        //taille la fenêtre selon les éléments
        this.pack();
        // mise en place du panneau principale
        this.setContentPane(getPanneauPrincipal());
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
