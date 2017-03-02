package com.progressoft.jip.paymentsproject.accounts;

import java.util.Iterator;
import java.util.LinkedList;

public class IBANStructurePattern {
	
	public enum IBANPatternType {
		NUMERIC, ALPHABETIC, ALPHANUMERIC;
	}
	private LinkedList<Pair<IBANPatternType, Integer>> patternSet = new LinkedList<>();
	
	private String countryCode;
	private int ibanLength;
	
	public int getIbanLength() {
		return ibanLength;
	}

	public void setIbanLength(int ibanLength) {
		this.ibanLength = ibanLength;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public void addPattern(IBANPatternType type, int patternLength) {
		patternSet.add(new Pair<IBANPatternType, Integer>(type, patternLength));
	}
	
	public Iterator<Pair<IBANPatternType, Integer>> getPatterns() {
		return patternSet.iterator();
	}
	
	public class Pair<P,L> {
		private P pattern;
		private L length;
		
		public Pair(P pattern, L length) {
			this.pattern = pattern;
			this.length = length;
		}
		public P getPattern() {
			return pattern;
		}
		public void setPattern(P pattern) {
			this.pattern = pattern;
		}
		public L getLength() {
			return length;
		}
		public void setLength(L length) {
			this.length = length;
		}
	}
}
