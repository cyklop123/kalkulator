
import java.util.List;

public interface IFunction //nazwa klasy implikuje nazwe uzyta w ciagu wejsciowym; może zawierać tylko litery
{
    int paramCount = 0;
    public double eval(List<Double> param);
    public int getParamCount();
}
