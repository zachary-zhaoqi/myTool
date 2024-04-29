package org.example.dto;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.applet.Applet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

class URLreq implements Comparable<URLreq> {
    public String url;
    public Integer lenHF;
    public Integer lenFZ;
    public Integer len;
    public String des;

    public static void main(String[] args)  {


//        Integer[] a = new Integer[23];
//        Arrays.fill(a, 0);
//        System.out.println(Arrays.toString(a));
//        for (int i = 0; i < 10000; i++) {
//            Random random = new Random();
//            int j = random.nextInt(1000) %23;
//            int k=random.nextInt(j+1);
//            a[k] = a[k]+1;
//            System.out.println(j);
//        }
//        System.out.println("a>>>>>>>>>>>>>>>>>");
//        System.out.println(Arrays.toString(a));
    }

    @Override
    public String toString() {
        return "URLreq{" +
                "url='" + url + '\'' +
                ", lenHF=" + lenHF +
                ", lenFZ=" + lenFZ +
                ", len=" + len +
                ", des='" + des + '\'' +
                '}';
    }

    @Override
    public int compareTo(URLreq o) {
        if (this.len < o.len) {
            return -1;
        } else if (this.len.equals(o.len)) {
            return this.lenHF.compareTo(o.lenHF);
        } else {
            return 1;
        }
    }
}
