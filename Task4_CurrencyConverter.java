import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Task4_CurrencyConverter {
   
    public static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String apiKey = "YOUR_API_KEY";
        String url = "https://v6.exchangerate-api.com/v6/b83a5da163dacbb8ff675969/latest/" + baseCurrency;
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

      
        String jsonResp = response.toString();
        double exchangeRate = parseExchangeRate(jsonResp, targetCurrency);

        return exchangeRate;
    }

   
    public static double parseExchangeRate(String jsonResp, String targetCurrency) {
        
        String[] parts = jsonResp.split("\"" + targetCurrency + "\":");
        if (parts.length > 1) {
            String valueStr = parts[1].split(",")[0];
            return Double.parseDouble(valueStr);
        } else {
            System.out.println("Exchange rate for target currency not found in response.");
            return -1; 
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

           
            System.out.print("Enter the base currency: ");
            String baseCurrency = reader.readLine().toUpperCase();

            // Input target currency
            System.out.print("Enter the target currency: ");
            String targetCurrency = reader.readLine().toUpperCase();

        
            System.out.print("Enter the amount to convert: ");
            double amount = Double.parseDouble(reader.readLine());

           
            double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

            if (exchangeRate != -1) {
              
                double convertedAmount = amount * exchangeRate;

               
                System.out.printf("%.2f %s equals %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

