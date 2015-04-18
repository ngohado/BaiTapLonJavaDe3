/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utc.java.database;

/**
 *
 * @author Ngo Hado
 */
public class Candidate {
    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
   
    public String getDateOfBirh() {
        return dateOfBirh;
    }

    public void setDateOfBirh(String dateOfBirh) {
        this.dateOfBirh = dateOfBirh;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getMathPoint() {
        return mathPoint;
    }

    public void setMathPoint(float mathPoint) {
        this.mathPoint = mathPoint;
    }

    public float getPhysicalPoint() {
        return physicalPoint;
    }

    public void setPhysicalPoint(float physicalPoint) {
        this.physicalPoint = physicalPoint;
    }

    public float getChemistryPoint() {
        return chemistryPoint;
    }

    public void setChemistryPoint(float chemistryPoint) {
        this.chemistryPoint = chemistryPoint;
    }

    public float getEnglishPoint() {
        return englishPoint;
    }

    public void setEnglishPoint(float englishPoint) {
        this.englishPoint = englishPoint;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
 
     public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    private String fullName ;
    private Province province ;
    private String dateOfBirh ;
    private boolean sex ;
    private String unit ;
    private float mathPoint ;
    private float physicalPoint ;
    private float chemistryPoint ;
    private float englishPoint ;
    private Area area ;

   
}
