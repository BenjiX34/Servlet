package simplejdbc;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class StatesTest {
	private DAO myDAO; // L'objet à tester
	private DataSource myDataSource; // La source de données à utiliser
        private States states;
	

	@Before
	public void setUp() throws SQLException {
		myDataSource = DataSourceFactory.getDataSource();
		myDAO = new DAO(myDataSource);
                states = new States(myDataSource);
	}
	
	@Test
	public void states() throws SQLException, DAOException{
            System.out.println(states.listOfStates());
        }
	
}
