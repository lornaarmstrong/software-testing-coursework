package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Task1_Functional {
	
	private Parser parser;
	
	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	@Test
	public void shortcutAndNoShortcutTest() {
		parser.add("option", Parser.STRING);
		parser.add("second", "s", Parser.STRING);
		parser.parse("--option=test -s=test2");
		assertEquals(parser.getString("s"), "test2");
	}
	
	@Test
	public void doubleDashFullNameTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=output.txt");
		assertEquals(parser.getString("option"), "output.txt");
	}
	
	@Test
	public void doubleDashBooleanTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option=false");
		assertEquals(parser.getBoolean("option"), false);
	}
	@Test
	public void doubleDashIntegerTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("--option=24");
		assertEquals(parser.getInteger("option"), 24);
	}
	
	@Test
	public void doubleDashCharTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("--option=m");
		assertEquals(parser.getChar("option"), 'm');
	}

	@Test
	public void doubleDashSingleQuotesStringTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option='me'");
		assertEquals(parser.getString("option"), "me");
	}
	
	@Test
	public void doubleDashSingleQuotesCharTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("--option='m'");
		assertEquals(parser.getChar("option"), 'm');
	}	
	
	@Test
	public void doubleDashIntSingleTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("--option='10'");
		assertEquals(parser.getInteger("option"), 10);
	}
	
	@Test
	public void doubleDashBooleanSingleTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option='true'");
		assertEquals(parser.getBoolean("option"), true);
	}

	@Test
	public void doubleDashDoubleQuotesStringTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=\"me\"");
		assertEquals(parser.getString("option"), "me");
	}
	
	@Test
	public void doubleDashDoubleQuotesCharTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("--option=\"m\"");
		assertEquals(parser.getChar("option"), 'm');
	}
	
	@Test
	public void doubleDashIntDoubleTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("--option=\"10\"");
		assertEquals(parser.getInteger("option"), 10);
	}
	
	@Test
	public void doubleDashBooleanDoubleTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option=\"true\"");
		assertEquals(parser.getBoolean("option"), true);
	}
	
	// Bug 7
	@Test
	public void negativeIntegerInput() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("--option=-24");
		assertEquals(parser.getInteger("option"), -24);
	}


	@Test
	public void singleDashShortcutNameTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o=output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}
	
	@Test
	public void singleDashShortcutCharNameTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("-o=o");
		assertEquals(parser.getChar("o"), 'o');
	}
	
	@Test
	public void singleDashShortcutIntNameTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("-o=10");
		assertEquals(parser.getInteger("o"), 10);
	}
	
	@Test
	public void singleDashShortcutBoolNameTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("-o=true");
		assertEquals(parser.getBoolean("o"), true);
	}

	@Test
	public void singleDashShortcutStringSingleTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o='output.txt'");
		assertEquals(parser.getString("o"), "output.txt");
	}
	
	@Test
	public void singleDashShortcutCharSingleTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("-o='o'");
		assertEquals(parser.getChar("o"), 'o');
	}
	
	@Test
	public void singleDashShortcutIntSingleTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("-o='10'");
		assertEquals(parser.getInteger("o"), 10);
	}
	
	@Test
	public void singleDashShortcutBoolSingleTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("-o='true'");
		assertEquals(parser.getBoolean("o"), true);
	}

	@Test
	public void singleDashShortcutStringDoubleTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o=\"output.txt\"");
		assertEquals(parser.getString("o"), "output.txt");
	}
	
	@Test
	public void singleDashShortcutCharDoubleTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("-o=\"o\"");
		assertEquals(parser.getChar("o"), 'o');
	}
	
	@Test
	public void singleDashShortcutIntDoubleTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("-o=\"10\"");
		assertEquals(parser.getInteger("o"), 10);
	}
	
	@Test
	public void singleDashShortcutBoolDoubleTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("-o=\"true\"");
		assertEquals(parser.getBoolean("o"), true);
	}

	@Test
	public void doubleAndSingleQuotes() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o=\"'true'\"");
		assertEquals(parser.getString("o"), "'true'");
	}
	
	// Bug 9 ---------------------------------------------------------------
	@Test
	public void veryLongInput() {
		parser.add("option", "o", Parser.STRING);
		parser.parse(
				"--option=aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		assertEquals(parser.getString("option"), 
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	// Bug 3
	@Test
	public void overrideTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("option", "m", Parser.STRING);
		parser.parse("-o output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}	
	
	// Bug 8
	@Test
	public void valueContainsUnderscoreTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o=test_input");
		assertEquals(parser.getString("option"), "test_input");
	}
	
	// Bug 2
	@Test
	public void nameDigitLetterUnderscoreTest() {
		parser.add("option123_", "o", Parser.STRING);
		parser.parse("--option123_ output.txt");
		assertEquals(parser.getString("option123_"), "output.txt");
	}	
	
	@Test
	public void nameDigitLetterExclamTest() {
		parser.add("optio!", "o", Parser.STRING);
		parser.parse("--optio! output.txt");
		assertEquals(parser.getString("optio!"), "output.txt");
	}
	
	@Test
	public void digitFirstLetterString() {
		parser.add("2option", "option", Parser.STRING);
		parser.parse("--2option output.txt");
		assertEquals(parser.getString("2option"), "output.txt");
	}
	
	@Test
	public void digitFirstLetterChar() {
		parser.add("option", "2option", Parser.CHAR);
		parser.parse("--2option o");
		assertEquals(parser.getChar("2option"), 'o');
	}
	
	@Test
	public void digitFirstLetterInt() {
		parser.add("2option", "", Parser.INTEGER);
		parser.parse("--2option 10");
		assertEquals(parser.getInteger("2option"), 10);
	}
	
	@Test
	public void digitFirstLetterBool() {
		parser.add("2option", "o", Parser.BOOLEAN);
		parser.parse("--2option true");
		assertEquals(parser.getBoolean("2option"), true);
	}

	@Test
	public void valueAssignedSpaceShortcutNameTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}

	@Test
	public void caseSensitiveTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("OpTiOn", "op", Parser.STRING);
		parser.parse("--OpTiOn output.txt --option test.txt");
		assertEquals(parser.getString("OpTiOn"), "test.txt");
	}

	@Test
	public void decorativeQuotationMarkTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=\'value=\"abc\"\'");
		assertEquals(parser.getString("option"), "value=\"abc\"");
	}
	
	@Test
	public void multipleOptionsTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("filename", "f", Parser.STRING);
		parser.parse("--filename=text.txt --option=output.txt");
		assertEquals(parser.getString("filename"), "text.txt");
	}

	@Test
	public void multipleOptionsShortcutSameTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=output1.txt -o=output.txt");
		assertEquals(parser.getString("option"), "output.txt");
	}

	@Test
	public void emptyOptionNoInputBooleanTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("");
		assertEquals(parser.getString("option"), false);
	}
	
	@Test
	public void emptyOptionNoInputCharTest() {
		parser.add("option", "o", Parser.CHAR);
		parser.parse("");
		assertEquals(parser.getString("option"), '\0');
	}
	
	@Test
	public void emptyOptionNoInputBoolTest() {
		parser.add("option", "o", Parser.INTEGER);
		parser.parse("");
		assertEquals(parser.getString("option"), 0);
	}	
	
	// Bug 11 [3 points]
	@Test
	public void emptyOptionTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("o", "O", Parser.STRING);
		parser.parse("--option=output.txt");
		assertEquals(parser.getString("option"), "output.txt");
	}
	
	@Test
	public void emptyOptionSymbolTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("o", "O", Parser.STRING);
		parser.parse("--option=output.txt");
		assertEquals(parser.getString("option"), "output.txt");
	}
	
	@Test
	public void caseSensitiveShortOptionsTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("Option", "O", Parser.STRING);
		parser.parse("-o=output.txt -O=test.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}
	
	@Test
	public void caseSensitiveOptionsTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("Option", "O", Parser.STRING);
		parser.parse("--option=output.txt --Option=test.txt");
		assertEquals(parser.getString("option"), "output.txt");
	}


	@Test
	public void optionNameContainsDigitsTest() {
		parser.add("option24", "o", Parser.STRING);
		parser.parse("--option24=output.txt");
		assertEquals(parser.getString("option24"), "output.txt");
	}
	
	@Test
	public void nameContainsUnderscoresTest() {
		parser.add("option_", "o", Parser.STRING);
		parser.parse("--option_=output.txt");
		assertEquals(parser.getString("option_"), "output.txt");
	}
	
	@Test
	public void digitAsFirstCharacterTest() {
		parser.add("2option", "o", Parser.STRING);
		parser.parse("--2option=output.txt");
		assertEquals(parser.getString("2option"), "output.txt");
	}
	
	@Test
	public void booleanOptionCaseInsensitiveTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option=FALSE");
		assertEquals(parser.getBoolean("option"), false);
	}

	// Bug 4 [2 marks]
	@Test
	public void booleanOptionNumberTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option=0");
		assertEquals(parser.getBoolean("option"), false);
	}
	
	@Test
	public void booleanOptionNoInputTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option ");
		assertEquals(parser.getBoolean("option"), false);
	}

	@Test
	public void booleanOptionTrueIntegerTest() {
		parser.add("option", "o", Parser.BOOLEAN);
		parser.parse("--option=100");
		assertEquals(parser.getBoolean("option"), true);
	}	
	
	// Bug 11 
	@Test
	public void shortcutSameNameAsOptionTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("o", "m", Parser.STRING);
		parser.parse("--option=text.txt --o=test");
		assertEquals(parser.getString("o"), "test");
	}
	
	// Bug 10
	@Test
	public void noShortcutSameNameOptionShortcutTest() {
		parser.add("option", Parser.STRING);
		parser.add("o", Parser.STRING);
		parser.parse("--option=text.txt --o=test");
		assertEquals(parser.getString("o"), "test");
	}
	
	// Bug 2
	@Test
	public void noShortOptionDigitsTest() {
		parser.add("option2", Parser.STRING);
		parser.parse("--option2=test");
		assertEquals(parser.getString("option2"), "test");
	}
	
	// Bug 1
	@Test
	public void emptyOptionInputTest() {
		parser.add("", Parser.STRING);
		parser.parse("--=test");
		assertEquals(parser.getString(""), "test");
	}
	
	@Test
	public void emptyOptionCharInputTest() {
		parser.add("", Parser.CHAR);
		parser.parse("--=c");
		assertEquals(parser.getChar(""), "c");
	}
	
	@Test
	public void emptyOptionIntegerInputTest() {
		parser.add("", Parser.INTEGER);
		parser.parse("--=100");
		assertEquals(parser.getInteger(""), "100");
	}
	
	@Test
	public void emptyOptionBooleanInputTest() {
		parser.add("", Parser.BOOLEAN);
		parser.parse("--=false");
		assertEquals(parser.getBoolean(""), "false");
	}
	
	@Test
	public void emptyOptionShortcutInputTest() {
		parser.add("option", "", Parser.STRING);
		parser.parse("-=test");
		assertEquals(parser.getString(""), "test");
	}

	@Test
	public void noShortOptionDigitsUnderscoreTest() {
		parser.add("option2_", Parser.STRING);
		parser.parse("--option2_=test");
		assertEquals(parser.getString("option2_"), "test");
	}
	
	@Test
	public void noShortOptionUnderscoreTest() {
		parser.add("option_", Parser.STRING);
		parser.parse("--option_=test");
		assertEquals(parser.getString("option_"), "test");
	}

	@Test
	public void noShortOptionStartDigitTest() {
		parser.add("7option", Parser.STRING);
		parser.parse("--7option=test");
		assertEquals(parser.getString("7option"), "test");
	}
	
	@Test
	public void booleanAssignmentTest() {
		parser.add("option", Parser.BOOLEAN);
		parser.parse("--option=100");
		assertEquals(parser.getBoolean("option"), true);
	}
	
	// Bug 4 [2 points]
	@Test
	public void booleanAssignmentFalseTest() {
		parser.add("option", Parser.BOOLEAN);
		parser.parse("--option=0");
		assertEquals(parser.getBoolean("option"), false);
	}

	@Test
	public void booleanAssignmentSpaceTest() {
		parser.add("option", Parser.BOOLEAN);
		parser.parse("--option 100");
		assertEquals(parser.getBoolean("option"), true);
	}
	
	@Test
	public void doubleHyphenTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=test.txt");
		assertEquals(parser.getString("option"), "test.txt");
	}
	
	@Test
	public void singleHyphenTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("-o=test.txt");
		assertEquals(parser.getString("o"), "test.txt");
	}	

	@Test
	public void equalsTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=text.txt");
		assertEquals(parser.getString("option"), "text.txt");
	}
	
	@Test
	public void spaceTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option text.txt");
		assertEquals(parser.getString("option"), "text.txt");
	}
	
	@Test
	public void singleQuotationMarksTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option='test.txt'");
		assertEquals(parser.getString("option"), "test.txt");
	}
	
	@Test
	public void doubleQuotationMarksTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=\"test.txt\"");
		assertEquals(parser.getString("option"), "test.txt");
	}
	
	@Test
	public void doubleQuotesSingleQuotesInInputTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=\"\'test\'=.txt\"");
		assertEquals(parser.getString("o"), "\'test\'=.txt");
	}
	
	@Test
	public void singleQuotesDoubleQuotesInInputTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=\'\"test\"=.txt\'");
		assertEquals(parser.getString("o"), "\"test\"=.txt");
	}

	// Bug 6 -----------------------------
	@Test
	public void multipleOptionAssignmentTest() {
		parser.add("option", "o", Parser.STRING);
		parser.parse("--option=test --option=test2");
		assertEquals(parser.getString("option"), "test2");
	}
	
	@Test
	public void missingValuesTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("second", "p", Parser.STRING);
		parser.add("third", "t", Parser.STRING);
		parser.parse("--option=test --second=second");
		parser.parse("--third=third");
		assertEquals(parser.getString("third"), "third");
	}
	
	@Test
	public void nameAndShortcutTest() {
		parser.add("option", "o", Parser.STRING);
		parser.add("o", Parser.STRING);
		parser.parse("-o=test.txt");
		assertEquals(parser.getString("o"), "test.txt");
	}
	
	@Test
	public void fullNameAndShortcutTest() {
		parser.add("o", "p", Parser.STRING);
		parser.add("option", "o", Parser.STRING);
		parser.parse("--o=test.txt --option=oops.txt");
		assertEquals(parser.getString("o"), "test.txt");
	}
	
	@Test
	public void notDefinedStringTest() {
		parser.parse("option=test");
		assertEquals(parser.getString("option"), "");
	}
	
	@Test
	public void notDefinedIntegerTest() {
		parser.parse("option=10");
		assertEquals(parser.getInteger("option"), 0);
	}
	
	@Test
	public void notDefinedBooleanTest() {
		parser.parse("option=true");
		assertEquals(parser.getBoolean("option"), false);
	}
	
	@Test
	public void notDefinedCharTest() {
		parser.parse("option=t");
		assertEquals(parser.getChar("option"), '\0');
	}
	
	@Test
	public void emptyInputStringTest() {
		parser.add("option", Parser.STRING);
		parser.parse("");
		assertEquals(parser.getString("option"), "");
	}
	
}
