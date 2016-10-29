package nkt;

import java.io.*;
import java.util.*;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.tanh;

/**
 * Created by stbe on 21.10.2016.
 */
public class Lab1 {

    public static Double getU(Map<Double, Double> xw)
    {
        Double u = 0.0;
        for (Double x : xw.keySet())
        {
            u += x * xw.get(x);
        }
        return u;
    }

    public static Map<Double,Double> fillXW(List<Double> x, List<Double> y)
    {
        Map<Double,Double> xw = new HashMap<Double,Double>();
        if (x.size() != y.size())
        {
            throw new InputException("Dimension vectors X and Y are not equal!");
        }
        if (x == null || y == null)
        {
            throw new InputException("x or y don`t exist");
        }
        for (int i = 0; i < x.size(); i++)
        {
            xw.put(x.get(i), y.get(i));
        }
        return xw;
    }

    public static Double getHevisaid(Double u)
    {
        if (u >= 0)
        {
            return 1.0;
        }
        else
        {
            return 0.0;
        }
    }

    public static Double getPiecewiseLinear(Double a, Double u)
    {
        if (u >= a)
        {
            return 1.0;
        }
        else if (a > u || u > -a)
        {
            return (u / 2 * a) + 1 / 2.0;
        }
        else
        {
            return 0.;
        }
    }

    public static Double getSigmoid(Double u, Double alpha)
    {
        return 1 / (1 + exp(-alpha * u));
    }

    public static Double getSigmoidBipol(Double u, Double alpha)
    {
        return tanh(alpha * u);
    }

    private static void exists(String fileName) throws FileNotFoundException
    {
        File file = new File(fileName);
        if (!file.exists())
        {
            throw new FileNotFoundException(file.getName());
        }
    }

    public static void main(String[] args) throws IOException
    {
        System.out.println("Input number of function and file with X Y \n " +
                "example 0 1.txt\n" +
                "numeric of functions:\n" +
                "0-hevisaid\n" +
                "1-piecewiseLinear\n"+
                "2-simoid\n" +
                "3-sigmoidBipol\n"+
                "4-pow\n");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        byte[] buf = input.getBytes();
        StringBuffer sb = new StringBuffer();
        char numberOfFunction = (char)buf[0];

        for(int i = 1; i<buf.length;i++)
        {
            if (32 == buf[i])
            {
                sb.delete(0, sb.length());
                continue;
            }
            sb.append((char)buf[i]);
        }
        String fileName = sb.toString();
        Double u = null;//= getU(readFile(fileName));
        Double result = getResult(numberOfFunction,u);
        System.out.println("result = "+result);
    }

    public static Map<Double,Double> readFile(File file)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            FileInputStream fin = new FileInputStream(file);
            try
            {
                byte[] buffer = new byte[fin.available()];
                fin.read(buffer, 0, fin.available());
                List<Double> x = new LinkedList<>();
                List<Double> y = new LinkedList<>();
                boolean flag = true;
                for (int i = 0; i < buffer.length; i++)
                {
                    System.out.println("i="+i);
                    if (32 == buffer[i]||9 == buffer[i])
                    {
                        flag = fillXY(flag,x,y,sb);
                        continue;
                    }
                    if (10 == buffer[i])
                    {
                        flag = fillXY(flag,x,y,sb);
                        continue;
                    }
                    sb.append((char)buffer[i]);
                }
                 return fillXW(x,y);
            }
            finally
            {
                fin.close();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean fillXY(boolean flag, List<Double> x, List<Double> y,StringBuilder sb)
    {
        if(sb.length()!=0)
        {
            if(flag)
            {
                x.add(Double.parseDouble(sb.toString()));
                flag = false;
            }
            else
            {
                y.add(Double.parseDouble(sb.toString()));
                flag = true;
            }
            sb.delete(0, sb.length());
        }
        return flag;
    }
    private static Double getResult(char c , Double u)
    {
        if(c=='0')
        {
            return getHevisaid(u);
        }
        else if(c=='1')
        {
            System.out.println("Input A");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Double a = Double.parseDouble(s);
            return getPiecewiseLinear(a,u);
        }
        else if(c=='2')
        {
            System.out.println("Input alpha");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Double alpha = Double.parseDouble(s);
            return getSigmoid(u,alpha);
        }
        else if(c=='3')
        {
            System.out.println("Input alpha");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Double alpha = Double.parseDouble(s);
            return getSigmoidBipol(u,alpha);
        }
        else if(c=='4') {
            System.out.println("Input k");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Double k = Double.parseDouble(s);
            return pow(u,k);
        }
        return  null;
    }
}
