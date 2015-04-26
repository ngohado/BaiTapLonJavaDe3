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

/**
 *
 * @author Ngô
 */
public class AdjustData {

    public static void insertCandidate(Connection connect, Candidate data) throws SQLException {
        //Chuyen ve dang chuan data de insert vao database
        String[] dateSplit = data.getDateOfBirh().split("/");
        String date = new StringBuilder(dateSplit[2]).
                append("-").append(dateSplit[1]).append("-").
                append(dateSplit[0]).toString();

        //Chuyen ve dang chuan gioi tinh de insert vao database
        int gender = 0;
        if (data.getSex()) {
            gender = 1;
        }

        //Xay dung cau lenh sql 
        String insertSQL = new StringBuilder("insert into TbListCandidate\n").
                append("values('").append(data.getId() + "','").append(data.getFullName()).append("','").
                append(data.getProvince().getProvinceCode() + "','").append(date).append("','").
                append(gender + "','").append(data.getMathPoint() + "','").append(data.getPhysicalPoint() + "','").
                append(data.getChemistryPoint() + "','").append(data.getEnglishPoint() + "','").
                append(data.getArea().getCode() + "','").append(data.getUnit()).append("')").toString();

        //Insert vao database
        Statement statement = connect.createStatement();
        statement.execute(insertSQL);

    }

    public static int insertProvince(Connection connect, String nameProvince) throws SQLException {
        Statement statement = connect.createStatement();
        String inspectSQL = "select *\n"
                + "from TbProvince\n"
                + "where TbProvince.provinceName like '" + nameProvince + "'";

        ResultSet resultSet = statement.executeQuery(inspectSQL);
        resultSet.next();
        try {
            int codeProvince = resultSet.getInt("provinceCode");
            return codeProvince;
        } catch (Exception e) {
            //Xay dung cau lenh SQL
            String insertSQL = new StringBuilder("insert into TbProvince\n").
                    append("values('").
                    append(nameProvince).
                    append("')").toString();

            //Insert vao database
            statement.execute(insertSQL);
            
            resultSet = statement.executeQuery(inspectSQL);
            resultSet.next();
            return resultSet.getInt("provinceCode");
        }

    }

    public static void deleteCandidate(Connection connect, String id) throws SQLException {

        //Xay dung cau lenh SQL
        String deleteSQL = new StringBuilder("delete from TbListCandidate\n").append("where id=" + id).toString();

        //Insert vao database
        Statement statement = connect.createStatement();
        statement.execute(deleteSQL);

    }
}
