import java.util.*;
import java.io.*;

class ModuloArithmetic {
    static final long MOD = 1000000007;

    // Modular multiplication
    static long multiply(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }

    // Modular exponentiation
    static long power(long a, long b) {
        long result = 1;
        a = a % MOD;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % MOD;
            }
            a = (a * a) % MOD;
            b = b >> 1;
        }
        return result;
    }

    // Modular inverse using Fermat's theorem
    static long modInverse(long a) {
        return power(a, MOD - 2);
    }
}

class Main {
    static long[] factorial;
    static long[] inverseFactorial;

    // Precompute factorials and their inverses
    static void precompute(int maxN) {
        factorial = new long[maxN + 1];
        inverseFactorial = new long[maxN + 1];
        factorial[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            factorial[i] = ModuloArithmetic.multiply(factorial[i - 1], i);
        }
        inverseFactorial[maxN] = ModuloArithmetic.modInverse(factorial[maxN]);
        for (int i = maxN - 1; i >= 0; i--) {
            inverseFactorial[i] = ModuloArithmetic.multiply(inverseFactorial[i + 1], i + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        precompute(1000000); // Precompute for max N = 10^6
        while (T-- > 0) {
            int N = sc.nextInt();
            System.out.println(inverseFactorial[N]);
        }
    }
}
