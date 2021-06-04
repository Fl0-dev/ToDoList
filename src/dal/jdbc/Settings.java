package dal.jdbc;

import java.util.Properties;

/**
 * Classe permettant le chargement de fichier
 * texte.properties
 * et contenant la méthode qui exporte du fichier
 * les données sensibles
 */
public class Settings {
    private static Properties propriete;

    static{
        try{
            propriete = new Properties();
            // pour charger le fichier
            propriete.load(Settings.class.getResourceAsStream("settings.properties"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //retourne la String noté en paramètre du fichier
    public static String getPropriete(String cle){
        String parametre = propriete.getProperty(cle,null);
        return parametre;
    }
}
