package Lab_3.Strategy;

public class Lab_3_7 {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Передайте имена вх. и вых. файлов и попробуйте еще раз!");
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        //DOM
        AnalizatorContext context = new AnalizatorContext();
        context.executeStrategy(inputFileName, outputFileName);

        //SAX
        context.setAnalizatorStrategy(new SAXAnalizator());
        //context.executeStrategy(inputFileName, outputFileName);
    }
}
