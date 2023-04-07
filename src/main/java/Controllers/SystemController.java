package Controllers;
import Models.SystemModel;
import java.text.ParseException;
import java.util.Date;

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
    
    // Formata a data que será inserida no banco de dados
    public static String formatDate(String data) throws ParseException
    {
        return SystemModel.formatDate(data);
    }
}
