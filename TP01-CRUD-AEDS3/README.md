# TP01-CRUD
Alunos: Guilherme Gomes de Brites, Nalberth Henrique Vieira, Mateus Nunes Belo, Joao Lucas Curi

Construtor (arquivos.Arquivo)

Inicializa o arquivo de dados e o índice direto.
Se o arquivo não tiver um cabeçalho, cria um com o valor 0 (último ID).
Configura o índice direto usando um hash extensível.

create(T object)

Lê o último ID do arquivo, incrementa, e atribui ao objeto.
Converte o objeto para um byte array, obtém o tamanho e escreve (lapide, Indicador de tamanho e o Registro ) no final do arquivo.
Atualiza o índice direto com o novo ID e endereço do objeto.

read(int id)

Utiliza o índice direto para obter o endereço do objeto com o ID fornecido.
Se o endereço for válido, vai até ele, verifica se o registro está ativo (sem lápide), e lê o registro.
Converte os dados lidos de volta para o objeto original.

delete(int id)

Usa o índice direto para encontrar o endereço do registro.
Se o registro existir, escreve uma lápide (*) no arquivo, marcando-o como deletado.
Remove o ID do índice direto.

update(T objAlterado)

Busca o endereço do registro atual através do índice direto.
Compara o tamanho do novo objeto com o registro existente:
Se o novo for menor ou igual, reescreve no mesmo local.
Se for maior, marca o registro antigo como deletado (com lápide) e escreve o novo no final do arquivo.
Atualiza o índice direto com o novo endereço, se necessário.

clear()

Reseta o arquivo, removendo todos os registros, exceto o cabeçalho (último ID).

# CHECK-LIST

O trabalho possui um índice direto implementado com a tabela hash extensível?
- **SIM**
A operação de inclusão insere um novo registro no fim do arquivo e no índice e retorna o ID desse registro?
- **SIM**
A operação de busca retorna os dados do registro, após localizá-lo por meio do índice direto?
- **SIM**
A operação de alteração altera os dados do registro e trata corretamente as reduções e aumentos no espaço do registro?
- **SIM**
A operação de exclusão marca o registro como excluído e o remove do índice direto?
- **SIM**
O trabalho está funcionando corretamente?
- **SIM**
O trabalho está completo?
- **SIM**
O trabalho é original e não a cópia de um trabalho de outro grupo?
- **SIM**
