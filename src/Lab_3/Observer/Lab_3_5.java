package Lab_3.Observer;

public class Lab_3_5 {

    public static void main(String[] args) {
        Face face = new Face();
        EyeObserver eyeObserver = new EyeObserver(face);
        NoseObserver noseObserver = new NoseObserver(face);
        MouthObserver mouthObserver = new MouthObserver(face);
        face.attach(eyeObserver);
        face.attach(noseObserver);
        face.attach(mouthObserver);
        face.showFace();
    }
}
