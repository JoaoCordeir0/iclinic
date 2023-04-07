package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Cordeiro
 */
public class SystemModel {
    
    // Pega a data atual do sistema e formata em dia/mês
    public static String getDateTime() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM");
        
        return dtf.format(LocalDateTime.now());
    }
    
    // Função que formata as datas
    public static String formatDate(String data) throws ParseException
    {   
        // Seta antigo formato de data
        SimpleDateFormat in= new SimpleDateFormat("dd/MM/yyyy");
        
        // Seta novo formato de data
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
                    
        // Formata a data no novo jeito, dando um parse no antigo
        String newData = out.format(in.parse(data));
        
        return newData;
    }  
}
