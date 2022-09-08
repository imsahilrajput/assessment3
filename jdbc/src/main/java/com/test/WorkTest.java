package com.test;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkTest {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        WorkData work = new WorkData();
        Worker wk = new Worker();
        System.out.println("Query 1:");
        System.out.println(work.query1(wk));

        System.out.println("Distinct Department:");
        System.out.println(work.query2(wk));

        System.out.println("Position of 'a' in Amitabh: ");
        ResultSet rs = work.query3(wk);
        rs.next();
        System.out.println(rs.getString(1) + " " + rs.getInt(2));

        for(;;) {
            System.out.println("1. Add Worker\t2. Update Worker\t3. Delete Worker\t4. Display All\t5. Exit");
            System.out.println("Enter your choice");
            int ch = sc.nextInt();
            switch (ch) {
                case 1: {
                    System.out.println("Enter Id");
                    int id = sc.nextInt();
                    System.out.println("Enter First Name");
                    String first = sc.next();
                    System.out.println("Enter Last Name");
                    String last = sc.next();
                    System.out.println("Enter Salary");
                    int salary = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Joining Date");
                    String join = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Enter Department");
                    String dept = sc.next();

                    wk.setId(id);
                    wk.setFirst_name(first);
                    wk.setLast_name(last);
                    wk.setSalary(salary);
                    wk.setJoining_date(join);
                    wk.setDepartment(dept);

                    int x = work.SaveWorker(wk);
                    if (x > 0)
                        System.out.println("Processed");
                    break;
                }

                case 2: {
                    System.out.println("Enter Worker Id");
                    int id = sc.nextInt();
                    System.out.println("Enter First Name");
                    String first = sc.next();
                    System.out.println("Enter Last Name");
                    String last = sc.next();
                    System.out.println("Enter Salary");
                    int salary = sc.nextInt();
                    System.out.println("Enter Joining Date");
                    String join = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Enter Department");
                    String dept = sc.next();


                    wk.setId(id);
                    wk.setFirst_name(first);
                    wk.setLast_name(last);
                    wk.setSalary(salary);
                    wk.setJoining_date(join);
                    wk.setDepartment(dept);

                    work.update(wk);
                    System.out.println("Updated");

                    break;
                }

                case 3: {
                    System.out.println("Enter Worker Id to be deleted");
                    int del = sc.nextInt();

                    wk.setId(del);
                    work.delete(wk);
                    break;
                }

                case 4:{
                    List<Worker> lst = new ArrayList<Worker>();
                    lst = work.getAll();
                    for(Worker w: lst){
                        System.out.println("Worker Id: "+w.getId()+", First Name: "+w.getFirst_name()+", Last Name: "+w.getLast_name()+", Salary: "+w.getSalary()+", Joining Date: "+w.getJoining_date()+", Department: "+w.getDepartment());
                    }
                    break;
                }
                default:
                    System.out.println("Exited");
                    System.exit(0);
                    break;

            }
        }
    }
}