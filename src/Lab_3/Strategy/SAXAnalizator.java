package Lab_3.Strategy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class SAXAnalizator implements Analizator {

    @Override
    public void rewriteAverageScoreWithCorrectMark(String inputFileName, String outputFileName) {
        try {
            Student student = readStudent(inputFileName);
            student.calcAverage();
            writeStudent(student, outputFileName);
        } catch (ParserConfigurationException | SAXException | IOException | XMLStreamException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private Student readStudent(String inputFileName) throws ParserConfigurationException, IOException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        StudentHandler userHandler = new StudentHandler();
        saxParser.parse(inputFileName, userHandler);
        return userHandler.getStudent();
    }

    private void writeStudent(Student student, String outputFileName) throws XMLStreamException, IOException {
        String xmlString = null;
        try (StringWriter stringWriter = new StringWriter()) {
            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

            xMLStreamWriter.writeStartDocument("UTF-8", "1.0");

            xMLStreamWriter.writeStartElement("student");
            xMLStreamWriter.writeAttribute("lastname", student.getLastname());

            for (Subject subject : student.getSubjects()) {
                xMLStreamWriter.writeEmptyElement("subject");
                xMLStreamWriter.writeAttribute("title", subject.getTitle());
                xMLStreamWriter.writeAttribute("mark", String.valueOf(subject.getMark()));
            }

            xMLStreamWriter.writeStartElement("average");
            xMLStreamWriter.writeCharacters(String.valueOf(student.getAverage()));
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            xmlString = stringWriter.getBuffer().toString();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            assert xmlString != null;
            writer.write(xmlString);
        }
        System.out.println("Done!");
    }
}
