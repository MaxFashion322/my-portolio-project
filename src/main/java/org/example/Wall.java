package org.example;
import java.util.List;

public class Wall extends Rectangle {
    public double layers;
    public double consumption;
    public List<Hole> holes;

    public Wall(double _height, double _length, double _layers, double _consumption, List<Hole> _holes) {
        super(_height,_length);
        layers=_layers;
        consumption=_consumption;
        holes = _holes;
    }

    public double getTotalSq(){
        double total = getArea();
        for(Hole hole:holes){
            total-=hole.getArea();
        }
        return Math.max(0,total);
    }

    public double getTotalConsumption(){
        return getTotalSq() * layers * consumption;
    }
}

