package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    
}
