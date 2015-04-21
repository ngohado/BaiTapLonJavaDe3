/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.liststudent;

import java.sql.SQLException;
import java.util.ArrayList;
import utc.java.database.Candidate;

/**
 *
 * @author Ngô
 */
public class Filtre {

    public static ArrayList<Candidate> filtreData(ArrayList<Candidate> arrCandidate, String fieldData, String keySearch) throws SQLException {
        ArrayList<Candidate> arrFiltre = new ArrayList<Candidate>();

        switch (fieldData) {
            case "ID":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);

                    try {
                        if (temp.getId() == Integer.parseInt(keySearch)) {
                        arrFiltre.add(temp);
                        break;
                        }
                    } catch (Exception e) {
                        
                    }

                }
                break;

            case "Full Name":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getFullName().equalsIgnoreCase(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Province":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getProvince().getProvinceName().equalsIgnoreCase(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Date of Birth":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getDateOfBirh().equalsIgnoreCase(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Sex":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    String gender = "false";
                    if (temp.getSex()) {
                        gender = "true";
                    }

                    if (gender.equalsIgnoreCase(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Unit":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getUnit().equalsIgnoreCase(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Math Point":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getMathPoint() == Float.parseFloat(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Physics Point":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getPhysicalPoint() == Float.parseFloat(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Chemistry Point":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getChemistryPoint() == Float.parseFloat(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "English Point":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getEnglishPoint() == Float.parseFloat(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

            case "Area":
                for (int i = 0; i < arrCandidate.size(); i++) {
                    Candidate temp = arrCandidate.get(i);
                    if (temp.getArea().getAreaName().equalsIgnoreCase(keySearch)) {
                        arrFiltre.add(temp);
                    }
                }
                break;

        }

        return arrFiltre;
    }
}
