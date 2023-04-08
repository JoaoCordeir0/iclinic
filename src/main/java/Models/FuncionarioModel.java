package Models;

import Core.Database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas Gabryel
 */
public class FuncionarioModel {
    
    String cod;
    String nome;
    String cargo;
    
    public FuncionarioModel(String nome, String cargo, String codigo)
    {
        this.nome = nome;
        this.cargo = cargo;
        this.cod = codigo;
    }
    
      public boolean cadastraFuncionario() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "INSERT INTO funcionario(codFuncionario, nomeFuncionario, nomeCargo) VALUES (?,?,?)";
        
        // Prepara a Sitaxe basica de sql a ser executada
        PreparedStatement comandoSQL = conn.prepareStatement(sql);
        
        //Injeta os Valores que devem compor o SQL
        comandoSQL.setString(1, this.cod);
        comandoSQL.setString(2, this.nome);
        comandoSQL.setString(3, this.cargo);
        
        //Executa o comando do Mysql
        comandoSQL.executeUpdate();
        
        //Fecha os recursos
        comandoSQL.close();
        conn.close();
        
        return true;
    }
      
    // Função responsável por retornar a listagem de funcionario inseridos no banco de dados
    public static ResultSet getFuncionario () throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Connection conn = Database.createConexao();
        
        String sql = "SELECT idFuncionario, nomeFuncionario FROM funcionario;";
        
        ResultSet rs = Database.execSelect(conn, sql);
        
        return rs;
    }
    
}
