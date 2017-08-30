package com.example.android.javadevslagos;



public class Developers {


    /**Developers Username
     */
    private String mUsername;


    /**Developers Image
     */

    private String mImage;


    /**Developers GITHUB URL
     */

    private String mDevelopersURL;


/**
 * Construct a new {@link Developers} object
 * @param username is the developers username
 * @param image is the developers image
 * @param devurl is the developers github url
 */
    public Developers(String username, String image, String devurl){
        mUsername = username;
        mImage = image;
        mDevelopersURL = devurl;
    }


    /**
     * Returns the username of the developer
     */
    public String getUsername(){return  mUsername;}

    /**
     * Returns the image of the developer
     */
public String getImage(){return mImage;}



    /**
     * Returns the GITHUB URL of the developer
     */

    public String getDeveloperURL(){return mDevelopersURL;}

}

