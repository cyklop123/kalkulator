
import java.util.List;

public class Sqrt implements IFunction {
    public Sqrt() {
    }

    public double eval(List<Double> var1) {
        return Math.sqrt((Double)var1.get(0));
    }

    public int getParamCount() {
        return 1;
    }
}
