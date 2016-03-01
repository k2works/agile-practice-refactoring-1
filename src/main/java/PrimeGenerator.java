/**
 * Created by k2works on 2016/02/15.
 * このクラスは素数を生成する。生成される素数の上限はユーザーが指定する。
 * ここで使用しているアルゴリズムは、「エラトステネスのふるい」法である。
 * このアルゴリズムは極めて単純である。２から始まる整数配列を与え、２の
 * 倍数をすべて消す。まだ消えていない次の整数を見つけ、その倍数を
 * 全て削除する。一番大きい数の平方根を超えるまで、この作業を繰り返す。
 *
 * @author k2works
 * @version 1.0.0
 */
public class PrimeGenerator {
    private static boolean[] crossedOut;
    private static int[] result;

    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2)
            return new int[0];
        else
        {
            uncrossIntegersUpto(maxValue);
            crossOutMultiples();
            putUncrossedIntegersIntoResult();
            return result;
        }
    }

    private static void uncrossIntegersUpto(int maxValue)
    {
        crossedOut = new boolean[maxValue + 1];
        for (int i = 2; i < crossedOut.length; i++)
            crossedOut[i] = false;
    }

    private static void crossOutMultiples()
    {
        int maxPrimeFactor = calcMaxPrimeFactor();
        for (int i = 2; i <= maxPrimeFactor; i++) {
            if (notCrossed(i))
                crossOutMultiplesOf(i);
        }
    }

    private static int calcMaxPrimeFactor() {
        // 配列に格納されているいかなる倍数も、その配列サイズの平方根に
        // 等しいか、それよりも小さい素数因子を持っている。したがって、
        // その平方根よりも大きな数の倍数をチェックする必要はない。
        double iterationLimit = Math.sqrt(crossedOut.length);
        return (int) iterationLimit;
    }

    private static boolean notCrossed(int i) {
        return crossedOut[i] == false;
    }

    private static void crossOutMultiplesOf(int i) {
        for (int multiple = 2 * i;
             multiple < crossedOut.length;
             multiple += i)
            crossedOut[multiple] = true;
    }

    private static void putUncrossedIntegersIntoResult()
    {
        result = new int[numberOfUncrossedIntegers()];
        for (int j = 0, i = 2; i < crossedOut.length; i++) {
            if (notCrossed(i))
                result[j++] = i;
        }
    }

    private static int numberOfUncrossedIntegers() {
        int count = 0;
        for (int i = 2; i < crossedOut.length; i++)
            if (notCrossed(i))
                count++;
        return count;
    }
}
