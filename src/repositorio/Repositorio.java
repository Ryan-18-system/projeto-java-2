
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package repositorio;
import java.util.ArrayList;

import modelo.Contato;
import modelo.Telefone;

public class Repositorio {
	
	private ArrayList<Contato> contatos = new ArrayList<Contato>();
	private ArrayList<Telefone> telefones = new ArrayList<Telefone>();

	public void adicionar(Contato p){
		contatos.add(p);
	}
	public void remover(Contato p){
		contatos.remove(p);
	}

	public Contato localizarContato(String nome){
		for(Contato p : contatos){
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	public void adicionar(Telefone p){
		telefones.add(p);
	}
	public void remover(Telefone p){
		telefones.remove(p);
	}
	public Telefone localizarTelefone(String numero){
		for(Telefone p : telefones){
			if(p.getNumero().equals(numero))
				return p;
		}
		return null;
	}
	
	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}
	public ArrayList<Contato> getContatos() {
		return contatos;
	}

	public int getTotalTelefones(){
		return telefones.size();
	}
	public int getTotalContatos(){
		return contatos.size();
	}
}

