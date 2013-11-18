public class SortValue implements java.util.Comparator {

   public int compare(Object o1, Object o2){
      return(((Word)o1).getValue().compareTo((((Word)o2).getValue())));
   }
}