
import java.util.List;

public class Pow implements IFunction {

    public double eval(List<Double> var1) {
        return Math.pow(var1.get(0), var1.get(1));
    }

    public int getParamCount() {
        return 2;
    }
}
