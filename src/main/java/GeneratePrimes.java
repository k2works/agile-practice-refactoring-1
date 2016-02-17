/**
 * Created by k2works on 2016/02/15.
 * このクラスは素数を生成する。生成される素数の上限はユーザーが指定する。
 * ここで使用しているアルゴリズムは、「エラトステネスのふるい」法である。
 * このアルゴリズムは極めて単純である。２から始まる整数配列を与え、２の
 * 倍数をすべて消す。まだ消えていない次の整数を見つけ、その倍数を
 * 全て削除する。一番大きい数の平方根を超えるまで、この作業を繰り返す。
 *
 * @author k2works
 * @version 0.1.0
 */
public class GeneratePrimes {
    /**
     * @param maxValue は、生成する素数の上限
     */
    public static int[] generatePrimes(int maxValue) {
        if (maxValue >= 2) // 有効なケース
        {
            // 宣言
            int s = maxValue + 1; // 配列サイズ
            boolean[] f = new boolean[s];
            int i;

            // 配列を真(true)に初期化
            for (i = 0; i < s; i++)
                f[i] = true;

            // 周知の非素数を取り除く
            f[0] = f[1] = false;

            // ふるい落とす
            int j;
            for (i = 2; i < Math.sqrt(s) + 1; i++) {
                if (f[i]) // iが除かれていなければ、その倍数を除く
                {
                    for (j = 2 * i; j < s; j += i)
                        f[j] = false; // 倍数は素数ではない
                }
            }
            // 見つけた素数の個数をカウント
            int count = 0;
            for (i = 0; i < s; i++) {
                if (f[i])
                    count++; // カウントアップ
            }

            int[] primes = new int[count];

            // 素数の抜き出し
            for (i = 0, j = 0; i < s; i++) {
                if (f[i]) // 素数であれば
                    primes[j++] = i;
            }
            return primes; // 素数を返す
        } else // maxValue < 2
            return new int[0]; // 入力が不適切な場合null配列を戻す
    }
}
