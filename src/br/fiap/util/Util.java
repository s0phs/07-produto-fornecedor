package br.fiap.util;

import br.fiap.fornecedor.Fornecedor;
import br.fiap.produto.Produto;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {

    private Fornecedor[] fornecedor = new Fornecedor[200];
    private Produto[] produto = new Produto[200];
    private int indexProd = 0;
    private int indexForn = 0;

    public void menuPrincipal(){
        int opcao = 0;
        String menu = "1. Cadastrar produto\n2. Pesquisar produto por nome\n" +
                       "3. Pesquisar fornecedor por CNPJ\n4. Finalizar";

        while(true){
            opcao = parseInt((showInputDialog(menu)));

            switch (opcao){
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    pesquisarProduto();
                    break;
                case 3:
                    pesquisarFornecedor();
                    break;
                case 4:
                    return;//para parar um método e retornar para o Main
                default:
                    showMessageDialog(null,"Opção Inválida");
            }
        }
    }

    private void cadastrarProduto(){
        String nome;
        double valor;
        int quantEstoque;
        Fornecedor fornecedor = pesquisarFornecedor();

        if (fornecedor == null){
            fornecedor = cadastraFornecedor();
        }

        nome = showInputDialog("Nome do produto: ");
        quantEstoque = parseInt(showInputDialog("Quantidade em estoque: "));
        valor = parseDouble(showInputDialog("Valor unitário: "));

        produto[indexProd++] = new Produto(nome, valor, quantEstoque, fornecedor);
    }

    private void pesquisarProduto() {
        String aux = "Produto não encontrado";
        String nome = showInputDialog("Nome do Produto: ");
        for (int i = 0; i < indexProd; i++) {
            if (produto[i].getNome().equalsIgnoreCase(nome)) {
                aux = "";
                aux += "Nome do produto: " + nome + "\n";
                aux += "Valor unitário: " + produto[i].getValor() + "\n";
                aux += "Fornecedor: " + produto[i].getFornecedor().getNome() + "\n";
            }

        }
        showMessageDialog(null,aux);
    }

    private Fornecedor pesquisarFornecedor(){
        long cnpj = parseLong(showInputDialog("CNPJ do Fornecedor: "));
        for(int i = 0;i < indexForn;i++){
            if(fornecedor[i].getCnpj() == cnpj){
               return fornecedor[i];
            }
        }
        showMessageDialog(null, "CNPJ não cadastrado");
        return null;
    }

    private Fornecedor cadastraFornecedor(){

        long cnpj = parseLong(showInputDialog("CNPJ do fornecedor: "));
        String nome = showInputDialog("Nome: ");
        fornecedor[indexForn] = new Fornecedor(nome, cnpj);
        indexForn++;
        return fornecedor[indexForn-1];//precisa tirar para retornar certo a posicão do objeto
    }

}
