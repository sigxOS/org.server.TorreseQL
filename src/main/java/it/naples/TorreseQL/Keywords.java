package it.naples.TorreseQL;

import java.util.Arrays;
import java.util.List;

public class Keywords {
	public final static String SELECT = "VOGLJ";

	public final static String UPDATE = "AMMESC";
	
	public final static String[] INSERT = { 
			"NGAS",
			"IND"
	};
	
	public static final String[] DELETE = {
			"LIEV",
			"A",
			"MIEZZ"
	};
	
	public static final String[] JOIN = { 
			"AMMESC", 
			"TUTT",
			"COS"
	};
	
	public static final String[] FROM = { 
			"MMIEZ",
			"A"
	};
	
	public final static String[] ALL = { 
			"TUTT",
			"COS"
	};
	
	public final static String WHERE = "ADDO'";
	
	public final static String[] BEGIN = { 
			"ITTAMM", 
			"I",
			"MMAN" 
	};
	
	public final static String[] COMMIT = { 
			"JAMMUNCENN", 
			"A",
			"CAS"
	};
	
	public final static String ROLLBACK = "CAZZ";
	
	public final static String AND = "PO";
	
	public final static String OR = "O";
	
	public final static String NULL = "NIENT";
	
	public final static String IS = "APPARTEN";

	public final static String[] ISNT = {
			"NUN",
			"APPARTEN"
	};

	public final static String VALUES = "CHEST";

	public final static String SET_KEYWORD = "APPARAMM";
	
	public final static List<String> OPERATORS = Arrays.asList(
			">",
			"<",
			"=",
			"!=",
			"<>",
			">=",
			"<=",
			Keywords.IS,
			Keywords.ISNT[0]
	);

	public final static String EQUAL = "UGUAL";
	 
}