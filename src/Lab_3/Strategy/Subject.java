package Lab_3.Strategy;

public class Subject {
    private String title;
    private int mark;

    public Subject() {
        title = "Philosophy";
        mark = 5;
    }

    public Subject(int mark, String title) {
        this.mark = mark;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getTitle() {
        return title;
    }

    public int getMark() {
        return mark;
    }
}
