package services;

public enum Services {
	FLIGHTS("flights", "flights.db"),
	ACCOMMODATION("accommodation", "accommodations.db"),
	ATTRACTIONS("attractions", "attractions.db");

	private String dbName;
	private String mainTable;

	Services(String mainTable, String dbName) {
		this.dbName = dbName;
		this.mainTable = mainTable;
	}

	public String getDbName() {
		return dbName;
	}

	public String getMainTable() {
		return mainTable;
	}
}
