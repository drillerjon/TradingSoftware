import java.sql.Timestamp;

public class Application {

    public static void main(String[] args) {
        Visualizer v = new Visualizer();
        v.setSize(700,700);
        v.setVisible(true);
        /*
        long start = System.currentTimeMillis();
        System.out.println("StartTime " + System.currentTimeMillis());
        CandleDataStructure candle = new CandleDataStructure();
        System.out.println(candle.openingPrice);
        System.out.println(candle.closingPrice);
        System.out.println(candle.candleStartPoint);
        System.out.println(candle.candleEndPoint);
        System.out.println(candle.minimumValue);
        System.out.println(candle.peakValue);
        System.out.println("EndTime " + System.currentTimeMillis());
        long end = System.currentTimeMillis();
        System.out.println("TimeDiff " + (end - start));
        */
        //CandleDataStructure candle = new CandleDataStructure(new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),23960,23940,23980,23920.0);
        //v.addCandleToGraph(candle);
        while (true){
            System.out.println("Add new Candle");
            CandleDataStructure candle = new CandleDataStructure();
            v.addCandleToGraph(candle);
            System.out.println("Following candle added");
            System.out.println("-------------------------------");
            System.out.println("OpeningPrice: " + candle.openingPrice);
            System.out.println("ClosingPrice: " +candle.closingPrice);
            System.out.println("MinPrice: " +candle.minimumValue);
            System.out.println("PeakPrice: " +candle.peakValue);
            System.out.println("-------------------------------");
        }



    }
}
