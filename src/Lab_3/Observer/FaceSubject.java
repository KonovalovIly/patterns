package Lab_3.Observer;

public interface FaceSubject {
    void attach(FaceObserver observer);
    void detach(FaceObserver observer);
    void notifyObservers(Feature f);
    
    boolean isMouthSmiling();
    boolean isNoseColored();
    boolean isEyeOpen(Side side);
    
    void updateMouth(boolean toSmile);
    void updateNose(boolean toColored);
    void updateEye(Side side, boolean toClosed);

}
