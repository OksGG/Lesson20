package gusakova;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

public class StreamAPITest {

    public static void main(String[] args) throws IOException {

        FileInputStream fstream = new FileInputStream("C:\\Users\\ZenBook 13\\Downloads\\products.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        int count = 0;
        String lastProduct = "";
        double lastCount = 0;
        double lastPrice = 0;
        double totalPrice = 0;
        StringBuilder stringBuilder = new StringBuilder();
        List<String> listProduct = new ArrayList<String>();
        List<Double> listPrice = new ArrayList<Double>();

        while ((strLine = br.readLine()) != null) {
            switch (count) {
                case 0:
                    lastProduct = strLine;
                    listProduct.add(strLine);
                    break;
                case 1:
                    lastCount = Double.parseDouble(strLine);

                    break;
                case 2:
                    lastPrice = Double.parseDouble(strLine);
                    listPrice.add(Double.parseDouble(strLine));
            }
            count++;
            if (count == 3) {
                double calculatedPrice = lastPrice * lastCount;
                totalPrice += calculatedPrice;
                String productLine = lastProduct + " " + lastCount + " x " + lastPrice + " =" + String.format("%.2f", calculatedPrice);
                stringBuilder.append("\n");
                stringBuilder.append(productLine);
                count = 0;
            }
        }
        stringBuilder.append("\n===================");
        stringBuilder.append("\nИтого:              " + String.format("%.2f", totalPrice));
        System.out.println(stringBuilder.toString());
        System.out.println(listProduct);
        Stream stream = listProduct.stream();
        System.out.println(stream.count());
        Optional<Double> min = listPrice.stream().min(Double::compareTo);
        System.out.println(min);
        System.out.println(listPrice.stream().mapToInt((s) -> (int) Double.parseDouble(String.valueOf((s)))).average());

    }
}
