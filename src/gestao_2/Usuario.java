package gestao_2;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private String departamento;
    private List<Tarefa> tarefas;

    public Usuario(String nome, String departamento) {
        this.nome = nome;
        this.departamento = departamento;
        this.tarefas = new ArrayList<>();
    }
    
    public Usuario() {
        this.nome = "Nome não informado";
        this.departamento = "Departamento não informado";
        this.tarefas = new ArrayList<>();
    }
    
    public Usuario(String nome, boolean isDepartamento) {
        this.nome = nome;
        this.departamento = "Departamento não informado";
        this.tarefas = new ArrayList<>();
    }
    public Usuario(boolean isNome, String departamento) {
        this.nome = "Nome não informado";
        this.departamento = departamento;
        this.tarefas = new ArrayList<>();
    }
    
    public void passarDias(int dias) {
        for (Tarefa tarefa : tarefas) {
            tarefa.setPrazo(tarefa.getPrazo() - dias);
        }
    }

    public static Usuario selecionarUsuario(List<Usuario> usuarios) {
        StringBuilder listaUsuarios = new StringBuilder("Lista de usuários:\n");
        for (int i = 0; i < usuarios.size(); i++) {
            listaUsuarios.append(i).append(". ").append(usuarios.get(i).getNome()).append(", ").append(usuarios.get(i).getDepartamento()).append("\n");
        }
        JOptionPane.showMessageDialog(null, listaUsuarios.toString());

        int num1;
        boolean usuarioValido = false;
        Usuario usuarioSelecionado = null;

        while (!usuarioValido) {
            String num = JOptionPane.showInputDialog(null, "Escolha o número do usuário:");
            try {
                num1 = Integer.parseInt(num);
                if (num1 < 0 || num1 >= usuarios.size()) {
                    JOptionPane.showMessageDialog(null, "Número inválido. Tente novamente.");
                } else {
                    usuarioSelecionado = usuarios.get(num1);
                    JOptionPane.showMessageDialog(null, "Usuário " + usuarioSelecionado.getNome() + " logado com sucesso!");
                    usuarioValido = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número.");
            }
        }
        return usuarioSelecionado;
    }

    public void menuUsuario() {
        Object[] opcooes = {"Ver e Interagir com tarefas", "Criar tarefa", "Tarefas atrasadas", "Voltar"};
        Object opcaoo;

        do {
            opcaoo = JOptionPane.showInputDialog(null, "Escolha a opção:", "MENU DO USUÁRIO", JOptionPane.INFORMATION_MESSAGE, null, opcooes, opcooes[0]);

            if (opcaoo == "Ver e Interagir com tarefas") {
                interagirComTarefas();
            } else if (opcaoo == "Criar tarefa") {
                criarTarefa();
            } else if (opcaoo == "Tarefas atrasadas") {
                exibirTarefasAtrasadas();
            }
        } while (opcaoo != "Voltar");
    }

    public void interagirComTarefas() {
    	if (tarefas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma tarefa encontrada!");
        } else {
            StringBuilder listaTarefas = new StringBuilder("Lista de tarefas:\n");
            
            // Listando as tarefas
            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa = tarefas.get(i);
                listaTarefas.append(i).append(". ").append(tarefa.getDescricao())
                             .append(", Prazo: ").append(tarefa.getPrazo())
                             .append(" dias, Status: ").append(tarefa.getStatus()).append("\n");
            }
            
            // Exibindo a lista de tarefas
            JOptionPane.showMessageDialog(null, listaTarefas.toString());
            
            int tarefaIndex = -1;
            boolean tarefaValida = false;

            // Laço para garantir que a entrada seja válida
            while (!tarefaValida) {
                String tarefaNum = JOptionPane.showInputDialog(null, "Digite o número da tarefa que deseja interagir:");
                
                try {
                    tarefaIndex = Integer.parseInt(tarefaNum);

                    // Verifica se o número da tarefa está dentro do intervalo válido
                    if (tarefaIndex >= 0 && tarefaIndex < tarefas.size()) {
                        tarefaValida = true; // Número válido, podemos sair do laço
                    } else {
                        JOptionPane.showMessageDialog(null, "Número da tarefa inválido. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, digite um número inteiro.");
                }
            }

            // Se a tarefa for válida, interagir com ela
            if (tarefaIndex >= 0 && tarefaIndex < tarefas.size()) {
                tarefas.get(tarefaIndex).menuTarefa();
            } else {
                JOptionPane.showMessageDialog(null, "Tarefa inválida.");
            }
        }
    }

    public void criarTarefa() {
        String descricao = JOptionPane.showInputDialog("Informe a descrição da tarefa:");
        int prazo = solicitarNumeroPositivo("Informe o prazo da tarefa (em dias):");
        if (prazo > 0) {
        	if(descricao == null || descricao.trim().isEmpty()) {
        		Tarefa novaTarefa = new Tarefa(prazo);
        		tarefas.add(novaTarefa);
        	}else{
        		Tarefa novaTarefa = new Tarefa(descricao, prazo);
        		tarefas.add(novaTarefa);
        		
        	}
            
            JOptionPane.showMessageDialog(null, "Tarefa criada com sucesso!");
        }
    }

    public void exibirTarefasAtrasadas() {
        StringBuilder tarefasAtrasadas = new StringBuilder("Tarefas Atrasadas:\n");
        boolean temAtrasadas = false;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getPrazo() < 0) {
                tarefasAtrasadas.append("- ").append(tarefa.getDescricao()).append(", Prazo: ").append(tarefa.getPrazo()).append(" dias\n");
                temAtrasadas = true;
            }
        }

        if (temAtrasadas) {
            JOptionPane.showMessageDialog(null, tarefasAtrasadas.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Não há tarefas atrasadas.");
        }
    }

    public static int solicitarNumeroPositivo(String mensagem) {
        int numero = -1;
        boolean numValido = false;

        while (!numValido) {
            String input = JOptionPane.showInputDialog(mensagem);
            try {
                numero = Integer.parseInt(input);
                if (numero > 0) {
                    numValido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Escolha um número inteiro e positivo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Escolha um número inteiro e positivo.");
            }
        }
        return numero;
    }

	public String getNome() {
		return nome;
	}
	
	public String getDepartamento() {
		return departamento;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

   
}