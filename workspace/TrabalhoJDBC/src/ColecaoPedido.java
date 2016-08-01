import java.util.*;
import java.sql.*;


public  class ColecaoPedido{
	
	private BDjdbc conexao = new BDjdbc();
	private PedidoBD pedidoBD;
	public static final int ADICIONAR = 1;
	public static final int REMOVER = 2;
	public static final int CONSULTAR = 3;
	public static final int MEDIA = 4;
	
/**
* Metodo Construtor que gera uma conexao com o Banco de Dados
*/
	public ColecaoPedido() throws Exception{
		 
    /* conexao.setLogin("sa");
     conexao.setSenha("123");
     String url = DriverUtilities.makeURL("localhost:1433", "sae", DriverUtilities.MSSQL);
     conexao.setDataSource(url);
     conexao.setConexao();
     conexao.getConexao().setCatalog("sae");*/
     conexao.setConexao();
     pedidoBD = new PedidoBD(conexao);
		
	}
/**
* Metodo que monta o menu principal e 
* obtem uma opcao do usuario
*/
	public int criaMenuPrincipal(){
		int opcao;
		System.out.println("Menu de Opcoes:");
		System.out.println("-------------------");
		System.out.println("1. Adicionar Aluno");
		System.out.println("2. Remover Aluno  ");
		System.out.println("3. Consultar Media Aluno");
		System.out.println("4. Consultar Media Geral");
		System.out.println("5. Sair do Programa");
		System.out.println("-------------------");
		return opcao = Console.readInt();
	}
/**
* Metodo que adiciona itens relacionado com o seu pedido
* 
* FEITO
*/	
	public void AdicionarNotas(Pedido p) throws Exception {
		String opcao;
		do
		{
			System.out.println("Adicionar Notas:");
			System.out.println("-------------------");


			System.out.print("Valor Nota:");
			double valornota = Console.readDouble();
			
		    pedidoBD.AdicionarNotaBD(new Nota(valornota),p.getID().intValue());
			p.addItem(valornota);
			System.out.print("Deseja Adicionar mais uma Nota? [S|N]: ");
			opcao = Console.readLine();
		} while (opcao.compareTo("S") == 0);
	}
	

/**
*      FEIto
*/	
	public void AdicionarAluno()throws Exception{
		Pedido p;
		String opcao;
		do {
			System.out.println("Adicionar Aluno:");
			System.out.println("-------------------");
			System.out.print("Matricula do Aluno:");
			Integer matricula = new Integer(Console.readInt());
			System.out.print("Nome do Aluno:");
			String nomealuno = Console.readLine();
			
						
			p = new Pedido(matricula, nomealuno);
			conexao.getConexao().setAutoCommit(false);
			// Gravando pedido no  Banco de Dados
			pedidoBD.AdicionarAlunoBD(p);
			
			this.AdicionarNotas(p);

			
			conexao.getConexao().commit();
			//conexao.getConexao().setAutoCommit(true);
			
			System.out.print("Deseja Adicionar mais um Aluno? [S|N]: ");
			opcao = Console.readLine();
		} while(opcao.compareTo("S") == 0);

	}
	
	
	
	
	public void RemoverAluno()throws Exception{
		int opcao;
		System.out.println("Remover Aluno:");
		System.out.println("-------------------");
		System.out.print("Digite a matricula do Aluno:");
		Integer id = new Integer(Console.readInt());
		pedidoBD.RemoverAlunoBD(id);
	}
	

	
	public void ConsultarAluno() throws Exception{
		int opcao;
		System.out.println("Consultar Media Aluno:");
		System.out.println("-------------------");
		System.out.print("Digite a matricula do Aluno:");
		Integer id = new Integer(Console.readInt());
		
		
//		System.out.println(pedidoBD.ConsultarMediaAlunoBD(id));
		System.out.println("-------------------");
		pedidoBD.ConsultarMediaAlunoBD(id);
		System.out.println("-------------------");
	
	}
	
	
	public void ConsultarMediaGeral() throws Exception{
		int opcao;
		System.out.println("A Media geral:");


		
		
//		System.out.println(pedidoBD.ConsultarMediaAlunoBD(id));

		pedidoBD.ConsultarMediaGeralBD();
		System.out.println("-------------------");
	
	}
	
	
	public static void main(String args[]){
		
		try {
			ColecaoPedido cp = new ColecaoPedido();
			int opcao;
			while((opcao = cp.criaMenuPrincipal()) != 5){
				if(opcao == ColecaoPedido.ADICIONAR)
					cp.AdicionarAluno();
				else if(opcao == ColecaoPedido.CONSULTAR)
					cp.ConsultarAluno();
				else if(opcao == ColecaoPedido.REMOVER)
					cp.RemoverAluno();
				else if(opcao == ColecaoPedido.MEDIA)
					cp.ConsultarMediaGeral();
				else System.out.println("Escolha uma opcao correta.");
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}
}