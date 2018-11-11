/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brailleantv2;
import java.lang.Exception;

/**
 *
 * @author pi
 */
public class Global {
    public static final String BRAILLEANT_DIR="/home/pi/Documents/Brailleant";
    public static final String CAMERA_CONFIG_DIR=BRAILLEANT_DIR+"/CameraConfig.txt";
    //public static final String CAMERA_CONFIG_DIR="C:/users/Zildjian/Documents/CameraConfig.txt";
    public static final String CAMERA_IMAGEBUFF_DIR=BRAILLEANT_DIR+"/imgbuff.jpg";
    public static final String FILES_DIR=BRAILLEANT_DIR+"/Files";
    public static final String DICTIONARY_DIR=BRAILLEANT_DIR+"/dictionary/";
    public static final String DICT_DIR=DICTIONARY_DIR+"dict.txt";
    
    public static int map(int xfromRange, int xtoRange, int x, int yfromRange, int ytoRange){
        if(xfromRange>=xtoRange||yfromRange>=ytoRange||x<xfromRange||x>xtoRange)
        throw new RuntimeException("IKAW BOBO!");
        return (int)((((x-xfromRange)*1.0/(xtoRange-xfromRange)))*(ytoRange-yfromRange))+yfromRange;
    }
    public static void main(String[] args){
        //System.out.println(map(-50,100,-25,-50,100));
        //System.out.println(toAlibata("1234567890!@#$^&*()qwertuiopasfghjklzxc`vbnmy"));
    }
    public static String fromAlibata(String str){
        String out = "";
        for(int ctr=0; ctr<str.length(); ctr++)
        {
            String x;
            switch(str.charAt(ctr)){
                case '1': x = "a";
                break;
                case '2': x = "ba";
                break;
                case '3': x = "be";
                break;
                case '4': x = "bo";
                break;
                case '5': x = "b";
                break;
                case '6': x = "ka";
                break;
                case '7': x = "ke";
                break;
                case '8': x = "ko";
                break;
                case '9': x = "k";
                break;
                case '0': x = "e";
                break;
                case '!': x = "ga";
                break;
                case '@': x = "ge";
                break;
                case '#': x = "go";
                break;
                case '$': x = "g";
                break;
                case '^': x = "ha";
                break;
                case '&': x = "he";
                break;
                case '*': x = "ho";
                break;
                case '(': x = "h";
                break;
                case ')': x = "la";
                break;
                case 'q': x = "le";
                break;
                case 'w': x = "lo";
                break;
                case 'e': x = "l";
                break;
                case 'r': x = "ma";
                break;
                case 't': x = "me";
                break;
                case 'u': x = "mo";
                break;
                case 'i': x = "na";
                break;
                case 'o': x = "ne";
                break;
                case 'p': x = "no";
                break;
                case 'a': x = "n";
                break;
                case 's': x = "nga";
                break;
                case 'f': x = "nge";
                break;
                case 'g': x = "ngo";
                break;
                case 'h': x = "ng";
                break;
                case 'j': x = "ta";
                break;
                case 'k': x = "te";
                break;
                case 'l': x = "to";
                break;
                case 'z': x = "t";
                break;
                case 'x': x = "wa";
                break;
                case 'c': x = "we";
                break;
                case '`': x = "wo";
                break;
                case 'v': x = "w";
                break;
                case 'b': x = "ya";
                break;
                case 'n': x = "ye";
                break;
                case 'm': x = "yo";
                break;
                case 'y': x = "y";
                break;
                case '\n': x="\n";
                break;
                default:
                x=" ";
                    
            }
            out=out.concat(x);
        }
        return out;
    }
    public static String fromAlibata2(String str){
        String out = "";
        for(int ctr=0; ctr<str.length(); ctr++)
        {
            String x;
            switch(str.charAt(ctr)){
                /*
                a V
e B
o N
ba L
be Z
bo X
b C
da A
de S
do D
d F
ga O
ge P
go {
g }
ha T
he Y
ho U
h I
ka G
ke H
ko J
k K
la Q
le W
lo E
l R
ma n
me m
mo ,
m .
Nga l
Nge ;
Ngo '
Ng z
na x
ne c
no v
n b
pa g
pe h
po j
p k
sa a
se s
so d
s f
ta o
te p
to [
t ]
wa q
we w
wo e
w r 
ya t
ye y
yo u
y i
                */
                case 'V': x = "a";
                break;
                case 'L': x = "ba";
                break;
                case 'Z': x = "be";
                break;
                case 'X': x = "bo";
                break;
                case 'C': x = "b";
                break;
                case 'A': x = "da";
                break;
                case 'S': x = "de";
                break;
                case 'D': x = "do";
                break;
                case 'F': x = "d";
                break;
                case 'G': x = "ka";
                break;
                case 'H': x = "ke";
                break;
                case 'J': x = "ko";
                break;
                case 'K': x = "k";
                break;
                case 'B': x = "e";
                break;
                case 'O': x = "ga";
                break;
                case 'P': x = "ge";
                break;
                case '{': x = "go";
                break;
                case '}': x = "g";
                break;
                case 'T': x = "ha";
                break;
                case 'Y': x = "he";
                break;
                case 'U': x = "ho";
                break;
                case 'I': x = "h";
                break;
                case 'Q': x = "la";
                break;
                case 'W': x = "le";
                break;
                case 'E': x = "lo";
                break;
                case 'R': x = "l";
                break;
                case 'n': x = "ma";
                break;
                case 'm': x = "me";
                break;
                case ',': x = "mo";
                break;
                case '.': x = "m";
                break;
                case 'x': x = "na";
                break;
                case 'c': x = "ne";
                break;
                case 'v': x = "no";
                break;
                case 'b': x = "n";
                break;
                case 'l': x = "nga";
                break;
                case ';': x = "nge";
                break;
                case '\'': x = "ngo";
                break;
                case 'z': x = "ng";
                break;
                case 'N' : x = "o";
                break;
                case 'g': x = "pa";
                break;
                case 'h': x = "pe";
                break;
                case 'j': x = "po";
                break;
                case 'k': x = "p";
                break;
                case 'a': x = "sa";
                break;
                case 's': x = "se";
                break;
                case 'd': x = "so";
                break;
                case 'f': x = "s";
                break;
                case 'o': x = "ta";
                break;
                case 'p': x = "te";
                break;
                case '[': x = "to";
                break;
                case ']': x = "t";
                break;
                case 'q': x = "wa";
                break;
                case 'w': x = "we";
                break;
                case 'e': x = "wo";
                break;
                case 'r': x = "w";
                break;
                case 't': x = "ya";
                break;
                case 'y': x = "ye";
                break;
                case 'u': x = "yo";
                break;
                case 'i': x = "y";
                break;
                case '\n': x="\n";
                break;
                default:
                x=" ";
                    
            }
            out=out.concat(x);
        }
        return out;
    }
}
