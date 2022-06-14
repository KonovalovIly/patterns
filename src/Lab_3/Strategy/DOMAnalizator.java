package Lab_3.Strategy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DOMAnalizator implements Analizator {

    @Override
    public void rewriteAverageScoreWithCorrectMark(String inputFileName, String outputFileName) {
        try {
            Student student = readStudent(inputFileName);
            student.calcAverage();
            writeStudent(student, outputFileName);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Student readStudent(String inputFileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder dbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = dbuilder.parse(inputFileName);

        NodeList list = document.getElementsByTagName("student");
        Element studentElement = (Element) list.item(0);
        NodeList subjectList = studentElement.getElementsByTagName("subject");

        int sum = 0;
        Subject[] subjects = new Subject[subjectList.getLength()];
        for (int i = 0; i < subjectList.getLength(); i++) {
            Node subject = subjectList.item(i);
            NamedNodeMap attributes = subject.getAttributes();
            String textMark = attributes.getNamedItem("mark").getTextContent();
            int mark = Integer.parseInt((textMark));
            if (mark < 1 || mark > 5) {
                System.out.println("Invalid mark! " + mark + " at " + i);
                return null;
            }
            sum += mark;
            subjects[i] = new Subject(mark, attributes.getNamedItem("title").getTextContent());
        }
        Student student = new Student(subjects);
        NodeList averageNodeList = studentElement.getElementsByTagName("average");
        if (averageNodeList.getLength() > 0) {
            student.setAverage(Double.parseDouble(averageNodeList.item(0).getTextContent()));
        }
        double avg = (double) sum / subjectList.getLength();

        if (student.getAverage() != null && student.getAverage() == avg) {
            System.out.println("Correct average");
        }else{
            student.setAverage(avg);
        }

        student.setLastname(studentElement.getAttributes().getNamedItem("lastname").getTextContent());
        return student;
    }

    public void writeStudent(Student student, String outputFileName) throws TransformerException, ParserConfigurationException, FileNotFoundException {
        DocumentBuilder dbuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document documentRepaired = dbuilder.newDocument();
        Element studentRepaired = documentRepaired.createElement("student");
        studentRepaired.setAttribute("lastname", student.getLastname());
        documentRepaired.appendChild(studentRepaired);

        for (Subject subject : student.getSubjects()) {
            Element subj = documentRepaired.createElement("subject");
            subj.setAttribute("title", subject.getTitle());
            subj.setAttribute("mark", String.valueOf(subject.getMark()));
            studentRepaired.appendChild(subj);
        }

        Element averageRepaired = documentRepaired.createElement("average");
        averageRepaired.setTextContent(String.valueOf(student.getAverage()));
        studentRepaired.appendChild(averageRepaired);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        DOMSource source = new DOMSource(documentRepaired);
        StreamResult result = new StreamResult(new FileOutputStream(outputFileName));
        transformer.transform(source, result);
        System.out.println("Done!");
    }

}
