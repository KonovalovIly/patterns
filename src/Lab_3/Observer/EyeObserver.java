package Lab_3.Observer;

public class EyeObserver implements FaceObserver {

    private final FaceSubject face;
    
    public EyeObserver(FaceSubject face) {
        this.face = face;
    }
    
    @Override
    public void update(Feature f) {
        if (f == Feature.LEFT_EYE) {
            face.updateEye(Side.LEFT, face.isEyeOpen(Side.LEFT));
        } else if (f == Feature.RIGHT_EYE) {
            face.updateEye(Side.RIGHT, face.isEyeOpen(Side.RIGHT));
        }
    }
    
}
