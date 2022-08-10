import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class DataFill {

    public DataFill() {
    }

    public double getActBitcoinPrice() throws IOException {

        // https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT end Point for live Price
        URL url = new URL("https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT");
        URLConnection yc = url.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()
                )
        );
        String inputLine;
        String value = "";
        while((inputLine = in.readLine()) != null){
            String separator = "price";
            int sepPos = inputLine.indexOf(separator);
            value = inputLine.substring(sepPos + separator.length()+3).replace("\"}","");
        }
        in.close();
        double bitcoinValue = Double.parseDouble(value);
        return bitcoinValue;
    }
}
