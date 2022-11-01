package de.exxcellent.challenge;

public class WeatherDataElement {
    private final int day;
    private final int mxt;
    private final int mnt;
    private final int avt;
    private final float avdp;
    private final int onehrp_tpcdn;
    private final int pdir;
    private final float avsp;
    private final int dir;
    private final int mxs;
    private final float skyc;
    private final int mxr;
    private final int mn;
    private final float r_avslp;

    public WeatherDataElement(int day, int mxt, int mnt, int avt, float avdp, int onehrp_tpcdn, int pdir, float avsp, int dir, int mxs, float skyc, int mxr, int mn, float r_avslp) {
        this.day = day;
        this.mxt = mxt;
        this.mnt = mnt;
        this.avt = avt;
        this.avdp = avdp;
        this.onehrp_tpcdn = onehrp_tpcdn;
        this.pdir = pdir;
        this.avsp = avsp;
        this.dir = dir;
        this.mxs = mxs;
        this.skyc = skyc;
        this.mxr = mxr;
        this.mn = mn;
        this.r_avslp = r_avslp;
    }

    public int getDay() {
        return day;
    }

    public int getMxt() {
        return mxt;
    }

    public int getMnt() {
        return mnt;
    }
}
