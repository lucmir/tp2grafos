JFLAGS = -d ./bin

JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = BFSearcher.java  CommunitySearcher.java  Edge.java  FloydWarshallSearcher.java  Graph.java  IGraphSearcher.java  Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class *~
	$(RM) ./bin/*.class ./bin/*~
