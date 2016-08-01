import  java.sql.*;
import java.math.BigDecimal;

public class PedidoBD{
  private BDjdbc conexao;
  public PedidoBD(BDjdbc conexao_){
  	     this.conexao = conexao_; 
  	
  }
/**
* Metodo que grava um item no Banco de Dados
*/	
	public void AdicionarNotaBD(Nota nota_,int pedido_id)throws Exception{
	  PreparedStatement Stmt;
      Stmt = conexao.getConexao().prepareStatement(
      "INSERT INTO NOTA (NOT_NOTA,ALU_MATRICULA) values (?,?)");
//      Stmt.setString(1,item_.getDescricao());
//      Stmt.setInt(2,item_.getQuantidade());
      //Stmt.setBigDecimal(1,BigDecimal.valueOf(nota_.getValorNota())); 
      Stmt.setDouble(1,nota_.getValorNota());
      Stmt.setInt(2,pedido_id); //pedido_id == aluno
      Stmt.executeUpdate();
      Stmt.close();
      conexao.getConexao().commit();
    
	}
/**
* Metodo que grava um pedido no Banco de Dados
*/	
	public void AdicionarAlunoBD(Pedido pedido_)throws Exception{
	  PreparedStatement Stmt;
      Stmt = conexao.getConexao().prepareStatement(
      "INSERT INTO aluno (ALU_MATRICULA,ALU_NOME) VALUES (?,?)");
    //coverte de inteiro pra string pois matricula eh charset
      Stmt.setString(1,Integer.toString(pedido_.getID().intValue()));      
      Stmt.setString(2,pedido_.getDescricao());
      Stmt.executeUpdate();
      Stmt.close();
    
	}
/**
*
*/	
	
	public void RemoverAlunoBD(Integer aluno_matricula)throws Exception{
	  PreparedStatement Stmt;
	  
      Stmt = conexao.getConexao().prepareStatement(
      "DELETE FROM NOTA WHERE  ALU_MATRICULA=?");
      Stmt.setString(1,Integer.toString(aluno_matricula.intValue())); //NOTA: converte de int pra string
      Stmt.executeUpdate();
	  
      Stmt = conexao.getConexao().prepareStatement(
      "DELETE FROM ALUNO WHERE  ALU_MATRICULA=?");
      Stmt.setString(1,Integer.toString(aluno_matricula.intValue())); //NOTA: converte de int pra string
      Stmt.executeUpdate();
      Stmt.close();
      conexao.getConexao().commit();
    
	}
	
/**
*
*/
	
//public Pedido ConsultarMediaAlunoBD(Integer aluno_matricula)throws Exception{
public void ConsultarMediaAlunoBD(Integer aluno_matricula)throws Exception{
	  
   
	  PreparedStatement Stmt;
	  ResultSet  rs;
      Stmt = conexao.getConexao().prepareStatement(
      "SELECT AVG(NOT_NOTA) FROM NOTA WHERE ALU_MATRICULA =?");
      Stmt.setInt(1,aluno_matricula.intValue());
      rs = Stmt.executeQuery();
 //     if (!(rs.next())) return null;
      if (!(rs.next())) return;
      
      System.out.println("A media do aluno:" + rs.getDouble("AVG(NOT_NOTA)")); //imprime a mÃ©dia
      
      
      
//      Pedido p = new Pedido(aluno_matricula,rs.getString("AVG(NOT_NOTA)"));
//      System.out.println(p.toString());
      Stmt.close();
      rs.close();
      
//      Stmt = conexao.getConexao().prepareStatement(
//      "SELECT DESCRICAO,QUANTIDADE,VALOR FROM ITEM WHERE PEDIDO_ID=?");
//      Stmt.setInt(1,aluno_matricula.intValue());
//      rs = Stmt.executeQuery();
//      while (rs.next()){
//      	
//      		
//            p.addItem(rs.getDouble("VALOR"));
//                      
//      }
//      
//      Stmt.close();
//      rs.close();
//      
      //conexao.getConexao().commit();
//      return p;
      
	}

public void ConsultarMediaGeralBD()throws Exception{
	  
	   
	  PreparedStatement Stmt;
	  ResultSet  rs;
    Stmt = conexao.getConexao().prepareStatement(
    "SELECT AVG(NOT_NOTA) FROM NOTA");

    rs = Stmt.executeQuery();
//     if (!(rs.next())) return null;
    if (!(rs.next())) return;
    
    System.out.println( rs.getDouble("AVG(NOT_NOTA)")); //imprime a mÃ©dia
    
    
    
//    Pedido p = new Pedido(aluno_matricula,rs.getString("AVG(NOT_NOTA)"));
//    System.out.println(p.toString());
    Stmt.close();
    rs.close();
}


}
