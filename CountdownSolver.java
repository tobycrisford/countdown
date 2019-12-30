public class CountdownSolver
{
  public static String solve(int[] numbers, int target)
  {
    long x = 1, y = 0, total;
    boolean adding = false;
    for (int i = 0;i < numbers.length;i++)
    {
      x *= numbers[i];
      y += numbers[i];
    }
    if (x > y)
    {
      total = x;
    }
    else
    {
      total = y;
      adding = true;
    }
    if (total < target || 0 - total > target)
    {
      return "i";
    }
    else if (total == target || 0 - total == target)
    {
      String result = "";
      if (0 - total == target)
      {
        result += "- ";
      }
      result += Integer.toString(numbers[0]);
      if (adding)
      {
        for (int i = 1;i < numbers.length;i++)
        {
          result += " + " + Integer.toString(numbers[i]);
        }
      }
      else
      {
        for (int i = 1;i < numbers.length;i++)
        {
          result += " * " + Integer.toString(numbers[i]);
        }
      }
      return result;
    }
    for (int i = 0;i < numbers.length;i++)
    {
      if (numbers[i] == target)
      {
        return Integer.toString(numbers[i]);
      }
      else if (0 - numbers[i] == target)
      {
        return "- " + Integer.toString(numbers[i]);
      }
      if (numbers.length > 1)
      {
        int[] remainders = new int[numbers.length - 1];
        try
        {
          System.arraycopy(numbers, 0, remainders, 0, i);
        }
        catch (IndexOutOfBoundsException e)  {  }
        try
        {
          System.arraycopy(numbers, i + 1, remainders, i, remainders.length - i);
        }
        catch (IndexOutOfBoundsException e)  {  }
        String remainder = solve(remainders, target - numbers[i]);
        if (!remainder.equals("i"))
        {
          return Integer.toString(numbers[i]) + " + (" + remainder + ")";
        }
        remainder = solve(remainders, target + numbers[i]);
        if (!remainder.equals("i"))
        {
          return "(" + remainder + ") - " + Integer.toString(numbers[i]);
        }
        remainder = solve(remainders, target * numbers[i]);
        if (!remainder.equals("i"))
        {
          return "(" + remainder + ") / " + Integer.toString(numbers[i]);
        }
        if (numbers[i] != 0 && target % numbers[i] == 0)
        {
          remainder = solve(remainders, target / numbers[i]);
          if (!remainder.equals("i"))
          {
            return Integer.toString(numbers[i]) + " * (" + remainder + ")";
          }
        }
        else if (target != 0 && numbers[i] % target == 0)
        {
          remainder = solve(remainders, numbers[i] / target);
          if (!remainder.equals("i"))
          {
            return Integer.toString(numbers[i]) + " / (" + remainder + ")";
          }
        }
      }
    }
    return "i";
  }

  public static void main(String[] args)
  {
    int[] numbers = new int[args.length - 1];
    for (int i = 0;i < numbers.length;i++)
    {
      numbers[i] = Integer.parseInt(args[i]);
    }
    System.out.println(solve(numbers, Integer.parseInt(args[numbers.length])));
  }
}