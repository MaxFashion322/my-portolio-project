package org.example;

import java.util.ArrayList;
import java.util.List;

public class UserSession {
    public String currentStep = "ASK_ROOM_QTY";
    public double roomQty;
    public double wallQty;
    public int currentRoomIndex;
    public int currentWallIndex;
    public List<Room> rooms = new ArrayList<>();
    public List<Wall> currentRoomWalls = new ArrayList<>();

    public double height;
    public double length;
    public double layers;
    public double consumption;
    public double holeQty;
    public int currentHoleIndex = 0;
    public double tempParam1;
    public double tempParam2;
    public List<Hole> holes = new ArrayList<>();

    public Wall buildWall(){
        return new Wall(height,length,layers,consumption,holes);
    }

    public Room buildRoom(){
        return new Room(new ArrayList<>(currentRoomWalls));
    }



//    public enum Step {
//        Ask_Room_Count,
//        Ask_Wall_Count,
//        Ask_Height,
//        Ask_Width,
//        Ask_Layers,
//        Ask_Consumption,
//        Ask_Holes,
//        Ask_Hole_Area,
//        Finished
//    }
}
