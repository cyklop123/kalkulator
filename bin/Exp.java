
import java.util.List;

public class Exp implements IFunction {

    public double eval(List<Double> var1) {
        return Math.exp(var1.get(0));
    }

    public int getParamCount() {
        return 1;
    }
}
