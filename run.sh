echo -e "\033[?25l"
stty raw -echo
javac -cp QuadEngine.jar:. *.java
java -cp QuadEngine.jar:. PrimitiveType
java -cp QuadEngine.jar:. Player
stty cooked echo
echo -e "\033[?25h"