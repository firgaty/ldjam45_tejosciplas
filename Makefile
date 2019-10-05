NAME = game
JC = javac
JFLAGS = -g

FILES = src/Scene.java \
		src/GameObject.java \
		src/Camera.java \
		src/SpriteBuilder.java \
		src/Animation.java \
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
