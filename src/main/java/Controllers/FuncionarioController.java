package Controllers;
import Models.FuncionarioModel;

import java.io.IOException;
import java.sql.SQLException;
/**
 *
 * @author Lucas Gabryel
 */
public class FuncionarioController {
     
    public static void CadastrarFuncionario(String nome, String cargo, String codigo) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        FuncionarioModel insere = new FuncionarioModel(nome, cargo, codigo);
        
        insere.cadastraFuncionario();
    }
    
}
