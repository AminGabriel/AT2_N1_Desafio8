package gestao_2;

import javax.swing.JOptionPane;

public class Tarefa {

    private String descricao;
    private String status;
    private int prazo;

    public Tarefa(String descricao, int prazo) {
        this.descricao = descricao;
        this.status = "Pendente";
        this.prazo = prazo;
    }
    
    public Tarefa(int prazo) {
        this.descricao = "Sem descrição";
        this.status = "Pendente";
        this.prazo = prazo;
    }
    

    public void menuTarefa() {
        Object[] opcooes = {"Alterar status", "Alterar descrição", "Adicionar prazo(em dias)", "Voltar"};
        Object opcaao;

        do {
            opcaao = JOptionPane.showInputDialog(null, "Escolha a opção:", "MENU DE TAREFA", JOptionPane.INFORMATION_MESSAGE, null, opcooes, opcooes[0]);

            if (opcaao == "Alterar status") {
                alterarStatus();
            } else if (opcaao == "Alterar descrição") {
                alterarDescricao();
            } else if (opcaao == "Adicionar prazo(em dias)") {
                adicionarPrazo();
            }
        } while (opcaao != "Voltar");
    }

    public void alterarStatus() {
        Object[] statusOpcoes = {"Pendente", "Em andamento", "Concluída"};
        Object novoStatus = JOptionPane.showInputDialog(null, "Escolha o novo status:", "ALTERAR STATUS", JOptionPane.INFORMATION_MESSAGE, null, statusOpcoes, statusOpcoes[0]);
        setStatus(novoStatus.toString());
        JOptionPane.showMessageDialog(null, "Status alterado com sucesso!");
    }

    public void alterarDescricao() {
        String novaDescricao = JOptionPane.showInputDialog("Informe a nova descrição:");
        setDescricao(novaDescricao);
        JOptionPane.showMessageDialog(null, "Descrição alterada com sucesso!");
    }

    public void adicionarPrazo() {
        int dias = Usuario.solicitarNumeroPositivo("Informe o número de dias a adicionar:");
        setPrazo(getPrazo() + dias);
        JOptionPane.showMessageDialog(null, "Prazo alterado com sucesso!");
    }

    // Getters e Setters

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }
}