/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import utc.java.database.Candidate;
import utc.java.database.GetData;

/**
 *
 * @author Ngô
 */
public class SortData {

    public static Vector getDataSorted(ArrayList<Candidate> arrCandidate, String column) {
        SortData sort = new SortData();

        switch (column) {
            case "ID":
                return GetData.toString(sort.sortID(arrCandidate));

            case "Full Name":
                return GetData.toString(sort.sortNameAndDate(arrCandidate, "Full Name"));

            case "Province":
                return GetData.toString(sort.sortString(arrCandidate, "Province"));

            case "Date Of Birth":
                return GetData.toString(sort.sortNameAndDate(arrCandidate, "Date Of Birth"));

            case "Gender":
                break;

            case "Unit":
                return GetData.toString(sort.sortString(arrCandidate, "Unit"));

            case "Math":
                return GetData.toString(sort.sortNumber(arrCandidate, "Math"));

            case "Physics":
                return GetData.toString(sort.sortNumber(arrCandidate, "Physics"));

            case "Chemistry":
                return GetData.toString(sort.sortNumber(arrCandidate, "Chemistry"));

            case "English":
                return GetData.toString(sort.sortNumber(arrCandidate, "English"));

            case "Area":
                return GetData.toString(sort.sortString(arrCandidate, "Area"));

        }

        return GetData.toString(arrCandidate);
    }

    public ArrayList<Candidate> sortString(ArrayList<Candidate> arr, String type) {

        for (int i = 0; i < arr.size(); i++) {
            

            for (int j = i + 1; j < arr.size(); j++) {
                String stringI = "";
                String stringJ = "";
                switch (type) {
                    case "Area":
                        stringI = arr.get(i).getArea().getAreaName();
                        stringJ = arr.get(j).getArea().getAreaName();
                        break;

                    case "Province":
                        stringI = arr.get(i).getProvince().getProvinceName();
                        stringJ = arr.get(j).getProvince().getProvinceName();
                        break;

                    case "Unit":
                        stringI = arr.get(i).getUnit();
                        stringJ = arr.get(j).getUnit();
                        break;
                        

                }
                if (stringI.compareToIgnoreCase(stringJ) > 0) {
                    Collections.swap(arr, i, j);
                }

            }
        }

        return arr;
    }

    public ArrayList<Candidate> sortNameAndDate(ArrayList<Candidate> arr, String type) {
        int n = arr.size();
        
        ArrayList<String> arrNameSplited = new ArrayList<String>();
        ArrayList<String> arrDateSplited = new ArrayList<String>();
        
        for (int i = 0; i < arr.size(); i++){
            arrNameSplited.add(arr.get(i).getFullName());
            arrDateSplited.add(arr.get(i).getDateOfBirh());
        }
        
        for (int i = 0; i < n; i++) {
            String[] nameSplit = arrNameSplited.get(i).split(" ");
            StringBuilder nameBuilder = new StringBuilder(nameSplit[nameSplit.length-1]);
            for(int j=0 ; j<nameSplit.length-2 ; j++){
                nameBuilder.append(nameSplit[j]);
            }
     
            String name = nameBuilder.toString();
            arrNameSplited.set(i, name);
            
            String[] dateSplit = arrDateSplited.get(i).split("/");
            String date = new StringBuilder(dateSplit[2]).append(dateSplit[1]).append(dateSplit[0]).toString();
            arrDateSplited.set(i, date);

        }
        
        if (type.equalsIgnoreCase("Full Name")) {
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (arrNameSplited.get(i).compareToIgnoreCase(arrNameSplited.get(j)) > 0) {
                        Collections.swap(arr, i, j);
                        Collections.swap(arrNameSplited, i, j);
                    }

                }
            }
        } else {
            for (int i = 0; i < n; i++) {   
                for (int j = i + 1; j < n; j++) {
                    String dateI = arrDateSplited.get(i);
                    String dateJ = arrDateSplited.get(j);
                    if (dateI.compareTo(dateJ) > 0) {
                        Collections.swap(arr, i, j);
                        Collections.swap(arrDateSplited, i, j);
                    }

                }
            }
        }
        
        return arr;
    }

    public ArrayList<Candidate> sortNumber(ArrayList<Candidate> arr, String type) {

        for (int i = 0; i < arr.size(); i++) {
            
            for (int j = i + 1; j < arr.size(); j++) {
                float pointI = 0;
                float pointJ = 0;
                switch (type) {
                    case "Math":
                        pointI = arr.get(i).getMathPoint();
                        pointJ = arr.get(j).getMathPoint();
                        break;

                    case "Physics":
                        pointI = arr.get(i).getPhysicalPoint();
                        pointJ = arr.get(j).getPhysicalPoint();
                        break;

                    case "Chemistry":
                        pointI = arr.get(i).getChemistryPoint();
                        pointJ = arr.get(j).getChemistryPoint();
                        break;

                    case "English":
                        pointI = arr.get(i).getEnglishPoint();
                        pointJ = arr.get(j).getEnglishPoint();
                        break;

                }

                if (pointI < pointJ) {
                    Collections.swap(arr, i, j);
                }

            }
        }

        return arr;
    }

    public ArrayList<Candidate> sortID(ArrayList<Candidate> arr) {
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i).getId() > arr.get(j).getId()) {
                    Collections.swap(arr, i, j);
                }
            }
        }
        System.out.println("" + arr.size());
        return arr;
    }
}
