JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	SearchType.java \
	Node.java \
	BreadthFirst.java \
	DepthFirst.java \
	DepthLimited.java \
	GreedyBestFit.java \
	Astar.java \
	FifteenProblem.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
