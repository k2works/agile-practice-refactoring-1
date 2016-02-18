/**
 * Created by k2works on 2016/02/15.
 * このクラスは素数を生成する。生成される素数の上限はユーザーが指定する。
 * ここで使用しているアルゴリズムは、「エラトステネスのふるい」法である。
 * このアルゴリズムは極めて単純である。２から始まる整数配列を与え、２の
 * 倍数をすべて消す。まだ消えていない次の整数を見つけ、その倍数を
 * 全て削除する。一番大きい数の平方根を超えるまで、この作業を繰り返す。
 *
 * @author k2works
 * @version 0.4.0
 */
public class PrimeGenerator {
    private static boolean[] isCrossed;
    private static int[] result;

    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2)
            return new int[0];
        else
        {
            initializeArrayOfIntegers(maxValue);
            crossOutMultiples();
            putUncrossedIntegersIntoResult();
            return result; // 素数を返す
        }
    }

    private static void initializeArrayOfIntegers(int maxValue)
    {
        // 宣言
        isCrossed = new boolean[maxValue + 1];
        int i;

        // 配列を偽(false)に初期化
        for (i = 2; i < isCrossed.length; i++)
            isCrossed[i] = false;
    }

    private static void crossOutMultiples()
    {
        int maxPrimeFactor = calcMaxPrimeFactor();
        for (int i = 2; i <= maxPrimeFactor; i++) {
            if (notCrossed(i)) // iが除かれていなければ、その倍数を除く
                crossOutMultiplesOf(i);
        }
    }

    private static int calcMaxPrimeFactor() {
        // pの倍数をすべて削除する。ただし、pは素数である。したがって、
        // 削除される倍数はすべて、素因子pと倍因子qを掛けあわせた数
        // として表現できる。もしpが配列サイズの平方根よりも大きい場合は、
        // 倍数因子qが１より大きくなることはありえない。したがって、pは
        // 配列に格納されている数の中で最大の素数因子であり、同時に
        // 繰り返しの上限であることになる。
        double maxPrimeFactor = Math.sqrt(isCrossed.length) + 1;
        return (int) maxPrimeFactor;
    }

    private static boolean notCrossed(int i) {
        return isCrossed[i] == false;
    }

    private static void crossOutMultiplesOf(int i) {
        for (int multiple = 2 * i;
             multiple < isCrossed.length;
             multiple += i)
            isCrossed[multiple] = true; // 倍数は素数ではない
    }

    private static void putUncrossedIntegersIntoResult()
    {
        int i;
        int j;

        // 見つけた素数の個数をカウント
        int count = 0;
        for (i = 2; i < isCrossed.length; i++) {
            if (notCrossed(i))
                count++; // カウントアップ
        }

        result = new int[count];

        // 素数の抜き出し
        for (i = 2, j = 0; i < isCrossed.length; i++) {
            if (notCrossed(i)) // 素数であれば
                result[j++] = i;
        }
    }
}
