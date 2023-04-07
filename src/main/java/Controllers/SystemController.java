package Controllers;
import Models.SystemModel;

/**
 *
 * @author Cordeiro
 */
public class SystemController {
    
    // Retorna o dia e mês atual da model 
    public static String showDate()
    {
        return SystemModel.getDateTime();
    }
}
