package Lab_3.Strategy;

public class AnalizatorContext {

    private Analizator strategy = new DOMAnalizator();

    public void setAnalizatorStrategy(Analizator strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(String inputFileName, String outputFileName) {
        strategy.rewriteAverageScoreWithCorrectMark(inputFileName, outputFileName);
    }

}
