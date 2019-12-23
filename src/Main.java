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
        int timeoutNoMoreThan = 13; // Last Input Lasts No More Than 10 Seconds
        long startTime = System.currentTimeMillis();

        while (newStringInputData.size() <= 3 && !newInputData.ready()) {
            while ((System.currentTimeMillis() - startTime) < timeoutNoMoreThan * 1000
                    && !newInputData.ready()) {

            }
            if (newInputData.ready()) {
                newStringInputData.add(newInputData.readLine());
                startTime = System.currentTimeMillis();
            } else break;
        }

        String[] arrayOfUserInputInformation;
        String oneLineOfUserInput;
        int quantityInRequest;
        int oldQuantityInRequest;
        float priceInRequest;
        char typeOfRequest;

        for (int i = 0; i < newStringInputData.size(); i++) {

            oneLineOfUserInput = newStringInputData.get(i);

            arrayOfUserInputInformation = oneLineOfUserInput.split(" ");

            oneLineOfUserInput = arrayOfUserInputInformation[0];

            typeOfRequest = oneLineOfUserInput.charAt(0);

            if (typeOfRequest == 'B') {

                quantityInRequest = Integer.parseInt(arrayOfUserInputInformation[1]);
                priceInRequest = Float.parseFloat(arrayOfUserInputInformation[2]);

                if ((priceInRequest >= 0.01 && priceInRequest <= 100.00) && (quantityInRequest >= 1 && quantityInRequest <= 1000)) {
                    if (requestToBuy.containsKey(priceInRequest)) {
                        oldQuantityInRequest = Integer.parseInt(requestToBuy.get(priceInRequest).toString());
                        requestToBuy.replace(priceInRequest, oldQuantityInRequest + quantityInRequest);
                    } else requestToBuy.put(priceInRequest, quantityInRequest);
                }
            } else if (typeOfRequest == 'S') {

                quantityInRequest = Integer.parseInt(arrayOfUserInputInformation[1]);
                priceInRequest = Float.parseFloat(arrayOfUserInputInformation[2]);
                if ((priceInRequest >= 0.01 && priceInRequest <= 100.00) && (quantityInRequest >= 1 && quantityInRequest <= 1000)) {
                    if (requestToSell.containsKey(priceInRequest)) {
                        oldQuantityInRequest = Integer.parseInt(requestToSell.get(priceInRequest).toString());
                        requestToSell.replace(priceInRequest, oldQuantityInRequest + quantityInRequest);
                    } else requestToSell.put(priceInRequest, quantityInRequest);
                }
            }
        }
        newStringInputData.clear();

        float mapBuyKeyPrice;
        int mapBuyQuantity;
        float mapSellKeyPrice;
        int mapSellQuantity;

        for (Map.Entry<Float, Integer> mapBuy : requestToBuy.entrySet()) {
            mapBuyKeyPrice = mapBuy.getKey();
            for (Map.Entry<Float, Integer> mapSell : requestToSell.entrySet()) {
                mapSellKeyPrice = mapSell.getKey();
                if (mapBuyKeyPrice >= mapSellKeyPrice) {
                    mapBuyQuantity = mapBuy.getValue();
                    mapSellQuantity = mapSell.getValue();
                    if (mapBuyQuantity != 0 || mapSellQuantity != 0) {
                        if (mapBuyQuantity - mapSellQuantity == 0) {
                            newStringInputData.add(mapSellQuantity + " " + mapSellKeyPrice);
                            requestToBuy.replace(mapBuyKeyPrice, mapBuyQuantity, 0);
                            requestToSell.replace(mapSellKeyPrice, mapSellQuantity, 0);
                            break;
                        } else if (mapBuyQuantity - mapSellQuantity < 0) {
                            newStringInputData.add(mapBuyQuantity + " " + mapSellKeyPrice);
                            requestToBuy.replace(mapBuyKeyPrice, mapBuyQuantity, 0);
                            break;
                        } else if (mapBuyQuantity - mapSellQuantity > 0) {
                            newStringInputData.add(mapSellQuantity + " " + mapSellKeyPrice);
                            requestToBuy.replace(mapBuyKeyPrice, mapBuyQuantity, mapBuyQuantity - mapSellQuantity);
                            requestToSell.replace(mapSellKeyPrice, mapSellQuantity, 0);
                            break;
                        }
                    } else break;
                }
            }
        }

        if (!newStringInputData.isEmpty()) {
            for (int i = 0; i < newStringInputData.size(); i++) {
                System.out.println(newStringInputData.get(i));
            }
        } else System.out.println("0 n/a");

    }
}