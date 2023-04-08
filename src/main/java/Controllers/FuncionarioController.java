package Controllers;
import Models.FuncionarioModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
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
    
    public static void listarFuncionarios(JComboBox comboBox) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        // Resultset usado para listar as informações na tabela
        ResultSet rsValue = FuncionarioModel.getFuncionario();                
               
        while(rsValue.next())
        {
            comboBox.addItem(rsValue.getInt("idFuncionario")+ "- " +rsValue.getString("nomeFuncionario"));
        }
    }
    
}
