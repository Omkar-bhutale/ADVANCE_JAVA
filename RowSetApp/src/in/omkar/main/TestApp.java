package in.omkar.main;

import javax.sql.rowset.*;
import java.sql.SQLException;

public class TestApp {

    public static void main(String[] args) throws SQLException {
        RowSetFactory rsf = RowSetProvider.newFactory();
        JdbcRowSet jrs = rsf.createJdbcRowSet();
        CachedRowSet crs = rsf.createCachedRowSet();
        WebRowSet wrs = rsf.createWebRowSet();
        JoinRowSet jnrs = rsf.createJoinRowSet();
        FilteredRowSet frs = rsf.createFilteredRowSet();


        System.out.println(rsf.getClass().getName());
        System.out.println(jrs.getClass().getName());
        System.out.println(crs.getClass().getName());
        System.out.println(wrs.getClass().getName());
        System.out.println(jnrs.getClass().getName());
        System.out.println(frs.getClass().getName());

    }

}
