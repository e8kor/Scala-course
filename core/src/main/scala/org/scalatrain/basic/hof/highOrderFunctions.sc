// Command Pattern
/*
public interface Command {
  void execute();
}
public class MyCommand implements Command {
  public void execute() {
    System.out.println("MyCommand");
  }
}
public void doCommand(Command command) { command.execute(); }
 */
def doCommand(f: () => Unit) = f
doCommand(() => println("MyCommand"))

// Strategy Pattern
def filter(ints: Array[Int], p: Int => Boolean) =
  for (i <- ints) if (p(i)) println(s"Filter $i")

val array = Array(1, 2, 3)

filter(array, (i: Int) => i > 2)
filter(array, (i) => i > 2)
filter(array, i => i > 2)
filter(array, _ > 2)

// Factory, Converter
def createFactory: Int => String = (i) => i.toString