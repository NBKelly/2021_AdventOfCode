package com.nbkelly.lib.bio;

import java.util.ArrayList;
import java.util.HashMap;
import com.nbkelly.lib.HashCounter;

/**
 * Metaclass for DNA/RNA sequences, defining the common algorithms of both
 */
public abstract class XNA extends Sequence {
    protected ArrayList<Nucleotide> sequence;

    /**
     * make a sequence from a string of characters. Subclasses are expected to sanity check.
     */
    public XNA(String sequence) {
	this.sequence = new ArrayList<Nucleotide>();
	for(int i = 0; i < sequence.length(); i++)
	    this.sequence.add(Nucleotide.fromChar(sequence.charAt(i)));
    }

    @Override public int hashCode() {
	return sequence.hashCode();
    }

    @Override public boolean equals(Object e) {
	if(e instanceof XNA)
	    return sequence.equals(((XNA)e).sequence);
	return false;
    }
    
    /**
     * Gets the complement of this nucleotide sequence
     * @return the complement of this nucleotide sequence
     */
    public abstract XNA complement();
    
    /**
     * Creates an XNA strain from a pre-defined sequence
     */
    protected XNA(ArrayList<Nucleotide> sequence) {
	this.sequence = new ArrayList<>(sequence);
    }
    
    /**
     * Get a count of all nucleotides within this sequence
     * @return a count of all nucleotides in this sequence
     */
    public HashMap<Nucleotide, Integer> count() {
	HashCounter<Nucleotide> hc = new HashCounter<Nucleotide>();
	for(Nucleotide n : sequence)
	    hc.add(n);

	return hc.toHashMap();
    }

    /**
     * Gets the sum of occurances of the given nucleotides in the sequence
     * @return the count of the given nucleotides in this sequence
     */
    public int getCount(Nucleotide... args) {
	HashCounter<Nucleotide> hc = new HashCounter<Nucleotide>();
	for(Nucleotide n : sequence)
	    hc.add(n);

	int res = 0;

	for(int i = 0; i < args.length; i++)
	    res += hc.count(args[i]);

	return res;
    }

    /**
     * Gets the percentage of this sequence made of up G and C nucleotides.
     * @return the percentage of this sequence made of up G and C nucleotides.
     */
    public double GC_Score() {
	return 100 * (double)getCount(Nucleotide.G, Nucleotide.C) / (double)sequence.size();
    }

    /**
     * Maps this nucleotide sequence to a string
     * @return this nucleotide sequence mapped to a string
     */
    public String toString() {
	StringBuilder sb = new StringBuilder();
	for(Nucleotide n : sequence)
	    sb.append(n.toString());

	return sb.toString();
    }
}
