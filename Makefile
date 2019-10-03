NAME = game
JC = javac
JFLAGS = -g

FILES = src/IOState.java \
		src/Scene.java \
		src/scenes/TestScene.java \
		src/App.java

CLASSES = $(FILES:%.java=%.class)

all: $(NAME)

%.class: %.java
	$(JC) $(JFLAGS) $?

$(NAME): $(CLASSES)
	@echo "Compilation done."

clean:
	rm $(CLASSES)

re: clean $(NAME)
