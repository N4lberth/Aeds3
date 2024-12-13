import java.io.*;

public class Main {
    private static Arquivo<Tarefa> arqTarefas;

    public static void main(String[] args) {

        Tarefa t1 = new Tarefa(-1, "Estudar", 20210901, 20210930, "Em andamento", 1);
        Tarefa t2 = new Tarefa(-1, "Trabalhar", 20210901, 20210930, "Em andamento", 2);
        Tarefa t3 = new Tarefa(-1, "Estudar", 20210901, 20210930, "Em andamento", 3);
        int id1, id2;

        try {

            new File("tarefas.db").delete();  // apaga o arquivo anterior (apenas para testes)
            arqTarefas = new Arquivo<>("tarefas.db", Tarefa.class.getConstructor());

            id1 = arqTarefas.create(t1);
            t1.setId(id1);
            id2 = arqTarefas.create(t2);
            t2.setId(id2);
            arqTarefas.create(t3);
            t3.setId(3);

            t1.setName("Jogar Basquete");
            arqTarefas.update(t1);

            arqTarefas.delete(t2.getId());

            System.out.println(arqTarefas.read(id1));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}