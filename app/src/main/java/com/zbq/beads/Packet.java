package com.zbq.beads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by a123 on 15-7-3.
 */
public class Packet {

    protected byte head = 0x55;
    protected byte type = 0x00;
    protected byte lens = 0x00;
    protected byte[] data;
    protected byte checksum = 0x00;

    public Packet(byte type, byte lens, byte[] data) {
        this.type = type;
        this.lens = lens;
        this.data = data;
    }

    public Packet(){

    }

    public enum  LangaugeType{
        ENGLISH(0x00),ARABIC(0x01);
        final int type;
        private LangaugeType(int type){
            this.type= type;
        }

        public byte getValue(){
            return (byte)type;
        }
    }

    public enum  ReminderStatus{
        OFF(0x00),ON(0x01);
        final int status;
        private ReminderStatus(int status){
            this.status= status;
        }

        public byte getValue(){
            return (byte)status;
        }
    }

    public byte[] getlanguage(LangaugeType language){
        this.type = 0x04;
        this.lens = 0x01;
        this.data = new byte[]{language.getValue()};
        return toBytes();
    }

    public byte[] getPair(LangaugeType language){
        this.type = 0x0a;
        this.lens = 0x01;
        this.data = new byte[]{language.getValue()};
        return toBytes();
    }

    public byte[] getTime(long time){
        this.type = 0x01;
        this.lens = 0x04;
        this.data = longToByte(time);
        return toBytes();
    }

    public byte[] Reminder(long time,int no,ReminderStatus state){
        this.type = 0x02;
        this.lens = 0x06;
        this.data = longToByte(time);
        return toBytes((byte)no,state.getValue());
    }

    public byte[] Information(long time,int no,ReminderStatus state){
        this.type = 0x09;
        this.lens = 0x03;
        this.data = longToByte(time);
        return toBytes((byte)no,state.getValue());
    }

    public static byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(temp & 0xff).byteValue();
            temp = temp >> 8;
        }
        return b;
    }

    public byte[] toBytes(byte... bytes){
        final ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        outSteam.write(head);
        outSteam.write(type);
        outSteam.write(lens);
        try {
            outSteam.write(data);
        } catch (IOException e) {
        }
        if(bytes.length>0){
            try {
                outSteam.write(bytes);
            } catch (IOException e) {
            }
        }
        checksum = (byte)outSteam.toByteArray().length;
        outSteam.write(checksum);
        return outSteam.toByteArray();
    }

}
