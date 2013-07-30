
package expressionMorphology.analyzer;

import lv.semti.morphology.analyzer.Word;

/**
 * @author Ginta
 *
 */
public class ExpressionWord
{
	public boolean isStatic;
	public Word word;
	
	ExpressionWord(Word w)
	{
		this.word=w;
		isStatic=false;
	}
	
}