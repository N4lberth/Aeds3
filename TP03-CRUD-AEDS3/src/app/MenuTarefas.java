package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import app.IndiceInvertido.TarefaIndex;
import app.arquivos.ArquivoCategoria;
import app.arquivos.ArquivoRotulo;
import app.arquivos.ArquivoTarefa;
import app.entidades.Categoria;
import app.entidades.Rotulo;
import app.entidades.Tarefa;
import app.main.Principal;

public class MenuTarefas extends Principal {
    private static ArquivoTarefa arqTarefas;
    private static ArquivoCategoria arqCategorias;
    private static ArquivoRotulo arqRotulos;

    public MenuTarefas() throws Exception {
        arqTarefas = new ArquivoTarefa();
        arqCategorias = new ArquivoCategoria();
        arqRotulos = new ArquivoRotulo();
    }

    public static void menu() {
        try {
            int opcao = 0;
            do {
                opcoes_menu();
                opcao = ler_opcao();
                action(opcao);
            } while (opcao != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void opcoes_menu() {
        System.out.println("\nPUCTAREFA 1.0          ");
        System.out.println("-------------------------");
        System.out.println("> Início > Tarefas       ");
        System.out.println("1 - Buscar               ");
        System.out.println("2 - Incluir              ");
        System.out.println("3 - Alterar              ");
        System.out.println("4 - Excluir              ");
        System.out.println("5 - Buscar por Categoria ");
        System.out.println("6 - Buscar por Rótulo    ");
        System.out.println("7 - Buscar por Palavra   ");
        System.out.println("0 - Voltar               ");
        System.out.print("Opção: ");
    }

    protected static void action(int opcao) {
        switch (opcao) {
            case 0:
                break;
            case 1:
                buscarTarefa();
                break;
            case 2:
                incluirTarefa();
                break;
            case 3:
                alterarTarefa();
                break;
            case 4:
                excluirTarefa();
                break;
            case 5:
                buscarTarefaPorCategoria();
                break;
            case 6:
                buscarTarefaPorRotulo();
                break;
            case 7:
                buscarTarefaPorPalavra();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static LocalDate formatarData(String dataEmString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = null;
        try {
            data = LocalDate.parse(dataEmString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("\nPor favor, use o formato dd/MM/yyyy.");
        }
        return data;
    }

    private static void listarStatus() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Pendente        ");
        System.out.println("2 - Em Andamento    ");
        System.out.println("3 - Concluída       ");
        System.out.println("4 - Cancelada       ");
        System.out.println("5 - Atrasada        ");
        System.out.print("Status: ");
    }

    private static void listarPrioridades() {
        System.out.println("\nEscolha uma prioridade:");
        System.out.println("1 - Muito Baixa          ");
        System.out.println("2 - Baixa                ");
        System.out.println("3 - Média                ");
        System.out.println("4 - Alta                 ");
        System.out.println("5 - Urgente              ");
        System.out.print("Prioridade: ");
    }

    private static void listarTarefas(List<Tarefa> lista) {
        if (lista != null) {
            System.out.println("\nLista de tarefas:");
            int tam = lista.size();
            for (int i = 0; i < tam; i++) {
                System.out.println((i + 1) + ": " + lista.get(i).getName());
            }
        }
    }

    private static void listarCategorias(List<Categoria> lista) {
        if (lista != null) {
            System.out.println("\nLista de categorias:");
            int tam = lista.size();
            for (int i = 0; i < tam; i++) {
                System.out.println((i + 1) + ": " + lista.get(i).getNome());
            }
        }
    }

    private static void listarRotulos(List<Rotulo> lista) {
        if (lista != null) {
            System.out.println("\nLista de rótulos:");
            int tam = lista.size();
            for (int i = 0; i < tam; i++) {
                System.out.println((i + 1) + ": " + lista.get(i).getRotulo());
            }
        }
    }

    public static Tarefa lerTarefa() {
        Tarefa tarefa = null;
        try {
            List<Categoria> categorias = arqCategorias.readAll();
            List<Rotulo> rotulos = arqRotulos.readAll();

            if (categorias.isEmpty()) {
                System.out.println("Não há categorias cadastradas!");
            } else if(rotulos.isEmpty()) {
                System.out.println("Não há rótulos cadastrados!");
            }
            else {
                System.out.print("\nNome: ");
                String nome = console.nextLine();

                if (nome.length() > 0) {
                    LocalDate dataCriacao = null;
                    while (dataCriacao == null) {
                        System.out.print("\nData de Criação (dd/MM/yyyy): ");
                        String dc1 = console.nextLine();
                        dataCriacao = formatarData(dc1);
                    }

                    LocalDate dataConclusao = null;
                    while (dataConclusao == null) {
                        System.out.print("\nData de Conclusão (dd/MM/yyyy): ");
                        String dc2 = console.nextLine();
                        dataConclusao = formatarData(dc2);
                    }

                    byte status = 0;
                    while (status < 1 || status > 5) {
                        listarStatus();
                        try {
                            status = Byte.parseByte(console.nextLine());
                            if (status < 1 || status > 5) {
                                System.out.println("Escolha um valor entre 1 e 5.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Insira um número.");
                        }
                    }

                    byte prioridade = 0;
                    while (prioridade < 1 || prioridade > 5) {
                        listarPrioridades();
                        try {
                            prioridade = Byte.parseByte(console.nextLine());
                            if (prioridade < 1 || prioridade > 5) {
                                System.out.println("Escolha um valor entre 1 e 5.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Insira um número.");
                        }
                    }

                    listarCategorias(categorias);
                    int idCategoria = -1;
                    while (idCategoria < 0 || idCategoria > categorias.size()) {
                        System.out.print("Categoria (ID): ");
                        try {
                            idCategoria = Integer.parseInt(console.nextLine());
                            if (idCategoria < 0 || idCategoria > categorias.size()) {
                                System.out.println("ID inválido!");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Insira um número.");
                        }
                    }

                    listarRotulos(rotulos);
                    ArrayList<Integer> idsRotulos = new ArrayList<>();
                    boolean valid = false;

                    while (!valid) {
                        System.out.print("Rótulos (IDs separados por vírgula): ");
                        idsRotulos.clear();
                        valid = true;

                        try {
                            String[] ids = console.nextLine().split(",");
                            for (String id : ids) {
                                int idRotulo = Integer.parseInt(id.trim());
                                boolean exists = false;
                                for (Rotulo rotulo : rotulos) {
                                    if (rotulo.getId() == idRotulo) {
                                        exists = true;
                                        break;
                                    }
                                }
                                if (!exists) {
                                    System.out.println("Rótulo inválido: " + idRotulo);
                                    valid = false;
                                    break;
                                } else {
                                    idsRotulos.add(idRotulo);
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Insira um número válido.");
                            valid = false;
                        }
                    }

                    if (valid) {
                        tarefa = new Tarefa(nome, dataCriacao, dataConclusao, status, prioridade, idCategoria, idsRotulos);
                    } else {
                        System.out.println("Operação cancelada!");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("\nErro ao ler tarefa!");
        }
        return tarefa;
    }

    public static void incluirTarefa() {
        System.out.println("\n> Incluir Tarefa:");
        try {
            Tarefa novaTarefa = lerTarefa();
            if (novaTarefa != null) {
                System.out.println("\nConfirma inclusão? (S/N)");
                char resp = console.nextLine().charAt(0);

                if (resp == 'S' || resp == 's') {
                    try {
                        arqTarefas.create(novaTarefa);
                        System.out.println("Tarefa incluída com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Não foi possível criar a tarefa!");
                    }
                } else {
                    System.out.println("Inclusão cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao incluir tarefa!");
        }
    }

    public static void buscarTarefa() {
        System.out.println("\n> Buscar Tarefa:");

        try {
            List<Tarefa> lista = arqTarefas.readAll();

            if (lista.isEmpty()) {
                System.out.println("Não há tarefas cadastrada.");
            } else {
                listarTarefas(lista);

                System.out.print("ID da Tarefa: ");
                String input = console.nextLine();

                if (input.length() > 0) {
                    Tarefa tarefaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size();
                    int id = Integer.parseInt(input);
                    for (int i = 0; i < tam && !encontrada; i++) {
                        if (lista.get(i).getId() == id) {
                            tarefaEncontrada = lista.get(i);
                            encontrada = true;
                        }
                    }

                    if (tarefaEncontrada != null) {
                        System.out.println(tarefaEncontrada);
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }

        } catch (Exception e) {
            System.err.println("Não foi possível buscar a tarefa!");
        }
    }

    public static void alterarTarefa() {
        System.out.println("\n> Alterar Tarefa:");

        try {
            List<Tarefa> lista = arqTarefas.readAll();

            if (lista.isEmpty()) {
                System.out.println("Não há tarefa cadastrada.");
            } else {
                listarTarefas(lista);

                System.out.print("ID da Tarefa: ");
                String input = console.nextLine();

                if (input.length() > 0) {
                    Tarefa tarefaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size();
                    int id = Integer.parseInt(input);
                    for (int i = 0; i < tam && !encontrada; i++) {
                        if (lista.get(i).getId() == id) {
                            tarefaEncontrada = lista.get(i);
                            encontrada = true;
                        }
                    }

                    if (tarefaEncontrada != null) {
                        System.out.print("\nInforme os novos dados:");
                        Tarefa novaTarefa = lerTarefa();

                        if (novaTarefa != null && novaTarefa.getName().length() > 0) {
                            novaTarefa.setId(tarefaEncontrada.getId());
                            arqTarefas.update(novaTarefa);
                            System.out.println("Tarefa alterada.");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível alterar a Tarefa!");
            e.printStackTrace();
        }
    }

    public static void excluirTarefa() {
        System.out.println("\n> Excluir Tarefa:");

        try {
            List<Tarefa> lista = arqTarefas.readAll();

            if (lista.isEmpty()) {
                System.out.println("Não há tarefa cadastrada.");
            } else {
                listarTarefas(lista);

                System.out.print("ID da tarefa: ");
                String input = console.nextLine();

                if (input.length() > 0) {
                    Tarefa tarefaEncontrada = null;
                    boolean encontrada = false;
                    int tam = lista.size();
                    int id = Integer.parseInt(input);
                    for (int i = 0; i < tam && !encontrada; i++) {
                        if (lista.get(i).getId() == id) {
                            tarefaEncontrada = lista.get(i);
                            encontrada = true;
                        }
                    }

                    if (tarefaEncontrada != null) {
                        System.out.print("\nTarefa:");
                        System.out.println(tarefaEncontrada);

                        System.out.println("\nConfirma a exclusão? (S/N)");
                        char resp = console.nextLine().charAt(0);

                        if (resp == 'S' || resp == 's') {
                            boolean sucesso = arqTarefas.delete(tarefaEncontrada.getId());

                            if (sucesso) {
                                System.out.println("Tarefa excluída.");
                            } else {
                                System.out.println("Erro: Não foi possível excluir a tarefa.");
                            }
                        }
                    } else {
                        System.out.println("Tarefa não encontrada.");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível excluir a tarefa!");
        }
    }

    public static boolean buscarTarefaPorCategoria() {
        boolean result = false;
        System.out.println("\n> Buscar Tarefa por Categoria:");

        try {
            List<Categoria> categorias = arqCategorias.readAll();

            if (categorias.isEmpty()) {
                System.out.println("Não há categorias cadastradas!");
            } else {
                listarCategorias(categorias);
                System.out.print("ID da Categoria: ");
                int idCategoria = Integer.parseInt(console.nextLine());

                if (idCategoria > 0) {
                    List<Tarefa> tarefas = arqTarefas.readAll(idCategoria);

                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas!");
                    } else {
                        System.out.println("\nLista de tarefas:");
                        for (Tarefa tarefa : tarefas) {
                            System.out.println(tarefa);
                        }
                        result = true;
                    }
                } else {
                    System.out.println("ID inválido!");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível buscar tarefa!");
        }

        return result;
    }

    public static boolean buscarTarefaPorRotulo() {
        boolean result = false;
        System.out.println("\n> Buscar Tarefa por Rótulo:");

        try {
            List<Rotulo> rotulos = arqRotulos.readAll();

            if (rotulos.isEmpty()) {
                System.out.println("Não há rótulos cadastrados!");
            } else {
                listarRotulos(rotulos);
                System.out.print("ID do Rótulo: ");
                int idRotulo = Integer.parseInt(console.nextLine());

                if (idRotulo > 0) {
                    List<Tarefa> tarefas = arqTarefas.readRotulos(idRotulo);

                    if (tarefas.isEmpty()) {
                        System.out.println("Não há tarefas cadastradas!");
                    } else {
                        System.out.println("\nLista de tarefas:");
                        for (Tarefa tarefa : tarefas) {
                            System.out.println(tarefa);
                        }
                        result = true;
                    }
                } else {
                    System.out.println("ID inválido!");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível buscar tarefa!");
        }

        return result;
    }

    public static boolean buscarTarefaPorPalavra() {
        boolean result = false;
        System.out.println("\n> Buscar Tarefa por Palavra:");

        try {
            TarefaIndex index = new TarefaIndex(10, "dicionario.db", "blocos.db");
            List<Tarefa> tarefas = arqTarefas.readAll();
            for(int i = 0; i < tarefas.size(); i++) {
                index.inserirTarefa(tarefas.get(i).getId(), tarefas.get(i).getName());
            }
            if (index.isEmpty() == true) {
                System.out.println("Não há tarefas cadastradas!");
            } else {
                System.out.print("Digite palavras: ");
                String palavras = console.nextLine();
                List<Integer> resultados = index.buscar(palavras);

                System.out.println("\nLista de tarefas:");
                int tam = tarefas.size();
                for(int i = 0; i < resultados.size(); i++) {
                    for (int j = 0; j < tam; j++) {
                        if(resultados.get(i) == tarefas.get(j).getId())
                        {
                            System.out.println((j + 1) + ": " + tarefas.get(j).getName());
                        }
                    }
                }                                                
            }
        } catch (Exception e) {
            System.out.println("Não foi possível buscar tarefa!");
        }

        return result;
    }

}
