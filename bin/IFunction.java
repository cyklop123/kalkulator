
import java.util.List;

public interface IFunction
{
    int paramCount = 0;
    public double eval(List<Double> param);
    public int getParamCount();
}
