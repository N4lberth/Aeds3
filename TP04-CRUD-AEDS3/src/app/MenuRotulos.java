package app;

import java.util.List;
import app.arquivos.ArquivoRotulo;
import app.entidades.Rotulo;
import app.main.Principal;

public class MenuRotulos extends Principal {
    private static ArquivoRotulo arqRotulos;

    public MenuRotulos() throws Exception {
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
        System.out.println("\nPUCTAREFA 1.0           ");
        System.out.println("-------------------------");
        System.out.println("> Início > Rótulos       ");
        System.out.println("1 - Buscar               ");
        System.out.println("2 - Incluir              ");
        System.out.println("3 - Alterar              ");
        System.out.println("4 - Excluir              ");
        System.out.println("0 - Voltar               ");
        System.out.print("Opção: ");
    }

    protected static void action(int opcao) {
        switch (opcao) {
            case 0:
                break;
            case 1:
                buscarRotulo();
                break;
            case 2:
                incluirRotulo();
                break;
            case 3:
                alterarRotulo();
                break;
            case 4:
                excluirRotulo();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void incluirRotulo() {
        System.out.println("\n> Incluir Rótulo:");
        try {
            Rotulo novoRotulo = lerRotulo();
            if (novoRotulo != null) {
                System.out.println("\nConfirma inclusão? (S/N)");
                char resp = console.nextLine().charAt(0);

                if (resp == 'S' || resp == 's') {
                    try {
                        arqRotulos.create(novoRotulo);
                        System.out.println("Rótulo incluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Não foi possível criar o rótulo!");
                    }
                } else {
                    System.out.println("Inclusão cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao incluir rótulo!");
        }
    }

    public static void buscarRotulo() {
        System.out.println("\n> Buscar Rótulo:");

        try {
            List<Rotulo> lista = arqRotulos.readAll();

            if (lista.isEmpty()) {
                System.out.println("Não há rótulos cadastrados.");
            } else {
                listarRotulos(arqRotulos);

                System.out.print("Número do Rótulo: ");
                String input = console.nextLine();

                if (!input.isEmpty()) {
                    try {
                        int posicao = Integer.parseInt(input) - 1; // Convert to zero-based index

                        if (posicao >= 0 && posicao < lista.size()) {
                            Rotulo rotuloEncontrado = lista.get(posicao);
                            System.out.println(rotuloEncontrado);
                        } else {
                            System.out.println("Número inválido!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Insira um número válido.");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (Exception e) {
            System.err.println("Não foi possível buscar o rótulo!");
        }
    }

    public static void alterarRotulo() {
        System.out.println("\n> Alterar Rótulo:");

        try {
            List<Rotulo> lista = arqRotulos.readAll();

            if (lista.isEmpty()) {
                System.out.println("Não há rótulos cadastrados.");
            } else {
                listarRotulos(arqRotulos);

                System.out.print("Número do Rótulo: ");
                String input = console.nextLine();

                if (!input.isEmpty()) {
                    int numero = Integer.parseInt(input);

                    if (numero > 0 && numero <= lista.size()) {
                        Rotulo rotuloEncontrado = lista.get(numero - 1);

                        System.out.print("\nInforme os novos dados:");
                        Rotulo novoRotulo = lerRotulo();

                        if (novoRotulo != null && !novoRotulo.getRotulo().isEmpty()) {
                            novoRotulo.setId(rotuloEncontrado.getId());
                            arqRotulos.update(novoRotulo);
                            System.out.println("Rótulo alterado.");
                        } else {
                            System.out.println("Operação cancelada!");
                        }
                    } else {
                        System.out.println("Número inválido. Operação cancelada!");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível alterar o rótulo!");
            e.printStackTrace();
        }
    }

    public static void excluirRotulo() {
        System.out.println("\n> Excluir Rótulo:");

        try {
            List<Rotulo> lista = arqRotulos.readAll();

            if (lista.isEmpty()) {
                System.out.println("Não há rótulos cadastrados.");
            } else {
                listarRotulos(arqRotulos);

                System.out.print("Número do Rótulo: ");
                String input = console.nextLine();

                if (input.length() > 0) {
                    try {
                        int posicao = Integer.parseInt(input) - 1; // Convert to zero-based index

                        if (posicao >= 0 && posicao < lista.size()) {
                            Rotulo rotuloEncontrado = lista.get(posicao);
                            System.out.println(rotuloEncontrado);

                            System.out.println("\nConfirma a exclusão? (S/N)");
                            char resp = console.nextLine().charAt(0);

                            if (resp == 'S' || resp == 's') {
                                boolean sucesso = arqRotulos.delete(rotuloEncontrado.getId());

                                if (sucesso) {
                                    System.out.println("Rótulo excluído.");
                                } else {
                                    System.out.println("Erro: Não foi possível excluir o rótulo.");
                                }
                            }
                        } else {
                            System.out.println("Número inválido!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Insira um número válido.");
                    }
                } else {
                    System.out.println("Operação cancelada!");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi possível excluir o rótulo!");
        }
    }

    private static Rotulo lerRotulo() {
        Rotulo rotulo = null;
        try {
            System.out.print("\nRótulo: ");
            String nomeRotulo = console.nextLine();

            if (nomeRotulo.length() > 0) {
                rotulo = new Rotulo(-1, nomeRotulo);
            } else {
                System.out.println("Operação cancelada!");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao ler rótulo!");
        }
        return rotulo;
    }

    public static void listarRotulos(ArquivoRotulo arqRotulos) {
        try {
            List<Rotulo> lista = arqRotulos.readAll();
            int cont = 1;
            if( lista.isEmpty( ) ) {
                System.out.println("Nenhum rótulo cadastrado." );
            } else {
                lista.sort( (r1, r2) -> Integer.compare( r1.getId(), r2.getId() ) );
                System.out.println( "\nLista de rótulos:" );
                for (Rotulo rotulo : lista) {
                    System.out.println(cont + ": " + rotulo.getRotulo());
                    cont++;
                }
            }
        }catch( Exception e ) {
            System.out.println("Erro no sistema. Não foi possível listar os rótulos!");
        }
    }
}