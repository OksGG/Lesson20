package gusakova;

import java.io.*;
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
        HashMap<String, Double> listProduct = new HashMap<>();
        List<Double> listPrice = new ArrayList<Double>();
        while ((strLine = br.readLine()) != null) {
            switch (count) {
                case 0:
                    lastProduct = strLine;
                    break;
                case 1:
                    lastCount = Double.parseDouble(strLine);

                    break;
                case 2:
                    lastPrice = Double.parseDouble(strLine);
                    listPrice.add(Double.parseDouble(strLine));
                    listProduct.put(lastProduct, lastPrice);
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
        Map<String, Double> collect = listProduct.entrySet().stream()
                .filter(x -> x.getValue() >= 200.00)
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
        System.out.println("Фильтр по цене" + collect);
        Stream stream = collect.entrySet().stream();
        System.out.println("Всего товаров с фильтом по цене: " + stream.count());
        Map.Entry<String, Double> min = Collections.min(listProduct.entrySet(),
                Comparator.comparing(Map.Entry::getValue));
        System.out.println("Продукт с минимальной ценой: " + min);
        System.out.println(listPrice);
        double average = listPrice.stream().mapToDouble(a -> a).average().orElse(0);
        System.out.println("Средняя цена товаров: " + average);


    }

}
