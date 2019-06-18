package control.command;

import java.util.ArrayList;
import java.util.List;

//classe responsavel por receber os comandos que deverao ser executados
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
	
	//funcao de desfazer a acao
	public void undo() {
		if (todos.size() > 0) {
			Command comm = todos.remove(todos.size() - 1);
			comm.undo();
			undo.add(comm);
		}

	}
	
	//executa um comando especifico na hora
	public void execute(Command comm) {
		todos.add(comm);
		comm.execute();
	}
	
}
