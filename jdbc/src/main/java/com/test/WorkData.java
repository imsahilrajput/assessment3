package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkData {
    public Statement getStatement() {
        Connection con = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/worker", "root", "password");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public int SaveWorker(Worker wk){
        try{
            String sql = "insert into Worker values ("+wk.getId()+",'"+wk.getFirst_name()+"','"+wk.getLast_name()+"',"+wk.getSalary()+",'"+wk.getJoining_date()+"','"+wk.getDepartment()+"')";
            return getStatement().executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Worker wk){
        try{
            String sql = "update Worker set First_name = '"+wk.getFirst_name()+"', Last_name = '"+wk.getLast_name()+"', Salary = "+wk.getSalary()+" ,Joining_date = '"+wk.getJoining_date()+"',Department = '"+wk.getDepartment()+"' where Worker_id = "+wk.getId()+"";
            return getStatement().executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(Worker wk){
        try{
            String sql = "delete from Worker where id = "+wk.getId()+"";
            return getStatement().executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public List<String> query1(Worker wk){
        List<String> full_name = new ArrayList<>();
        try{
            String sql = "select concat(UPPER(first_name),' ',UPPER(last_name)) as fullname from Worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                full_name.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return full_name;
    }

    public List<String> query2(Worker wk){
        List<String> distDept = new ArrayList<>();
        try{
            String sql = "select distinct(Department) from Worker;";
            ResultSet rs = getStatement().executeQuery(sql);

            while(rs.next()) {
                distDept.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distDept;
    }

    public ResultSet query3(Worker wk){
        String sql = null;
        try{
            sql = "select First_name, POSITION('a' in First_name) from Worker where Worker_id = 4;";
            return getStatement().executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Worker> getAll(){
        List<Worker> lst = new ArrayList<Worker>();
        try{
            String sql = "select * from Worker";
            ResultSet rs = getStatement().executeQuery(sql);
            while(rs.next()){
                Worker wk =  new Worker();
                wk.setId(rs.getInt(1));
                wk.setFirst_name(rs.getString(2));
                wk.setLast_name(rs.getString(3));
                wk.setSalary(rs.getInt(4));
                wk.setJoining_date(rs.getString(5));
                wk.setDepartment(rs.getString(6));
                lst.add(wk);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lst;
    }
}