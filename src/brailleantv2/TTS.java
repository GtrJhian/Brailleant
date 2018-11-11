/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brailleantv2;

import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.FreeTTSSpeakableImpl;

/**
 *
 * @author pi
 */
public class TTS extends Thread {

    public Voice kevin;
    private boolean speak = false;
    private FreeTTSSpeakableImpl speakable;
    private static final int THREAD_DELAY=500;

    public TTS() {
        this.kevin = VoiceManager.getInstance().getVoice("kevin16");
        this.kevin.allocate();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if(speak){
                    this.speak=false;
                    this.kevin.speak(this.speakable);
                }
                else Thread.sleep(THREAD_DELAY);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void speak(String str) {
        this.kevin.getAudioPlayer().cancel();
        this.speakable = new FreeTTSSpeakableImpl(str);
        this.speak=true;
    }
}
