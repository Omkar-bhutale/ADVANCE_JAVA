//not useful for fresher
package in.omkar.main;

import javax.sql.rowset.JdbcRowSet;


import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class JdbcRowSet1 {
    public static void main(String[] args) throws SQLException {
        RowSetFactory rsf = RowSetProvider.newFactory();
        JdbcRowSet jrs = rsf.createJdbcRowSet(); //same as resultset(connexted)but updatable and scrolleble
        //setting url user pass
//        jrs.setURL("jdbc:mysql:///omkar");
//      jrs.setUsername("root");
//        jrs.setPassword("passsword");-->not working why it is given in class
        String url = "jdbc:mysql://localhost:3306/omkar?useSSL=false&user=root&password=password";
        jrs.setUrl(url);

        //setting Command foe Execution
        jrs.setCommand("select * from student");
        jrs.execute();
        //getting data in forward dirction
        while (jrs.next()){

            System.out.println(jrs.getInt(1));
            System.out.println(jrs.getString(2));
            System.out.println(jrs.getInt(3));
            System.out.println(jrs.getString(4));

        }
        //getting data in backword dirction
        while (jrs.previous()){

            System.out.println(jrs.getInt(1));
            System.out.println(jrs.getString(2));
            System.out.println(jrs.getInt(3));
            System.out.println(jrs.getString(4));

        }
        //accessing record randomly
    }
}
