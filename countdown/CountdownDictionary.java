package countdown;

import java.util.*;

public class CountdownDictionary
{
  Hashtable letters;
  String word;
  int length;

  public CountdownDictionary()
  {
    letters = new Hashtable(35);
    word = "";
    length = 0;
  }

  public void add(String word, String key)
  {
    if (key.equals(""))
    {
      this.word = word;
    }
    else
    {
      String index = key.substring(0, 1);
      key = key.substring(1);
      CountdownDictionary sub = (CountdownDictionary) (letters.get(index));
      if (sub == null)
      {
        sub = new CountdownDictionary();
        letters.put(index, sub);
      }
      sub.add(word, key);
    }
    if (word.length() > length)
    {
      length = word.length();
    }
  }

  public String solve(String check)
  {
    String longest = word;
    for (int i = 0;i < check.length();i++)
    {
      while (i < check.length() - 1)
      {
        if (check.substring(i + 1).indexOf(check.substring(i, i + 1)) == -1)
        {
          break;
        }
        else
        {
          i++;
        }
      }
      CountdownDictionary sub = (CountdownDictionary) (letters.get(check.substring(i, i + 1)));
      if (sub != null && longest.length() < sub.getLongest())
      {
        String temp;
        if (i < check.length() - 1)
        {
          temp = sub.solve(check.substring(0, i) + check.substring(i + 1));
        }
        else
        {
          temp = sub.solve(check.substring(0, i));
        }
        if (temp.length() > longest.length())
        {
          longest = temp;
        }
      }
    }
    return longest;
  }

  public int getLongest()
  {
    return length;
  }
}