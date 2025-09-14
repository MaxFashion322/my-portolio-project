# Paint Calculator Bot

## Description

### ATTENTION!
- _**This is my first big project. I’m still learning how to structure and write code properly. Any suggestions, improvements, or advice are very welcome!**_

### What it does
- _**This is just a bot that calculates paint for you. Bot includes only multiplication.**_

### Note
- _**This program is not deployed on servers you will not be able to use on the network, but you can freely use my code for your needs.**_

![img.png](img.png)

## Features 
- Calculation wall on entered parameters.
- Consideration of windows and doors.
- Comfortable output of result. 
### Example
`Welcome to Paint Calculator bot!`

`This bot is for help with calculating paint for the house, use it in good health!`

`Let's start calculating!`

`Firstly, please enter the amount of rooms you would paint:`

1

`Room #1`  
`Enter the amount of walls you would paint:`

2

`Wall #1`  
`Enter the height of the wall:`

2.6

`Enter the length of the wall:`

3.5

`Enter the number of layers you will apply to the wall:`

2

`Enter the paint consumption(it is written on the back of the can):`

1.75

`Enter the number of holes on the wall:`

1

`Hole #1`  
`Enter the type: ('1' if circle / '2' if triangle / '3' if rectangle)`

3

`Enter the height of the rectangle:`

1.24

`Enter the length of the rectangle:`

0.6

`Wall #2`  
`Enter the height of the wall:`

2.6

`Enter the length of the wall:`

3.5

`Enter the number of layers you will apply to the wall:`

2

`Enter the paint consumption(it is written on the back of the can):`

1.75

`Enter the number of holes on the wall:`

0

`You entered 0 holes. Continuing wall calculation...`  
`Total paint needed: 64.0 liters.`

`use '/start' again, if you want to :).`

