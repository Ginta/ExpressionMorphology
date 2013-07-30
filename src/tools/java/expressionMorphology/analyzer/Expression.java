
package expressionMorphology.analyzer;
import lv.semti.morphology.analyzer.*;
import lv.semti.morphology.attributes.AttributeNames;
import lv.semti.morphology.attributes.AttributeValues;

import java.util.*;

/**
 * @author Ginta
 * 
 */
public class Expression
{
	public LinkedList <ExpressionWord> expWords;
	Category cat;
	Analyzer locītājs = new Analyzer("../Morphology/dist/Lexicon.xml");
	
	public Expression(String phrase) throws Exception
	{
		LinkedList <Word> words = Splitting.tokenize(locītājs, phrase);
		expWords=new LinkedList<ExpressionWord>();
		for (Word w : words)
		{
			ExpressionWord tmp = new ExpressionWord(w);
			expWords.add(tmp);
		}
	}
	
	public void addPattern(String c) //Method adds isStatic attribute to the Expression word, which indicates, whether to inflect the Word
	{
		boolean staticWhile=false;
		cat=get(c);

		switch(cat)
		{
			case geo :
			{
				for (ExpressionWord w : expWords)
				{
					if(w.word.isRecognized()==false)
					{
						w.isStatic=true;
						continue;
					}
					
					switch(w.word.getBestWordform().getValue("Vārdšķira"))
					{
						case "Lietvārds":
						{
							if(staticWhile || expWords.lastIndexOf(w)!=expWords.size()-1) 
							{
								w.isStatic=true;
								break;
							}
							w.isStatic=false;
							break;
						}
						case "Īpašības vārds":
						{
							if(staticWhile) 
							{
								w.isStatic=true;
								break;
							}
							w.isStatic=false;
							break;
						}
						case "Pieturzīme":
						{
							w.isStatic=true;
							staticWhile=!staticWhile;
							break;
						}
					}
				}
				break;
			}
			
			case hum :
			{
				break;
			}
		}
	}
	
	public String normalize(String inflect) throws Exception
	{
		return inflect(inflect,null);
	}
	
	public String inflect(String inflect, String cat) throws Exception
	{
		addPattern(cat);
		
		String inflectedPhrase="";
		AttributeValues filtrs;
		HashMap<String,String> attribute_map;
		Wordform forma;
		for(ExpressionWord w : expWords)
		{
			if(w.isStatic==false)
			{
				forma=w.word.getBestWordform();
				attribute_map=forma.attributes;
				attribute_map.put("Locījums",inflect);
				filtrs=new AttributeValues();
				filtrs.addAttributes(attribute_map);
				inflectedPhrase+=locītājs.generateInflections(forma.getValue("Pamatforma"),false,filtrs).toString()+' ';
			}
			else
			{
				inflectedPhrase+=w.word.getToken()+' ';
			}
		}
		
		return inflectedPhrase.trim();
	}
	
	public static Category get(String s)
	{
		switch(s)
		{
		case "geo":
			return Category.geo;
			default:
				return null;
		}
	}
}
	


