package st;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Task3_TDD_1 {

	private Parser parser;
	
	@Before
	public void set_up() {
		parser = new Parser();
	}
	
	// Testing sameType helper function ----------------------------------------------------------
	
	@Test
	public void twoIntInput() {
		assertEquals(parser.sameType('1', '2'), true);
	}
	
	@Test
	public void twoCharLowercaseInput() {
		assertEquals(parser.sameType('a', 'b'), true);
	}
	
	@Test
	public void twoCharUppercaseInput() {
		assertEquals(parser.sameType('A', 'D'), true);
	}
	
	@Test
	public void twoSameCharInput() {
		assertEquals(parser.sameType('a', 'a'), true);
	}
	
	@Test
	public void twoSameIntInput() {
		assertEquals(parser.sameType('1', '1'), true);
	}
	
	@Test
	public void twoSymbolInput() {
		assertEquals(parser.sameType('*', '?'), false);
	}
	
	@Test
	public void twoSameSymbolInput() {
		assertEquals(parser.sameType('*', '*'), false);
	}
	
	@Test
	public void twoSameHyphensInput() {
		assertEquals(parser.sameType('-', '-'), false);
	}
	
	@Test
	public void mixedCaseInput() {
		assertEquals(parser.sameType('A', 'b'), true);
	}
	
	@Test
	public void mixedCaseSameInputInput() {
		assertEquals(parser.sameType('A', 'a'), true);
	}
	
	// Testing isBoundaryPair helper function ----------------------------------------------------

	@Test
	public void indexToBoundaryPair() {
		assertEquals(parser.isBoundaryPair(2, "abc-e"), true);
	}
	
	@Test
	public void indexToBoundaryPairUppercase() {
		assertEquals(parser.isBoundaryPair(2, "ABC-E"), true);
	}
	
	@Test
	public void indexToBoundaryPairMixedCase() {
		assertEquals(parser.isBoundaryPair(2, "Abc-E"), true);
	}
	
	
	@Test
	public void indexNotAtBoundaryPair() {
		assertEquals(parser.isBoundaryPair(1, "abc-e"), false);
	}
	
	@Test
	public void hyphenAtIndexBetweenIntAndIntAscending() {
		assertEquals(parser.isBoundaryPair(2, "123-7"), true);
	}
	
	@Test
	public void hyphenAtIndexBetweenIntAndIntDescending() {
		assertEquals(parser.isBoundaryPair(2, "485-1"), true);
	}
	
	@Test
	public void boundaryBetweenCharAndInt() {
		assertEquals(parser.isBoundaryPair(2, "abc-7"), false);
	}
	
	@Test
	public void boundaryBetweenSymbolAndSymbol() {
		assertEquals(parser.isBoundaryPair(1, "!!-?"), false);
	}
	
	@Test
	public void boundaryBetweenIntAndSymbol() {
		assertEquals(parser.isBoundaryPair(1, "!5-?"), false);
	}
	
	@Test
	public void boundaryBetweenCharAndSymbol() {
		assertEquals(parser.isBoundaryPair(1, "!c-?"), false);
	}
	
	@Test
	public void boundaryPairLongInput() {
		assertEquals(parser.isBoundaryPair(2, "abc-efghjiklmnopqrstuvwxyzABCDEFGHI"), true);
	}
	
	@Test
	public void noInput() {
		assertEquals(parser.isBoundaryPair(0, ""), false);
	}

	@Test
	public void multipleBoundaryPairs() {
		assertEquals(parser.isBoundaryPair(4, "123-7-9"), true);
	}
	
	@Test
	public void indexAtEndOfBoundaryPair() {
		assertEquals(parser.isBoundaryPair(4, "123-7"), false);
	}
	
	@Test
	public void noBoundaryPairsInString() {
		assertEquals(parser.isBoundaryPair(1, "abcd"), false);
	}
	
	@Test
	public void indexOutOfRange() {
		assertEquals(parser.isBoundaryPair(10, "abcd"), false);
	}
	
	@Test
	public void noBoundaryPairsInStringWithHyphen() {
		assertEquals(parser.isBoundaryPair(3, "abc-"), false);
	}
	
	@Test
	public void incompleteBoundaryPair() {
		assertEquals(parser.isBoundaryPair(0, "a-"), false);
	}
	
	@Test
	public void boundaryBetweenSameValuesInt() {
		assertEquals(parser.isBoundaryPair(0, "7-7"), true);
	}	
	
	@Test
	public void boundaryBetweenSameValuesChar() {
		assertEquals(parser.isBoundaryPair(0, "a-a"), true);
	}	
	
	// Testing isSpecialCharacter function ------------------------------------------------------
	@Test
	public void lowercaseCharInput() {
		assertEquals(parser.isSpecialCharacter('c'), false);
	}
	
	@Test
	public void uppercaseCharInput() {
		assertEquals(parser.isSpecialCharacter('C'), false);
	}
	
	@Test
	public void integerInput() {
		assertEquals(parser.isSpecialCharacter('8'), false);
	}
	
	@Test
	public void fullStopInput() {
		assertEquals(parser.isSpecialCharacter('.'), false);
	}
	
	@Test
	public void hyphenInput() {
		assertEquals(parser.isSpecialCharacter('-'), true);
	}
	
	@Test
	public void otherSymbolInput() {
		assertEquals(parser.isSpecialCharacter('#'), true);
	}
	
	@Test
	public void emptyInput() {
		assertEquals(parser.isSpecialCharacter(' '), true);
	}
	
	// Testing getCharacterList function ---------------------------------------------------------
	
	@Test
	public void optionNotGivenValue() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list= ");
		List<Character> expected = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void emptyOption() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--=a");
		List<Character> expected = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void orderOfSearch() {
		parser.add("list", "l", Parser.STRING);
		parser.add("l", "lo", Parser.STRING);
		parser.parse("-l=abc --l=abcd ");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void stringInput() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=abc");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void shortcutStringInput() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("-l=a-c");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("l"), expected);
	}
	
	@Test
	public void valueIncludingDigits() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=abc123");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c','1','2',
				'3'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueIncludingDigitsSpace() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list abc123");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c','1','2',
				'3'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueIncludingDigitsAndFullStop() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=abc123.");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c','1','2','3',
				'.'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void stringInputIncludingHyphenIncreasing() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-c");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void stringInputIncludingHyphenIncreasingDouble() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-c-e");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c','c','d',
				'e'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void stringInputIncludingHyphenIncreasingWholeAlphabet() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-z");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c','d','e','f',
				'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void stringInputIncludingHyphenDecreasing() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=c-a");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('c','b','a'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void stringInputIncludingHyphenDecreasingLargeRange() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=f-a");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('f','e','d','c','b',
				'a'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void shortcutStringInputIncludingHyphen() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("-l=a-c");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("l"), expected);
	}
	
	@Test
	public void includingForbiddenChars() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=abc!");
		List<Character> expected = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test 
	public void caseInsensitiveValueAllUppercase() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=ABC");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test 
	public void caseInsensitiveValueMixedCase() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=AbCdeF");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c','d','e',
				'f'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueStartingWithHyphen() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=-abc");
		List<Character> expected = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueStartingWithHyphenThenForbidden() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=-!abc");
		List<Character> expected = new ArrayList<Character>();
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueEndingWithHyphen() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=abc-");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','c'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithHyphenBetweenCharAndFullStop() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=ab-.");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','b','.'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithHyphenBetweenFullStopAndInteger() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=.-1");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('.','1'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithHyphenBetweenSameChars() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-a");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithHyphenBetweenSameCharsOverlapping() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-a-a");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a', 'a'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithHyphenBetweenSameCharsRepeating() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-aa-a");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a', 'a'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithDoubleHyphenBetweenChars() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a--c");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','c'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithAscendingThenDescendingRange() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=1-3-1");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('1','2','3','3','2',
				'1'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void valueWithSplitAscendingRange() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=1-4-8");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('1','2','3','4','4','5',
				'6','7','8'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void largestRangeInts() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=0-9");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('0','1','2','3','4','5',
				'6','7','8','9'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
	@Test
	public void largeRange() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list=a-a123");
		List<Character> expected = new ArrayList<Character>(Arrays.asList('a','1','2','3'));
		assertEquals(parser.getCharacterList("list"), expected);
	}
	
}
