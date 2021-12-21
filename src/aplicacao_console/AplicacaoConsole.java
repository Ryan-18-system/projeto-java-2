package aplicacao_console;
import fachada.Fachada;
import modelo.Contato;
import modelo.Telefone;

public class AplicacaoConsole {

	public AplicacaoConsole() {
		try {
			Contato contato;
			Telefone telefone;
			Fachada.inicializar();

			contato = Fachada.criarContato("teste1", "11/11/2021","busto tamandare");
			telefone = Fachada.adicionarTelefoneContato("00000000", "teste1");
			telefone = Fachada.adicionarTelefoneContato("99999999", "teste1");
			System.out.println("novo contato:"+contato);

			Fachada.alterarTelefone("00000000", "11111111");
			Fachada.removerTelefoneContato("11111111", "teste1");
			Fachada.removerTelefoneContato("99999999", "teste1");

			System.out.println("\n---------listagem de contatos-----");
			for(Contato c : Fachada.listarContatos(""))
				System.out.println(c);
			System.out.println("\n---------listagem de telefones");
			for(Telefone t : Fachada.listarTelefones(""))
				System.out.println(t);

			Fachada.finalizar();

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}

		//****************
		testarExcecoes();
		//****************

	}


	public static void testarExcecoes()
	{
		System.out.println("\n-------TESTE EXCEÇÕES LANÇADAS--------");
		try
		{
			Contato contato = Fachada.criarContato("teste", "11/11/2021","busto tamandare");
			contato = Fachada.criarContato("teste", "11/11/2021","busto tamandare");
			System.out.println("*************1--->Nao lançou exceção para: criar contato ");
		}catch (Exception e) {System.out.println("1ok--->"+e.getMessage());}

		try
		{
			Telefone telefone;
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste");
			telefone = Fachada.adicionarTelefoneContato("22222222", "teste");
			System.out.println("*************2--->Nao lançou exceção para: ad telefone");
		}catch (Exception e) {System.out.println("2ok--->"+e.getMessage());}

		try
		{
			Fachada.removerTelefoneContato("22222222", "teste");
			Fachada.alterarTelefone("22222222", "33333333");
			System.out.println("*************3--->Nao lançou exceção: alterar telefone ");
		}catch (Exception e) {System.out.println("3ok--->"+e.getMessage());}

		try
		{
			Telefone telefone = Fachada.adicionarTelefoneContato("22222222", "teste");
			Fachada.alterarTelefone("22222222", "22222222");
			System.out.println("*************4--->Nao lançou exceção: alterar telefone ");
		}catch (Exception e) {System.out.println("4ok--->"+e.getMessage());}
	}



	public static void main (String[] args)
	{
		AplicacaoConsole aplicacaoConsole = new AplicacaoConsole();
	}
}

