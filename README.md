ExpressionMorphology
====================

Morphology project extended to phrase level

Project uses Morphology and LVTagger projects

Project contains 3 classes:
1) ExpressionWord - uses Word class, added attribute isStatic, which indicates, whether to inflect the Word
2) Category - contains category names
3) Expression - contains whole named entity, adds Category and isStatic attribute to each Word. Main method: String inflect(String inflection, String category) //category can be null

Test cases and examples of use in AnalyzerTest
