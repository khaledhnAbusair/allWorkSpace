package constant;

public class Constants {
	public static final String UPDATE_TERM_CATEGORY = "update TermCategory as t set t.allowChildren=:allowChildren where t.catName=:catName ";
	public static final String UPDATE_TERM = "update Term as t set t.termName=:termName , t.termLabel=:termLable,termPurpose=:termPurpose where t.termId=:termId";
	public static final int HAS_CHILDREN = 1;

	private Constants() {
	}

}
