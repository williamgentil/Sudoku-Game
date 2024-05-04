### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

first : Main.class  

Main.class : Main.java Grille.class
	${JC} ${JCFLAGS} Main.java 

SudokuFileWriter.class : SudokuFileWriter.java SudokuBoard.class
	${JC} ${JCFLAGS} SudokuFileWriter.java

SudokuFileReader.class : SudokuFileReader.java SudokuBoard.class
	${JC} ${JCFLAGS} SudokuFileReader.java

SudokuBoard.class : SudokuBoard.java
	${JC} ${JCFLAGS} SudokuBoard.java

SudokuPanel.class : SudokuPanel.java SudokuBoard.class Grille.class LoadFromFilePanel.class
	${JC} ${JCFLAGS} SudokuPanel.java

Grille.class : Grille.java 
	${JC} ${JCFLAGS} Grille.java

EvenementSouris.class : EvenementSouris.java SudokuPanel.class
	${JC} ${JCFLAGS} EvenementSouris.java

LoadFromFilePanel.class : LoadFromFilePanel.java SudokuPanel.class
	${JC} ${JCFLAGS} LoadFromFilePanel.java


second : 

### REGLES OPTIONNELLES ###

run : Main.class
	${JVM} ${JVMFLAGS} Main

clean :
	-rm -f *.class

mrproper : clean Main.class

all : first second

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###
