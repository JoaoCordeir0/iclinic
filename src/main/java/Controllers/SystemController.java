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
    public static String getDataAtual()
    {
        return SystemModel.getDateTime();
    }
    
    // Formata a data que será inserida no banco de dados
    public static String formatDateDB(String data) throws ParseException
    {
        return SystemModel.formatDateDB(data);
    }
    
    // Formata a data que será exibida para o usuário
    public static String formatDateUser(String data) throws ParseException
    {
        return SystemModel.formatDateUser(data);
    }
}
