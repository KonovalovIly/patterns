package Lab_3.Observer;

public class NoseObserver implements FaceObserver {

    private final FaceSubject face;

    public NoseObserver(FaceSubject face) {
        this.face = face;
    }

    @Override
    public void update(Feature f) {
        if (f == Feature.NOSE) {
            face.updateNose(!face.isNoseColored());
        }
    }

}
