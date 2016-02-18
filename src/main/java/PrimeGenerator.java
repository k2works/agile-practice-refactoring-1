/**
 * Created by k2works on 2016/02/15.
 * このクラスは素数を生成する。生成される素数の上限はユーザーが指定する。
 * ここで使用しているアルゴリズムは、「エラトステネスのふるい」法である。
 * このアルゴリズムは極めて単純である。２から始まる整数配列を与え、２の
 * 倍数をすべて消す。まだ消えていない次の整数を見つけ、その倍数を
 * 全て削除する。一番大きい数の平方根を超えるまで、この作業を繰り返す。
 *
 * @author k2works
 * @version 0.3.4
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
        int i;
        int j;
        for (i = 2; i < Math.sqrt(isCrossed.length) + 1; i++) {
            if (isCrossed[i] == false) // iが除かれていなければ、その倍数を除く
            {
                for (j = 2 * i; j < isCrossed.length; j += i)
                    isCrossed[j] = true; // 倍数は素数ではない
            }
        }
    }

    private static void putUncrossedIntegersIntoResult()
    {
        int i;
        int j;

        // 見つけた素数の個数をカウント
        int count = 0;
        for (i = 2; i < isCrossed.length; i++) {
            if (isCrossed[i] == false)
                count++; // カウントアップ
        }

        result = new int[count];

        // 素数の抜き出し
        for (i = 2, j = 0; i < isCrossed.length; i++) {
            if (isCrossed[i] == false) // 素数であれば
                result[j++] = i;
        }
    }
}
