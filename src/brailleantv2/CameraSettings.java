/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brailleantv2;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

/**
 *
 * @author pi
 */
public class CameraSettings{
    CameraSettings(String src){
        this.src=src;
        try{
            Scanner sc=new Scanner(new File(src));
            this.contrast=sc.nextInt();
            //-100-100 0
            this.saturation=sc.nextInt();
            //-100-100 0
            this.brightness=sc.nextInt();
            //0-100 50
            this.sharpness=sc.nextInt();
            //-100-100 0
            this.shutterSpeed=sc.nextInt();
            //millisec
            this.width=sc.nextInt();
            this.height=sc.nextInt();
            this.rotation=sc.nextInt();
            //0 90 270 180
            this.quality=sc.nextInt();
            //0-100 75
            //this.writeSettings();
        }
        catch(Exception e){
            System.out.println(e);
        }            
    }
    public void writeSettings(){
        try{
            FileWriter wr=new FileWriter(new File(this.src));
            wr.write(this.contrast+"\n");
            wr.write(this.saturation+"\n");
            wr.write(this.brightness+"\n");
            wr.write(this.sharpness+"\n");
            wr.write(this.shutterSpeed+"\n");
            wr.write(this.width+"\n");
            wr.write(this.height+"\n");
            wr.write(this.rotation+"\n");
            wr.write(this.quality+"\n");
            wr.close();
        }
        catch(Exception e){        
            System.out.println(e);
        }        
    }
    public int sharpness;
    public int contrast;
    public int saturation;
    public int brightness;
    public int rotation;
    public int shutterSpeed;
    public int width;
    public int height;
    public int quality;
    private final String src;
}
