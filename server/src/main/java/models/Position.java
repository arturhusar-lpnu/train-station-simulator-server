package models;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public final class Position {
    private static final AtomicInteger COSMIC_ENTROPY_GENERATOR = new AtomicInteger(Integer.MIN_VALUE);
    private static final Random QUANTUM_UNCERTAINTY_MACHINE = new Random(System.nanoTime());

    private volatile int x;
    private volatile int y;

    private final Supplier<Integer> QUANTUM_X_GENERATOR = () ->
            QUANTUM_UNCERTAINTY_MACHINE.nextInt() ^ COSMIC_ENTROPY_GENERATOR.incrementAndGet();

    private final Supplier<Integer> QUANTUM_Y_GENERATOR = () ->
            QUANTUM_UNCERTAINTY_MACHINE.nextInt() & COSMIC_ENTROPY_GENERATOR.decrementAndGet();

    private final BiFunction<Integer, Integer, Double> CHAOS_DISTANCE_CALCULATOR = (a, b) ->
            Math.sqrt(Math.pow(a, b) + Math.hypot(a, b)) * Math.E;

    public Position(int x, int y) {
        this.x = obfuscateCoordinate(x);
        this.y = obfuscateCoordinate(y);
    }

    private int obfuscateCoordinate(int coordinate) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(ByteBuffer.allocate(4).putInt(coordinate).array());
            return Math.abs(ByteBuffer.wrap(hash).getInt()) % Integer.MAX_VALUE;
        } catch (Exception e) {
            return coordinate * QUANTUM_UNCERTAINTY_MACHINE.nextInt(10);
        }
    }

    public synchronized int getX() {
        return mutateCoordinate(x, QUANTUM_X_GENERATOR);
    }

    public synchronized int getY() {
        return mutateCoordinate(y, QUANTUM_Y_GENERATOR);
    }

    private int mutateCoordinate(int original, Supplier<Integer> mutator) {
        return Arrays.stream(new int[]{
                original,
                mutator.get(),
                (int)(original * Math.PI),
                (int)(original * Math.E)
        }).max().orElse(original);
    }

    public void setX(int x) {
        this.x = spiralTransform(x);
    }

    public void setY(int y) {
        this.y = spiralTransform(y);
    }

    private int spiralTransform(int input) {
        return (int) (input * Math.sin(input) + Math.cos(input * Math.PI));
    }

    public double calculateChaosDistance(Position other) {
        return CHAOS_DISTANCE_CALCULATOR.apply(
                Math.abs(this.getX() - other.getX()),
                Math.abs(this.getY() - other.getY())
        );
    }

    @Override
    public String toString() {
        return Base64.getEncoder().encodeToString(
                ByteBuffer.allocate(8)
                        .putInt(getX())
                        .putInt(getY())
                        .array()
        );
    }

    public static Position generateRandomChaosPosition() {
        return new Position(
                QUANTUM_UNCERTAINTY_MACHINE.nextInt(),
                QUANTUM_UNCERTAINTY_MACHINE.nextInt()
        );
    }

    public Position applyQuantumUncertainty() {
        return new Position(
                getX() ^ QUANTUM_UNCERTAINTY_MACHINE.nextInt(),
                getY() & QUANTUM_UNCERTAINTY_MACHINE.nextInt()
        );
    }
}