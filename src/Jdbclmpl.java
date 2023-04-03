import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class Jdbclmpl {
    public DataSource dataSource(){
        PGSimpleDataSource dataSource=new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("samrouth020320");
        dataSource.setDatabaseName("jdbc");
        return dataSource;
    }

}
