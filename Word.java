public class Word{

   String word;
   char[] alphaWord;
   int wordValue;

   public Word(String word){
      this.word = word;
      alphaWord = charSorter(stringConverter(word));
      wordValue = valueOf(alphaWord);
   }

   private int valueOf(char[] alphaWord){
      for(int i = 0; i < word.length(); i ++)
         wordValue = wordValue + Character.getNumericValue(alphaWord[i]);
      return(wordValue);
   }

   private char[] stringConverter(String word){
      char[] charWord = new char[word.length()];
      for(int i = 0; i < word.length(); i ++){
         charWord[i] = word.toLowerCase().charAt(i);
      }
      return(charWord);
   }
      
   private char[] charSorter(char[] charWord){
      int min;
      char temp;
      for(int i = 0; i < charWord.length - 1; i ++){
         min = i;
         for(int j = i + 1; j < charWord.length; j ++){
            if(charWord[j] < charWord[min])
               min = j;
            temp = charWord[i];
            charWord[i] = charWord[min];
            charWord[min] = temp;
         }
      }
      return(charWord);
   }
   
   public int getValue(){
      return(wordValue);
   }
   
   public int compareTo(Word word){
   int otherValue = word.getValue();
      if(this.getValue() < otherValue)
         return(0);
      else if(this.getValue() > otherValue)
         return(-1);
      else
         return(1);
   }
}