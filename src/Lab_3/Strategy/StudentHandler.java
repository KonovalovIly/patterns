package Lab_3.Strategy;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class StudentHandler extends DefaultHandler {

    private Student student;
    private final LinkedList<Subject> subjects = new LinkedList<>();

    boolean isAverage = false;

    public Student getStudent() {
        Subject[] subjectsArray = new Subject[subjects.size()];
        subjects.toArray(subjectsArray);
        student.setSubjects(subjectsArray);
        return student;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("student")) {
            student = new Student();
            student.setLastname(attributes.getValue("lastname"));
        } else if (qName.equalsIgnoreCase("subject")) {
            Subject subject = new Subject();
            subject.setTitle(attributes.getValue("title"));
            subject.setMark(Integer.parseInt(attributes.getValue("mark")));
            subjects.add(subject);
        } else if (qName.equalsIgnoreCase("average")) {
            isAverage = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        if (isAverage) {
            String avg = new String(ch, start, length);
            student.setAverage(Double.parseDouble(avg));
            isAverage = false;
        }
    }
}

