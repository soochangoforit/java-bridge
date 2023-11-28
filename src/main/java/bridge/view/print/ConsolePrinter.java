package bridge.view.print;

public class ConsolePrinter implements Printer {
    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printEmptyLine() {
        System.out.println();
    }
}
