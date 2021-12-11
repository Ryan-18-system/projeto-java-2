/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POO - Programa��o Orientada a Objetos
 * Prof. Fausto Ayres
 *
 */
package fachada;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Contato;
import modelo.Telefone;
import repositorio.Repositorio;

public class Fachada {
	private Fachada() {}		//classe nao instanciavel

	private static Repositorio repositorio = new Repositorio();

	public static ArrayList<Contato> listarContatos(String caracteres) {
		ArrayList<Contato> todos =  repositorio.getContatos();
		if (caracteres.isEmpty())
			return todos;
		else {
			ArrayList<Contato> aux =  new ArrayList<>();
			for(Contato c : todos)
				if(c.getNome().contains(caracteres))
					aux.add(c);
			return aux;
		}
	}

	public static ArrayList<Telefone> listarTelefones(String digitos) {
		ArrayList<Telefone> todos = repositorio.getTelefones();
		if (digitos.isEmpty())
			return  todos;
		else{
			ArrayList<Telefone> aux  = new ArrayList<>();
			for (Telefone tel: todos)
				if(tel.getNumero().contains(digitos))
					aux.add(tel);
			return aux;

		}
	}

	public static Contato criarContato(String nome,String nascimento, String endereco)	throws  Exception {
		nome = nome.trim();
		nascimento = nascimento.trim();
		endereco = endereco.trim();

		Contato contato = repositorio.localizarContato(nome);
		if (contato!=null)
			throw new Exception("criar contato - nome existente:" + nome);

		LocalDate data;
		try {
			DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data  = LocalDate.parse(nascimento, parser); 
		}
		catch(DateTimeParseException e) {
			throw new Exception ("data de nascimento com formato invalido");
		}

		contato = new Contato(nome,data,endereco);
		repositorio.adicionar(contato);
		return contato;
	}

	public static Telefone adicionarTelefoneContato(String numero, String nome)	throws  Exception{
		nome = nome.trim();
		numero = numero.trim();
		Contato c = repositorio.localizarContato(nome);
		if (c==null)
			throw  new Exception("Contato não cadastrado: " + nome);
		Telefone t = repositorio.localizarTelefone(numero);
		if (t==null){
			t = new Telefone(numero);
			repositorio.adicionar(t);
			c.adicionar(t);
			t.adicionar(c);
		}else{
			Telefone t2 = repositorio.localizarTelefone(numero);
			if(t2 != null)
				throw  new Exception("o contato ja possui este número: "+numero);
			else{
				c.adicionar(t);
				t.adicionar(c);
			}
		}
		return t;
		/*
		 * localizar contato  no repositorio
		 * localizar  telefone  no repositorio
		 * localizar  telefone no contato
		 * criar telefone (se inexistente no repositorio) e adiciona-lo ao repositorio 
		 * criar relacionamento entre contato e telefone
		 */

	}

	public static void removerTelefoneContato(String numero, String nome) throws  Exception{
		nome = nome.trim();
		numero = numero.trim();
        
		/*
		 * localizar contato  no repositorio
		 * localizar  telefone  no repositorio
		 * localizar  telefone no contato
		 * remover relacionamento entre contato com telefone e, se for o caso, remover telefone do repositorio
		 */
	}

	public static void alterarTelefone(String numero, String novo) throws  Exception{
		novo = novo.trim();
		numero = numero.trim();

		/*
		 * localizar telefone no repositorio com numero atual 
		 * localizar  telefone no repositorio com numero novo 
		 * alterar numero 
		 */
	}

	public static void inicializar() throws Exception 	{
		//ler os arquivos de dados os contatos e telefones
		Scanner arquivo1=null;
		Scanner arquivo2=null;
		InputStream fonte;
		try	{
			fonte = Fachada.class.getResourceAsStream("/arquivos/contatos.csv");// Para arquivo interno
			arquivo1 = new Scanner(fonte);// Para arquivo interno
		}
		catch(Exception e)		{
			throw new Exception("arquivo de contatos inexistente:");
		}

		try	{
			fonte = Fachada.class.getResourceAsStream("/arquivos/telefones.csv");// Para arquivo interno
			arquivo2 = new Scanner(fonte);// Para arquivo interno
		}
		catch(Exception e)		{
			arquivo1.close();
			throw new Exception("arquivo de telefones inexistente:");
		}

		String linha;	
		String[] partes;	
		String nome, nascimento, endereco;
		Contato c;
		while(arquivo1.hasNextLine()) 	{
			linha = arquivo1.nextLine().trim();		
			partes = linha.split(";");	
			nome = partes[0];
			nascimento = partes[1];
			endereco = partes[2];
			c = Fachada.criarContato(nome,nascimento,endereco);
		} 
		arquivo1.close();			

		String numero;
		String [] nomes;
		Telefone t;
		while(arquivo2.hasNextLine()) 	{
			linha = arquivo2.nextLine().trim();		
			partes = linha.split(";");	
			numero = partes[0];
			nomes = partes[1].split(",");	
			for(String nom : nomes){
				t = Fachada.adicionarTelefoneContato(numero, nom);
			}
		}
		arquivo2.close();	
	}

	public static void	finalizar() throws Exception {
		//gravar nos arquivos de contatos e telefones
		FileWriter arquivo1=null;
		FileWriter arquivo2=null;
		try	{
			arquivo1 = new FileWriter( new File("src/arquivos/contatos2.csv") ); 
		}
		catch(IOException e){
			throw new Exception("problema na cria��o do arquivo de participantes");
		}

		try	{
			arquivo2 = new FileWriter( new File("src/arquivos/telefones2.csv") ); 
		}
		catch(IOException e){
			throw new Exception("problema na cria��o do arquivo de reunioes");
		}

		for(Contato c : Fachada.listarContatos("")) {
			String nascimento = c.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			arquivo1.write(c.getNome() +";" + nascimento + ";" + c.getEndereco() +"\n");	
		} 
		arquivo1.close();			

		ArrayList<String> listanomes;
		String strnomes;
		for(Telefone t : Fachada.listarTelefones("")) {
			//criar uma lista de strings com os nomes dos contatos
			listanomes = new ArrayList<>();
			for(Contato c : t.getContatos()) {
				listanomes.add(c.getNome());
			}
			//inserir separador na lista de strings
			strnomes = String.join(",", listanomes);
			arquivo2.write(t.getNumero()+";"+strnomes+"\n");	
		} 
		arquivo2.close();	
	}
	/*public  static  void main(String[] args){
		try {
			criarContato("ryan", "25/04/2002", "epitacio");
			adicionarTelefoneContato("83987466214","ryan");
			System.out.println(listarTelefones(""));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}


	}*/
}


