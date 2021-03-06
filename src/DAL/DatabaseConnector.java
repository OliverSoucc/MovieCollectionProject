package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class DatabaseConnector {
    private SQLServerDataSource dataSource;

    public DatabaseConnector(){
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("MovieCollection_Slovaks");
        dataSource.setUser("CSe21B_26");
        dataSource.setPassword("CSe21B_26");
        dataSource.setPortNumber(1433);
    }

    public Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }
}
