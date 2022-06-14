package Lab_3.State;

import java.awt.image.BufferedImage;

public class Student {

    Activity activity;

    private void setActivity(Activity activity){
        this.activity = activity;
    }

    public void changeActivity(State state){
        switch (state){
            case FUNNY:
                setActivity(new FunnyStudent());
                break;
            case SLEEP:
                setActivity(new SleepStudent());
                break;
            case PRAYING:
                setActivity(new PrayingStudent());
                break;
        }
    }

    public BufferedImage getImage(){
        return activity.getImage();
    }

}
