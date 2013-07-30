package expressionMorphology.Testi;

import static org.junit.Assert.*;
import org.junit.Test;

import expressionMorphology.analyzer.Expression;
import expressionMorphology.analyzer.ExpressionWord;

public class AnalyzerTest
{
//Ģeogrāfisko nosaukumu testi
	@Test
	public void lietvLietv() throws Exception
	{
		Expression e = new Expression("Mākslas akadēmija");
		e.addPattern("geo");
		for (ExpressionWord w : e.expWords)
		{
			System.out.println(w.isStatic+"\n");
		}
	}
	
	@Test
	public void ipvLietv() throws Exception
	{
		Expression e = new Expression("kaķis");
		e.addPattern("geo");
		for (ExpressionWord w : e.expWords)
		{
			System.out.println(w.isStatic+"\n");
		}
		System.out.println(e.inflect("Ģenetīvs", "geo"));
	}
	
	@Test
	public void pedinaLietvpedina() throws Exception
	{
		Expression e = new Expression("Skaista Latvijas Zaļā bibliotēka");
		
	}
	
	
	
	
//Cilvēku vārdu un uzvārdu testi
}
