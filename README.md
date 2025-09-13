# Paint Calculator Bot

## Description

### ATTENTION! This is my first big project. I’m still learning how to structure and write code properly. Any suggestions, improvements, or advice are very welcome!

### This is just a bot that calculates paint for you. Bot includes only multiplication.

### This program is not deployed on servers you will not be able to use on the network, but you can freely use my code for your needs.

![img.png](img.png)

## Possibilities 
- Calculation wall on entered parameters.
- Consideration of windows and doors.
- Comfortable output of result. 
### Example:
Welcome to Paint Calculator bot!

This bot is for help with calculating paint for the house, use it in good health!

Let's start calculate!

Firstly, please enter the amount of rooms you would paint:

1

Room #1  
Enter the amount of walls you would paint:

2

Wall #1  
Enter the height of the wall:

2.6

Enter the length of the wall:

3.5

Enter the number of layers you will apply to the wall:

2

Enter the paint consumption(it is written on the back of the can):

1.75

Enter the number of holes on the wall:

1

Hole #1  
Enter the type: ('1' if circle / '2' if triangle / '3' if rectangle)

3

Enter the height of the rectangle:

1.24

Enter the length of the rectangle:

0.6

Wall #2  
Enter the height of the wall:

2.6

Enter the length of the wall:

3.5

Enter the number of layers you will apply to the wall:

2

Enter the paint consumption(it is written on the back of the can):

1.75

Enter the number of holes on the wall:

0

You entered 0 holes. Continuing wall calculation...

Total paint needed: 64.0 liters.

use '/start' again, if you want to :).

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

if this project interests you, and you would like to see for yourself how it works,

1. clone the project:

```
git clone // допишу ещё 
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
`calculator-telegram-bot-{version}-jar-with-dependencies.jar` // `ЗДЕСЬ ТОЖЕ ДОПИСАТЬ НАДО`

7. Run this jar file and enjoy:
```shell
java -jar calculator-telegram-bot-{version}-jar-with-dependencies.jar `ЗДЕСЬ ТОЖЕ ДОПИСАТЬ НАДО`
```

## Technical details


## License summary

MIT License

Copyright (c) 2022 Maxim Golostsuk

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
