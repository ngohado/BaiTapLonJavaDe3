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

}
