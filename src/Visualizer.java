import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Visualizer extends JFrame {
    CandleDataStructure[] candles = new CandleDataStructure[10];
    float scalaStart  = (float) 23880.0;
    float scalaScale = 20;

    public Visualizer(){
        JPanel chartFrame = new JPanel();
        getContentPane().add(chartFrame);
        chartFrame.setSize(500,500);
        chartFrame.setVisible(true);

    }

    public void paint(Graphics g) {
        //Init Graph
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Line2D linY = new Line2D.Float(100, 50, 100, 550);
        Line2D linX = new Line2D.Float(100, 550, 600, 550);
        g2.draw(linY);
        g2.draw(linX);
        Line2D linz1 = new Line2D.Float(95, 550, 105, 550);
        Line2D linz2 = new Line2D.Float(600, 545, 600, 555);
        g2.draw(linz1);
        g2.draw(linz2);
        for(int i = 0; i < 10; i++){
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            rh.put(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);

            g2.setRenderingHints(rh);
            Line2D lin = new Line2D.Float(95, 50 + i*50, 105, 50 + i*50);
            g2.draw(lin);

            Line2D lin2 = new Line2D.Float(100 + i*50, 545, 100 + i*50, 555);
            g2.draw(lin2);

            g.drawString(String.valueOf(scalaStart + i*scalaScale), 45, 550- i*50);
        }
        //Build Graph
        for(int i = 0; i < candles.length; i++){
            //For every not null entry in Graph build it
            if(candles[i] != null){
                CandleDataStructure actCandle = candles[i];
                if (actCandle.minimumValue < scalaStart){
                    scalaStart =(float) actCandle.minimumValue - 3*scalaScale;
                    repaint();
                }
                if (actCandle.peakValue > scalaStart + 9 * scalaScale){
                    scalaStart =(float) actCandle.peakValue - 4*scalaScale;
                    repaint();
                }

                float xCoor = 150 + i * 50;
                float yCoorPeak = 500- (Math.abs(((float)actCandle.peakValue) - scalaStart - scalaScale)  / scalaScale) * 50;
                float yCoorMin = 500- (Math.abs(((float)actCandle.minimumValue) - scalaStart - scalaScale)  / scalaScale) * 50;
                float yStart = 500- (Math.abs(((float)actCandle.openingPrice) - scalaStart - scalaScale)  / scalaScale) * 50;
                float yEnd = 500- (Math.abs(((float)actCandle.closingPrice) - scalaStart - scalaScale)  / scalaScale) * 50;
                //DrawPeak
                Line2D peakLine = new Line2D.Float(xCoor-5, yCoorPeak, xCoor+5, yCoorPeak);
                g2.draw(peakLine);

                Line2D minLine = new Line2D.Float(xCoor-5, yCoorMin, xCoor+5, yCoorMin);
                g2.draw(minLine);

                //DrawRectangle
                float height = Math.abs(yStart - yEnd);
                float startRectY = 0;
                if(yStart > yEnd){
                    startRectY = yEnd;
                }else{
                    startRectY = yStart;
                }
                //DrawLines
                Line2D connectLine = new Line2D.Float(xCoor, yCoorPeak, xCoor, yCoorMin);
                g2.draw(connectLine);

                Rectangle2D rect = new Rectangle2D.Double(xCoor - 5, startRectY, 10, height);

                g2.draw(rect);
                Color c = new Color(0, 0, 0);
                if(actCandle.isGreen){
                    c = new Color(0, 255, 0);
                }else{
                    c = new Color(255, 0, 0);
                }
                g2.setPaint(c);
                g2.fill(rect);

                //add Timestamp
                g2.setPaint(new Color(0,0,0));
                g.drawString(String.valueOf(candles[i].candleStartPoint).substring(11, 16), 135 + i*50, 600);

            }
        }
    }

    public void addCandleToGraph(CandleDataStructure candle){
        //check if its full
        boolean isIn = false;
        for(int i = 0; i<candles.length; i++){
            if(candles[i] == null && !isIn){
                candles[i] = candle;
                isIn = true;
            }
        }
        if(!isIn){
            for(int i = 0; i<candles.length - 1; i++){
                candles[i] = candles[i+1];
            }
            candles[9] = candle;
        }

        repaint();
    }



}
