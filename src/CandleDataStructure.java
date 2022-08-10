import java.sql.Timestamp;
import java.util.Arrays;

//Data structure for the typical trading candle
public class CandleDataStructure {
    Timestamp candleStartPoint;
    Timestamp candleEndPoint;
    double openingPrice;
    double closingPrice;
    double peakValue;
    double minimumValue;
    boolean isGreen;
    long timeSpan;

    public CandleDataStructure(Timestamp candleStartPoint, Timestamp candleEndPoint, double openingPrice, double closingPrice, double peakValue, double minimumValue) {
        this.candleStartPoint = candleStartPoint;
        this.candleEndPoint = candleEndPoint;
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.peakValue = peakValue;
        this.minimumValue = minimumValue;
        this.isGreen = closingPrice > openingPrice;
        this.timeSpan = candleEndPoint.getTime() - candleStartPoint.getTime();
    }
    public CandleDataStructure(){
        CandleDataStructure cs = createOneMinuteCandle();
        this.candleStartPoint = cs.candleStartPoint;
        this.candleEndPoint = cs.candleEndPoint;
        this.openingPrice = cs.openingPrice;
        this.closingPrice = cs.closingPrice;
        this.peakValue = cs.peakValue;
        this.minimumValue = cs.minimumValue;
        this.isGreen = this.closingPrice > this.openingPrice;
        this.timeSpan = this.candleEndPoint.getTime() - this.candleStartPoint.getTime();
    }

    public Timestamp getCandleStartPoint() {
        return candleStartPoint;
    }

    public Timestamp getCandleEndPoint() {
        return candleEndPoint;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public double getPeakValue() {
        return peakValue;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public boolean isGreen() {
        return isGreen;
    }


    //Return a Candle with a timeSpan of one minute from the present time
    public CandleDataStructure createOneMinuteCandle(){
        DataFill df = new DataFill();
        Timestamp start = new Timestamp(System.currentTimeMillis());
        double[] ValueArray = new double[60];
        try{
            System.out.println("Val 0");
            double getOpeningPrice = df.getActBitcoinPrice();
            ValueArray[0] = getOpeningPrice;
            for(int i = 1; i < 59; i++){
                Thread.sleep(1000);
                System.out.println("Val " + i);
                ValueArray[i] = df.getActBitcoinPrice();
            }
            Thread.sleep(1000);
            System.out.println("Val 59");
            double getClosingPrice = df.getActBitcoinPrice();
            ValueArray[59] = getClosingPrice;
            double PeakValue = Arrays.stream(ValueArray).max().getAsDouble();
            double MinimumValue = Arrays.stream(ValueArray).min().getAsDouble();
            Timestamp end = new Timestamp(System.currentTimeMillis());
            CandleDataStructure candle = new CandleDataStructure(
                    start, end, ValueArray[0], ValueArray[59], PeakValue, MinimumValue
            );
            return candle;

        }catch (Exception o){
            return null;
        }



    }
}
