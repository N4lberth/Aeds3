# **Relatório TP4**

### **Alunos:**
- Guilherme Gomes de Brites  
- Nalberth Henrique Vieira  
- Mateus Nunes Bello  
- João Lucas Curi  

---

## **1. Classe VetorDeBits**

A classe `VetorDeBits` é uma abstração para manipulação de bits utilizando a classe `BitSet` da biblioteca Java. Seu objetivo principal é fornecer uma estrutura flexível para operações bit a bit, incluindo conversões para arrays de bytes e manipulações básicas como setar, limpar e acessar bits.

### **Atributos**:
- **`vetor`**: Instância de `BitSet` que armazena os bits.

### **Construtores**:
- **`VetorDeBits()`**: Inicializa um `BitSet` vazio e seta o bit 0.
- **`VetorDeBits(int n)`**: Inicializa um `BitSet` de tamanho `n` e seta o bit `n`.
- **`VetorDeBits(byte[] v)`**: Constrói um `BitSet` a partir de um array de bytes.

### **Métodos**:
- **`toByteArray`**: Converte o `BitSet` em um array de bytes.
- **`set(int i)`**: Seta o bit `i` e ajusta o tamanho do `BitSet`, se necessário.
- **`clear(int i)`**: Limpa o bit `i` e ajusta o tamanho do `BitSet`, se necessário.
- **`get(int i)`**: Retorna o estado (true/false) do bit na posição `i`.
- **`length`**: Retorna o tamanho efetivo do vetor de bits.
- **`size`**: Retorna a capacidade atual do vetor.
- **`toString`**: Retorna uma representação em string dos bits do vetor.

---

## **2. Classe Backup**

A classe `Backup` implementa uma solução robusta para backup e restauração de arquivos, utilizando compressão LZW para reduzir o espaço ocupado no armazenamento.

### **Atributos**:
- **Diretórios**:
  - `DIRETORIO_BACKUP`: Caminho para armazenar backups.
  - `DIRETORIO_DADOS`: Caminho para os arquivos de dados.
- **Constantes de estilo**:
  - `VERMELHO` e `RESETAR`: Códigos ANSI para mensagens coloridas no terminal.

### **Métodos**:
- **Diretórios**:
  - `criarDiretorio(String caminho)`: Cria um diretório no caminho especificado.
- **Serialização e desserialização**:
  - `serializarArquivos(File[] arquivos)`: Serializa os arquivos fornecidos em um array de bytes.
  - `lerArquivo(File arquivo)`: Lê o conteúdo de um arquivo em um array de bytes.
  - `escreverArquivo(String caminho, byte[] conteudo)`: Escreve um array de bytes em um arquivo.
- **Backup e restauração**:
  - `criarBackup(String arquivoBackup)`: Cria um backup comprimido dos arquivos do diretório de dados.
  - `restaurarBackup(String arquivoBackup)`: Restaura os arquivos de um backup para o diretório de dados.
- **Utilitários**:
  - `calcularTaxaCompressao(byte[] dadosOriginais, byte[] dadosComprimidos)`: Calcula a eficiência da compressão.
  - `listarBackups()`: Lista os backups disponíveis.

---

## **Integração entre as classes**

1. **`VetorDeBits`**:
   - Fornece manipulação de bits para compactar/descompactar os dados.
2. **`Backup`**:
   - Gerencia os arquivos, utilizando o método `LZW.codifica` para comprimir os backups e `LZW.decodifica` para restaurá-los.
3. **`LZW`**:
   - Executa a lógica de compressão e descompressão.

### **Eficiência**
A compressão LZW integrada ao sistema mostrou-se eficaz na redução do espaço de armazenamento, com a classe `Backup` apresentando mensagens claras sobre as taxas de compressão calculadas.

---

Há uma rotina de compactação usando o algoritmo LZW para fazer backup dos arquivos? SIM
Há uma rotina de descompactação usando o algoritmo LZW para recuperação dos arquivos? SIM
O usuário pode escolher a versão a recuperar? SIM
Qual foi a taxa de compressão alcançada por esse backup? (Compare o tamanho dos arquivos compactados com os arquivos originais)
O trabalho está funcionando corretamente? SIM
O trabalho está completo? SIM
O trabalho é original e não a cópia de um trabalho de um colega? SIM

---
