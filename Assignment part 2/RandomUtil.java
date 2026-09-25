public final class RandomUtil {
    private static final java.util.Random RANDOM = new java.util.Random();

    private RandomUtil() {
    }

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    public static double nextDouble() {
        return RANDOM.nextDouble();
    }
}