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
    public static Date formatDate(String data) throws ParseException
    {        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date newData = formato.parse("23/11/2015");
        
        return newData;
    } 
    
}
