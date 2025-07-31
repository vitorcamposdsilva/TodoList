package br.com.vitorcamposdsilva.todolist.enums;

public enum StatusTarefa {
    PENDENTE("Pendente"),
    EM_ANDAMENTO("Em andamento"),
    AGUARDANDO_REVISAO("Aguardando revisão"),
    CONCLUIDA("Concluída"),
    BLOQUEADA("Bloqueada"),
    CANCELADA("Cancelada");

    private final String descricao;

    StatusTarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
