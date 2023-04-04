import org.postgresql.ds.PGSimpleDataSource;
public class Jdbclmpl {
    public PGSimpleDataSource dataSource(){
        PGSimpleDataSource dataSource=new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("samrouth020320");
        dataSource.setDatabaseName("jdbc");
        return dataSource;
    }

}
