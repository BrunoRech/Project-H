package control.command;

import java.util.ArrayList;
import java.util.List;

//classe responsável por receber os comandos que deverão ser executados
public class CommandInvoker {

	private static CommandInvoker instance;
	
	private List<Command> todos = new ArrayList<>();

	private List<Command> undo = new ArrayList<>();

	public static CommandInvoker getInstance() {
		if(instance == null) {
			instance = new CommandInvoker();
		}
		return instance;
	}
	
	private CommandInvoker() {}
	
	//função de desfazer a ação
	public void undo() {
		if (todos.size() > 0) {
			Command comm = todos.remove(todos.size() - 1);
			comm.undo();
			undo.add(comm);
		}

	}
	//função de refazer a ação
	public void redo() {
		if (undo.size() > 0) {
			Command comm = undo.remove(undo.size() - 1);
			comm.redo();
			todos.add(comm);
		}
	}
	//imprime o log dos comandos
	public void imprimir() {

		System.out.println("Log :");
		for (Command comm : todos) {
			System.out.println(" " + comm);
		}

	}
	//executa um comando específico na hora
	public void execute(Command comm) {
		System.out.println("execute " +comm.toString());
		
		todos.add(comm);
		comm.execute();
	}
	
}
