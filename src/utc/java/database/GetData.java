/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utc.java.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Ngo Hado
 */
public class GetData {

    public static ArrayList<Candidate> getAllCandidatesInformation(Connection connect) throws SQLException {
        ArrayList<Candidate> arrCandidate = new ArrayList<Candidate>();

        String querySQL = "select *\n"
                + "from TbArea,TbListCandidate,TbProvince\n"
                + "where (TbArea.areaCode=TbListCandidate.areaCode) and (TbListCandidate.provinceCode=TbProvince.provinceCode)";

        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(querySQL);

        while (resultSet.next()) {
            Candidate element = new Candidate();

            element.setId(resultSet.getInt("id"));

            element.setFullName(resultSet.getString("fullName"));

            Province a = new Province();
            a.setProvinceCode(resultSet.getInt("provinceCode"));
            a.setProvinceName(resultSet.getString("provinceName"));
            element.setProvince(a);

            element.setSex(resultSet.getInt("sex") == 1 ? true : false);

            element.setUnit(resultSet.getString("unit"));

            element.setMathPoint(resultSet.getFloat("math"));

            element.setPhysicalPoint(resultSet.getFloat("physics"));

            element.setChemistryPoint(resultSet.getFloat("chemistry"));
            
            element.setEnglishPoint(resultSet.getFloat("english"));

            String[] arrDate = resultSet.getString("bod").split("-");
            element.setDateOfBirh(arrDate[2]+"/"+arrDate[1]+"/"+arrDate[0]);

            Area area = new Area();
            area.setAddPoint(resultSet.getFloat("addPoint"));
            area.setAreaName(resultSet.getString("areaName"));
            area.setCode(resultSet.getInt("areaCode"));
            element.setArea(area);

            arrCandidate.add(element);
        }

        return arrCandidate;
    }
    public static Candidate getOneCandidatesInformation(Connection connect,String id) throws SQLException {
        String querySQL = "select *\n"
                + "from TbArea,TbListCandidate,TbProvince\n"
                + "where (TbArea.areaCode=TbListCandidate.areaCode) and (TbListCandidate.provinceCode=TbProvince.provinceCode) and (TbListCandidate.id="+id+")";

        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(querySQL);
        Candidate element = new Candidate();
        if (resultSet.next()) {
            

            element.setId(resultSet.getInt("id"));

            element.setFullName(resultSet.getString("fullName"));

            Province a = new Province();
            a.setProvinceCode(resultSet.getInt("provinceCode"));
            a.setProvinceName(resultSet.getString("provinceName"));
            element.setProvince(a);

            element.setSex(resultSet.getInt("sex") == 1 ? true : false);

            element.setUnit(resultSet.getString("unit"));

            element.setMathPoint(resultSet.getFloat("math"));

            element.setPhysicalPoint(resultSet.getFloat("physics"));

            element.setChemistryPoint(resultSet.getFloat("chemistry"));
            
            element.setEnglishPoint(resultSet.getFloat("english"));

            String[] arrDate = resultSet.getString("bod").split("-");
            element.setDateOfBirh(arrDate[2]+"/"+arrDate[1]+"/"+arrDate[0]);

            Area area = new Area();
            area.setAddPoint(resultSet.getFloat("addPoint"));
            area.setAreaName(resultSet.getString("areaName"));
            area.setCode(resultSet.getInt("areaCode"));
            element.setArea(area);

        }

        return element;
    }
    
    public static Vector toString(ArrayList<Candidate> a){
        Vector dataMain = new Vector();
        for(int i=0 ; i<a.size() ; i++){
            Candidate temp = (Candidate) a.get(i);
            Vector data = new Vector();
            data.add(temp.getId()+"");
            data.add(temp.getFullName());
            data.add(temp.getProvince().getProvinceName());
            data.add(temp.getDateOfBirh());
            if(temp.getSex()){
                data.add("Male");
            } else data.add("Female");
            data.add(temp.getUnit());
            data.add(temp.getMathPoint()+"");
            data.add(temp.getPhysicalPoint()+"");
            switch(temp.getUnit()){
                case "A":
                    data.add(temp.getChemistryPoint()+"");
                    data.add("x");
                    break;
                case "A1":
                    data.add("x");
                    data.add(temp.getEnglishPoint()+"");                   
                    break;
            }
            data.add(temp.getArea().getAreaName());
            
            dataMain.add(data);
        }
        
        return dataMain;
    }

}
