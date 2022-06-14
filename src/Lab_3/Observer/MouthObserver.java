package Lab_3.Observer;

public class MouthObserver implements FaceObserver {

    private final FaceSubject face;
    
    public MouthObserver(FaceSubject face) {
        this.face = face;
    }
    
    @Override
    public void update(Feature f) {
        if (f == Feature.MOUTH) {
            face.updateMouth(!face.isMouthSmiling());
        }
    }
    
}
