package application;

import entities.Produto;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Produto[] produtos = new Produto[3];

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o diretório: ");

        String strPath = sc.nextLine();

        File path = new File(strPath);


        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line;
            int n = 0;


            while ((line = br.readLine()) != null) {
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(",");


                String name = scanner.next();
                double value = scanner.nextDouble();
                int quantity = scanner.nextInt();


                produtos[n] = new Produto(name, value, quantity);
                ;


                n++;

                if (n == produtos.length) {
                    break;
                }

                scanner.close();
            }


            for (Produto p : produtos) {
                System.out.println(p.toString());
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
