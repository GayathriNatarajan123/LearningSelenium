
public enum ScrollAction {

	TOP("scrollTop = 0"), 
	BOTTOM("scrollTop = scrollHeight");

	private final String jsCode;

	ScrollAction(String jsCode) {
		this.jsCode = jsCode;
	}

	public String getJsCode() {
		return jsCode;
	}

}