## Libraries
This project uses the library introduced on the [Telegram website](https://core.telegram.org/bots/samples#java).  
You can find more information about this library [here](https://github.com/rubenlagus/TelegramBots).

### How to import library?

1. Using Maven Central Repository
```xml
    <dependency>
        <groupId>org.telegram</groupId>
        <artifactId>telegrambots</artifactId>
        <version>6.9.7.1</version>
    </dependency>
```
2. Using Gradle:

```gradle
    implementation 'org.telegram:telegrambots:6.9.7.1'
```

3. Download the jar(including all dependencies) from [right here](https://mvnrepository.com/artifact/org.telegram/telegrambots/6.9.7.1)

Import the library *.jar* directly to your project. Depending on the IDE you are using, the process to add a library
is different, here is a video that may help with [Intellij](https://www.youtube.com/watch?v=NZaH4tjwMYg) or
[Eclipse](https://www.youtube.com/watch?v=VWnfHkBgO1I)

## Compile and Run

if this project interests you and you'd like to try it out:

1. clone the repository:

```
git clone https://github.com/MaxFashion322/paint-calculator-bot.git 
```

2. You must enter the token you received from the [BotFather](https://telegram.me/BotFather)  
   in the `Bot` class in the `getBotToken()` method.
3. You must enter the username of your bot without '@' in the `CalculatorBot` class in the `getBotUsername()` method.
4. Install [JDK 11](https://www.oracle.com/java/technologies/downloads) or later.
5. Install [Maven 3](https://maven.apache.org/download.cgi).
6. Build the project:

```shell
mvn clean install
```

Maven will read the `pom.xml` and download all dependencies automatically then gives you a jar file named
`calculator-telegram-bot-{version}-jar-with-dependencies.jar`

7. Run this jar file and enjoy:
```shell
java -jar paint-calculator-bot-{version}-jar-with-dependencies.jar
```

## Technical details

### Chapter 1. || Flow(`logic`) ||
_This is the main logic that shows how the bot works._  
_After this chapter you will find more detailed information about classes/methods._

1. User sends message to Telegram Bot.
2. Message gets into method `onUpdateReceived` class of **Bot**.  
2.1 `onUpdateReceived` is necessary because every time your bot receives any **information** it needs to be processed.
3. Inside this method we have another method that does everything it's `handleMessage()`.
4. The method calls(_I'll give small piece of information about each thing that it does_):  

**Every method will go almost one after the other**
- `startCommandReceived()` - is used to greet user when **'/start'** is given.  
**e.g.**  
_Welcome to Paint Calculator bot!_  
_This bot is for help with calculating paint for the house, use it in good health!_  


- `sendMessage()` - then you see different text because of this function.  
Basically `sendMessage` is `System.out.println()`, it allows you to send any text to the user, so it's very important.  
But you use it inside Telegram API, **not for console**!   
**e.g.**  
_Let's start calculating!_  
_Firstly, please enter the amount of rooms you would paint:_ 


- `isInputLegit()` - there is method that is included in every step (_I now realize that it'd require a whole class to check the input, but I'll improve it in the future._).
This is the second **important method** I got, because I knew that user couldn't just **break my app**, and it's a kind of check for the legitimacy of the message.
Well, for now the user still can destroy the program, if he writes just **_letters_**, but we will come back to this later in the **_Classes_** chapter.
```
    public boolean isInputLegit(Long userId, double value,
                                String negativeMsg, String zeroMsg,
                                boolean removeSession, boolean ifRoomIsZero,
                                boolean mustBeInteger, UserSession session) {
        if (value < 0){                                              
            sendMessage(userId, negativeMsg);
            if (removeSession) {
                sessions.remove(userId);
            }
            return false;
        }
        if (value == 0){
            sendMessage(userId, zeroMsg);
            if (removeSession) {
                sessions.remove(userId);
            }
            if(ifRoomIsZero){
                finishRoom(session, userId);
            }
            return false;
        }
        if (mustBeInteger && value % 1 != 0){
            sendMessage(userId, "Please enter a whole number!");
            return false;
        }
        return true;
    }
```
_Then we have three methods that execute the most work._
- `finishRoom()` - this method creates the **room** and calculates all the paint user needs.  
Inside the `if` statement we have a condition that checks if the **room index** is less than the amount of rooms.
And if everything is fine, then we continue to the last **room**, but if this is it, then we write a **whole** amount of paint.
```
    public void finishRoom(UserSession session, Long userId) {
        Room room = new Room(new ArrayList<>(session.currentRoomWalls));
        session.rooms.add(room);

        session.currentRoomIndex++;
        if (session.currentRoomIndex < session.roomQty) {
            session.currentWallIndex = 0;
            session.currentRoomWalls.clear();
            session.currentStep = "ASK_WALL_QTY";
            sendMessage(userId, "Room #" + (session.currentRoomIndex + 1)
                    + "\n" + "Enter the amount of walls you would paint: ");
        } else {
            double totalPaint = 0;
            for (Room rooms : session.rooms) {
                totalPaint += rooms.getTotalPaint();
            }
            sendMessage(userId, "Total paint needed: " + Math.ceil(totalPaint) + " liters." +
                    "\n" + "\n" + " use " + "'/start' again, if you want to :).");
        }
    }

```
`currentRoomIndex` - this counter is necessary to keep in mind, which step was it _(1 or 7596789578)._

`roomQty` - the number of rooms we got from the **user's** request.

- `finishWall()` - this method needs to calculate amount of walls very similar to `finishRoom()`, but _walls =/._  
There is interesting thing, if user writes '0' or rooms are finished, then we get completed **room.**
```    
public void finishWall(UserSession session, Long userId) {
        Wall wall = session.buildWall();
        session.currentRoomWalls.add(wall);
        session.currentWallIndex++;

        if (session.currentWallIndex < session.wallQty) {
            session.currentStep = "ASK_HEIGHT";
            sendMessage(userId, "Wall #" + (session.currentWallIndex + 1) + "\n" +
                    "Enter the height of the wall:");
        } else
            finishRoom(session, userId);

        // Clear data
        session.holes.clear();
        session.holeQty = 0;
        session.currentHoleIndex = 0;
    }
```
`currentWallIndex` and `wallQty` - work the same as described above with `currentRoomIndex`,`roomQty`.  
_For `finishHole` I used variables in the same way._

- `finishHole()` - yes, I named it _holes_, for example we have a window, and it's basically a **rectangular hole,** 
or maybe I just see things as holes...(_and I didn't want to have a lot of figures, you can fix it under you._)
```
    public void finishHole(UserSession session, Long userId) {
        session.currentHoleIndex++;
        if (session.currentHoleIndex < session.holeQty) {
            session.currentStep = "ASK_HOLE_TYPE";
            sendMessage(userId, "Enter the type of the #" + (session.currentHoleIndex + 1) +
                    " hole" + " ('1' if circle / '2' if triangle / '3' if rectangle):");
        } else
            finishWall(session, userId);
    }
```

- `parseInput()` - the last method for safely converting text to number. The thing is that you shouldn't 
**return** `null`, because you can't control it and this might be very bad for your application.
```
    public Optional<Double> parseInput(String text) {
        try {
            double value = Double.parseDouble(text);
            return Optional.of(value);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
```
Instead of returning `null`, `Optional` is used, which makes the result predictable:
- If the input is correct → `Optional.of(value)` is returned.
- If the input is invalid → `Optional.empty()` is returned.

_**And now you see how the logic works, now let's talk about `Classes`**._
 
### Chapter 2. || Classes ||
**_I prefer the same structure, which we used with methods, so I'll explain every class one
after the other._**

- `UserSession` - is used to spectate user's **current** step.
```
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
}
```
`buildWall()` - we need this method to build the wall taking into account each parameter.

- `Optional<Double>maybeValue` - So, remember what I said about `isInputLegit`? The thing is,  
with **letters**, now thanks to `Optional`we can control this moment. You can exclude the writing of letters!  
Like this:
```
Optional<Double>maybeValue = parseInput(text);
if (maybeValue.isEmpty()){
    sendMessage(userId, "Please enter valid number!");
    return;
}
double value = maybeValue.get();
```

- `HoleFactory` - this is abstract class we have special quantity of figures, so it's better to give  
them all at least one function `createHole`.
```
public abstract class HoleFactory {
    public abstract Hole createHole();
}
```

_Now we will go inside each factory and even more into figures!_

- `CircleFactory`, `TriangleFactory`, `RectangleFactory` - these classes are created using the **Factory Pattern.**  
I find it easier to create shapes by sticking to a structure. 
```
public class CircleFactory extends HoleFactory{
    private final double radius;

    public CircleFactory(double radius) {
        this.radius = radius;
    }

    @Override
    public Hole createHole() {
        return new Circle(radius);
    }
}

public class RectangleFactory extends HoleFactory{
    private final double height;
    private final double length;

    public RectangleFactory(double _height,double _length) {
        height = _height;
        length = _length;
    }

    public Hole createHole(){
        return new Rectangle(height, length);
    }
}

public class TriangleFactory extends HoleFactory{
    private final double base;
    private final double height;

    public TriangleFactory(double _base, double _height) {
        base = _base;
        height = _height;
    }

    public Hole createHole(){
        return new Triangle(base, height);
    }
}
```

- `Circle`, `Triangle`, `Rectangle` -  Due to previous `Factory` classes we can make such shapes with all  
parameters.

```
public class Circle extends Hole {
    private final double radius;

    public Circle(double _radius){
        radius=_radius;
    }

    @Override
    public double getArea(){
        return Math.PI * radius * radius;
    }
}

public class Rectangle extends Hole {
    private final double height;
    private final double length;

    public Rectangle(double _height,double _length){
        height=_height;
        length=_length;
    }

    @Override
    public double getArea(){
        return height*length;
    }
}

public class Triangle extends Hole{
    private final double base;
    private final double height;

    public Triangle(double _base,double _height){
        base = _base;
        height = _height;
    }

    @Override
    public double getArea(){
        return 0.5 * base * height;
    }
}
```

`getArea()` - this method provides a formula for each shape.

- `Hole` - abstract class that gives our descendants a method like `getArea()`.

```
public abstract class Hole {
    public abstract double getArea();
}
```

- `Wall` - inherits from Rectangle and describes one wall of the room. Stores 
the number of layers of paint (layers), paint consumption per m² (consumption) 
and a list of holes (holes) - windows, doors and other exceptions to the area.

```
public class Wall extends Rectangle {
    private final double layers;
    private final double consumption;
    private final List<Hole> holes;

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
```
`getTotalSq()` - method calculates the total area of the wall, excluding all holes.

`getTotalConsumption` - method returns how much paint is needed for this wall,
taking into account the area, layers and consumption.


- `Room` - combines several Wall objects into a single entity. Stores a list of walls.

```
public class Room {
    public ArrayList<Wall> walls = new ArrayList<>();

    public Room(ArrayList<Wall> _walls) {
        walls=_walls;
    }

    public Room(){}

    public double getTotalPaint(){
        double total=0;
        for(Wall w:walls){
            total+=w.getTotalConsumption();
        }
        return total;
    }
}
```

`getTotalPaint()` - method sums up the paint consumption for all the walls of 
the room, giving the total result for the room.

- `Main` - just to start everything. In principle, you don't need it, but I used. 

```
public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();
        botsApi.registerBot(bot);
    }
}
```
_This is it with classes, now I wanted to conclude my project._

### Chapter 3. || Conclusion ||
#### _**For now, I've been doing this project for couple of months and it was tough.**_
#### _**While making, I noticed plenty of code that could've been improved. I'll make**_
#### _**an update in the future plan to improve the input validation, refactor**_
#### _**some parts of the logic, but for now it's going to be offline, because I'd like**_
#### _**to try something new.**_

## License summary

MIT License

Copyright (c) 2025 Maxim Golostsuk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
