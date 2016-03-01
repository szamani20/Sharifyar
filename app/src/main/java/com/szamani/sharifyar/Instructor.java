package com.szamani.sharifyar;

/**
 * Created by Szamani on 8/4/2015.
 */
public class Instructor {
    private String name;
    private String honors;
    private String teachingBackground;
    private Integer logo;

    public Instructor(String name, String honors) {
        this.name = name;
        this.honors = honors;
    }

    public Instructor(String name, String honors,
                      String teachingBackground, Integer logo) {
        this.name = name;
        this.honors = honors;
        this.teachingBackground = teachingBackground;
        this.logo = logo;
    }

    public Instructor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeachingBackground() {
        return teachingBackground;
    }

    public void setTeachingBackground(String teachingBackground) {
        this.teachingBackground = teachingBackground;
    }

    public Integer getLogo() {
        return logo;
    }

    public void setLogo(Integer logo) {
        this.logo = logo;
    }

    public String getHonors() {
        return honors;
    }

    public void setHonors(String honors) {
        this.honors = honors;
    }

}
