package com.example.stas.project;
/**
 * Created by stas on 17/04/16.
 */
public class Tremps {

    private String trempID;
    private String trempSource;
    private String trempDest;
    private String trempDriverId;
    private String trempOutTime;
    private String trempArriveTime;

    public Tremps(){

    }



    public Tremps(String trempID, String trempSource, String trempDest, String trempDriverId, String trempOutTime, String trempArriveTime,
                  String trempPostedAt, int trempNumberP, String trempMaslulim)
    {
        this.trempID=trempID;
        this.trempSource=trempSource;
        this.trempDest=trempDest;
        this.trempDriverId= trempDriverId;
        this.trempOutTime= trempOutTime;
        this.trempArriveTime= trempArriveTime;
        this.trempPostedAt= trempPostedAt;
        this.trempNumberP= trempNumberP;
        this.trempMaslulim= trempMaslulim;


    }







    public String getTrempDest() {
        return trempDest;
    }

    public void setTrempDest(String trempDest) {
        this.trempDest = trempDest;
    }



    public String getTrempDriverId() {
        return trempDriverId;
    }

    public void setTrempDriverId(String trempDriverId) {
        this.trempDriverId = trempDriverId;
    }

    public String getTrempOutTime() {
        return trempOutTime;
    }

    public void setTrempOutTime(String trempOutTime) {
        this.trempOutTime = trempOutTime;
    }

    public String getTrempArriveTime() {
        return trempArriveTime;
    }

    public void setTrempArriveTime(String trempArriveTime) {
        this.trempArriveTime = trempArriveTime;
    }

    public int getTrempNumberP() {
        return trempNumberP;
    }

    public void setTrempNumberP(int trempNumberP) {
        this.trempNumberP = trempNumberP;
    }

    public String getTrempPostedAt() {
        return trempPostedAt;
    }

    public void setTrempPostedAt(String trempPostedAt) {
        this.trempPostedAt = trempPostedAt;
    }

    public String getTrempMaslulim() {
        return trempMaslulim;
    }

    public void setTrempMaslulim(String trempMaslulim) {
        this.trempMaslulim = trempMaslulim;
    }

    private String trempPostedAt;
    private int trempNumberP;
    private String trempMaslulim;


    public String getTrempSource() {
        return trempSource;
    }

    public void setTrempSource(String trempSource) {
        this.trempSource = trempSource;
    }



    public String getTrempID() {
        return trempID;
    }

    public void setTrempID(String trempID) {
        this.trempID = trempID;
    }

}
