package application;

import entities.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Produto> list = new ArrayList();


        System.out.println("Digite o diretório: ");

        String strPath = sc.nextLine();

        File path = new File(strPath);

        String strFolder = path.getParent(); // Esqueci de criar a pasta out

        boolean success = new File(strFolder + "\\out").mkdir();
        System.out.println("Caminho da pasta: " + strFolder);

        System.out.println("Folder created: " + success);

        String targetFileStr = strFolder + "\\out\\summary.scv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();
            while (line != null) {

                String[] fields = line.split(",");
                String name = fields[0];
                double price = Double.parseDouble(fields[1]); // Converteu a string do arquivo csv pra double
                int quantity = Integer.parseInt(fields[2]); // E aqui converteu pra int

                list.add(new Produto(name, price, quantity));

                line = br.readLine(); // Vai ler e verificar se a outra linha é != null

            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) // Vai criar o arquivo out nesse caminho
            { for (Produto item : list) {
                bw.write(item.toString());
                bw.newLine();
            }
                System.out.println( targetFileStr + " Creadted");

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
