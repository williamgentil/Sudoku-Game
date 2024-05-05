### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

first : Main.class  

Main.class : Main.java SudokuGame.class
	${JC} ${JCFLAGS} Main.java 

SudokuGame.class : SudokuGame.java SudokuBoard.class SudokuPanel.class SudokuTimer.class LoadGrid.class SaveGrid.class
	${JC} ${JCFLAGS} SudokuGame.java

SudokuBoard.class : SudokuBoard.java
	${JC} ${JCFLAGS} SudokuBoard.java

SudokuPanel.class : SudokuPanel.java 
	${JC} ${JCFLAGS} SudokuPanel.java

LoadGrid.class : LoadGrid.java SudokuPanel.class
	${JC} ${JCFLAGS} LoadGrid.java

SaveGrid.class : SaveGrid.java SudokuPanel.class SudokuBoard.class
	${JC} ${JCFLAGS} SaveGrid.java

SudokuTimer.class : SudokuTimer.java TimerActionListener.class
	${JC} ${JCFLAGS} SudokuTimer.java

TimerActionListener.class : TimerActionListener.java 
	${JC} ${JCFLAGS} TimerActionListener.java

SudokuPanelMouseListener.class : SudokuPanelMouseListener.java SudokuPanel.class
	${JC} ${JCFLAGS} SudokuPanelMouseListener.java

SudokuPanel2MouseListener.class : SudokuPanel2MouseListener.java SudokuPanel2.class
	${JC} ${JCFLAGS} SudokuPanel2MouseListener.java


Main.class : SudokuPanelMouseListener.class

second : Main2.class

Main2.class : Main2.java SudokuGame2.class
	${JC} ${JCFLAGS} Main2.java 

SudokuGame2.class : SudokuGame2.java SudokuBoard2.class SudokuPanel2.class SudokuTimer.class LoadGrid2.class
	${JC} ${JCFLAGS} SudokuGame2.java

SudokuBoard2.class : SudokuBoard2.java
	${JC} ${JCFLAGS} SudokuBoard2.java

SudokuPanel2.class : SudokuPanel2.java 
	${JC} ${JCFLAGS} SudokuPanel2.java

LoadGrid2.class : LoadGrid2.java SudokuPanel2.class
	${JC} ${JCFLAGS} LoadGrid2.java

SudokuTimer.class : SudokuTimer.java TimerActionListener.class
	${JC} ${JCFLAGS} SudokuTimer.java

TimerActionListener.class : TimerActionListener.java 
	${JC} ${JCFLAGS} TimerActionListener.java

Main2.class : SudokuPanel2MouseListener.class
### REGLES OPTIONNELLES ###

run1 : Main.class
	${JVM} ${JVMFLAGS} Main

run2 : Main2.class
	${JVM} ${JVMFLAGS} Main2

clean :
	-rm -f *.class

mrproper : clean Main.class

all : first second

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###
