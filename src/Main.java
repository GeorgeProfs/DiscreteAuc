import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

final class Main {

    public static void main(String args[]) throws IOException {

        BufferedReader newInputData = new BufferedReader(new InputStreamReader(System.in));
        List<String> newStringInputData = new ArrayList<>();
        Map<Float, Integer> requestToBuy = new TreeMap<>();
        Map<Float, Integer> requestToSell = new TreeMap<>();
        int timeoutNoMoreThan = 100; // Last Input Lasts No More Than 10 Seconds
        long startTime = System.currentTimeMillis();

        while (newStringInputData.size() <= 2 && !newInputData.ready()) {
            while ((System.currentTimeMillis() - startTime) < timeoutNoMoreThan * 1000
                    && !newInputData.ready()) {

            }
            if (newInputData.ready()) {
                newStringInputData.add(newInputData.readLine());
                startTime = System.currentTimeMillis();
            } else break;
        }

        String[] strArray;
        String element;
        String result;
        int number2;
        float number1;
        char var;

        for (int i = 0; i < newStringInputData.size(); i++) {

            element = newStringInputData.get(i);

            System.out.println(element);

            strArray = element.split(" ");

            result = strArray[0];

            var = result.charAt(0);

            if (var == 'B') {

                number2 = Integer.parseInt(strArray[1]);
                number1 = Float.parseFloat(strArray[2]);

                if ((number1 >= 0.01 && number1 <= 100.00) && (number2 >= 1 && number2 <= 1000)) {
                    requestToBuy.put(number1, number2);
                }
            } else if (var == 'S') {

                number2 = Integer.parseInt(strArray[1]);
                number1 = Float.parseFloat(strArray[2]);
                if ((number1 >= 0.01 && number1 <= 100.00) && (number2 >= 1 && number2 <= 1000)) {
                    requestToSell.put(number1, number2);
                }
            }
        }
        newStringInputData.clear();

        float mapBuyKeyPrice;
        int mapBuyQuantity;
        float mapSellKeyPrice;
        int mapSellQuantity;

        for (Map.Entry mapBuy : requestToBuy.entrySet()) {
            //System.out.println(mapBuy.getKey() + " " + mapBuy.getValue());
            mapBuyKeyPrice = Float.parseFloat(mapBuy.getKey().toString());
            for (Map.Entry mapSell : requestToSell.entrySet()) {
                //    System.out.println(mapSell.getKey() + " " + mapSell.getValue());
                mapSellKeyPrice = Float.parseFloat(mapSell.getKey().toString());
                if (mapBuyKeyPrice >= mapSellKeyPrice) {
                    mapBuyQuantity = Integer.parseInt(mapBuy.getValue().toString());
                    mapSellQuantity = Integer.parseInt(mapSell.getValue().toString());
                    if (mapBuyQuantity - mapSellQuantity == 0) {
                        newStringInputData.add(mapSellQuantity + " " + mapSellKeyPrice);
                        requestToBuy.remove(mapBuyKeyPrice, mapBuyQuantity);
                        requestToSell.remove(mapSellKeyPrice, mapSellQuantity);
                        break;
                    } else if (mapBuyQuantity - mapSellQuantity < 0) {

                    } else {

                    }
                } else break;
            }
        }

        for (int i = 0; i < newStringInputData.size(); i++) {
            System.out.println(newStringInputData.get(i));
        }

    }
}