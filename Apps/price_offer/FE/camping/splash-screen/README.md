# Splash screen

### Concept
The main idea is very simple.<br>
* Generate the splash screen
* Run the code you wish to run "behind the scenes".
* Once it is done, dispose the frame.
<br><br>

### Usage
```
AppSplashScreen appSplashScreen = new AppSplashScreen();

JFrame fSplash = appSplashScreen.generate();

fSplash.setVisible(true);
```
>Your code here ...
```
fSplash.dispose();
```

<br><br>

##### Author
Yaron Golan, yargolan@gmail.com