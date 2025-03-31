package lib;

public class SearchResultWrapper {
	private Object result;
	private String type;

	public SearchResultWrapper(Object result, String type) {
		this.result = result;
		this.type = type;
	}

	public Object getResult() {
		return result;
	}

	public String getType() {
		return type;
	}

	public String toString() {
		return result.toString();
	}
}
