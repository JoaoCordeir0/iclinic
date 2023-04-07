package Controllers;
import Models.TurnosModel;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Cordeiro
 */
public class TurnosController {
    
    // Função responsável por chamar o model que irá fazer á inserção no banco de dados
    public static void cadastraTurno(String data, String nome, String tipoTurno) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException
    {                        
        TurnosModel insercao = new TurnosModel(nome, SystemController.formatDate(data), tipoTurno);
        
        insercao.insertTurno();
    }
    
}
