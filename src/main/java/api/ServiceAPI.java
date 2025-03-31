package api;

import java.sql.ResultSet;

public interface ServiceAPI {
	ResultSet getAllEntries();
	ResultSet getAllAvailable();
	void close();
}
