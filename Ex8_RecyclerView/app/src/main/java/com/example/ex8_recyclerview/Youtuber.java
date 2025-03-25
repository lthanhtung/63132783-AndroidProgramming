package com.example.ex8_recyclerview;

public class Youtuber {
    //Khai báo các biến đại diện cho các trường
    String YoutuberImg;
    String YoutuberName;

    public Youtuber(String youtuberImg, String youtuberName) {
        YoutuberImg = youtuberImg;
        YoutuberName = youtuberName;
    }

    public String getYoutuberImg() {
        return YoutuberImg;
    }

    public void setYoutuberImg(String youtuberImg) {
        YoutuberImg = youtuberImg;
    }

    public String getYoutuberName() {
        return YoutuberName;
    }

    public void setYoutuberName(String youtuberName) {
        YoutuberName = youtuberName;
    }
}
