package main;

import java.util.Scanner;
import java.io.PrintWriter;

import static main.BenderBRodriguez11507.Position.X_PLUS;

public class BenderBRodriguez11507 {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int l = in.nextInt();

        if (0 == l) {
            throw new UnknownError();
        }

        Position currentPos = X_PLUS;
        for (int i = 0; i < l - 1; i++) {
            currentPos = currentPos.bend(Position.fromRep(in.next()));
        }

        out.println(currentPos);
    }

    enum Position {
        NO("No") {
            @Override
            public Position bend(Position direction) {
                return direction;
            }
        },
        X_PLUS("+x") {
            @Override
            public Position bend(Position direction) {
                switch (direction) {
                    case Y_PLUS:
                        return Y_PLUS;
                    case Y_MINUS:
                        return Y_MINUS;
                    case Z_PLUS:
                        return Z_PLUS;
                    case Z_MINUS:
                        return Z_MINUS;
                    case NO:
                        return this;
                    default:
                        throw new IllegalStateException("Unrecognized transition to " + direction);
                }
            }
        },
        X_MINUS("-x") {
            @Override
            public Position bend(Position direction) {
                switch (direction) {
                    case Y_PLUS:
                        return Y_MINUS;
                    case Y_MINUS:
                        return Y_PLUS;
                    case Z_PLUS:
                        return Z_MINUS;
                    case Z_MINUS:
                        return Z_PLUS;
                    case NO:
                        return this;
                    default:
                        throw new IllegalStateException("Unrecognized transition to " + direction);
                }
            }
        },
        Y_PLUS("+y") {
            @Override
            public Position bend(Position direction) {
                switch (direction) {
                    case Y_PLUS:
                        return X_MINUS;
                    case Y_MINUS:
                        return X_PLUS;
                    case Z_PLUS:
                        return Y_PLUS;
                    case Z_MINUS:
                        return Y_PLUS;
                    case NO:
                        return this;
                    default:
                        throw new IllegalStateException("Unrecognized transition to " + direction);
                }
            }
        },
        Y_MINUS("-y") {
            @Override
            public Position bend(Position direction) {
                switch (direction) {
                    case Y_PLUS:
                        return X_PLUS;
                    case Y_MINUS:
                        return X_MINUS;
                    case Z_PLUS:
                        return Y_MINUS;
                    case Z_MINUS:
                        return Y_MINUS;
                    case NO:
                        return this;
                    default:
                        throw new IllegalStateException("Unrecognized transition to " + direction);
                }
            }
        },
        Z_PLUS("+z") {
            @Override
            public Position bend(Position direction) {
                switch (direction) {
                    case Y_PLUS:
                        return Z_PLUS;
                    case Y_MINUS:
                        return Z_PLUS;
                    case Z_PLUS:
                        return X_MINUS;
                    case Z_MINUS:
                        return X_PLUS;
                    case NO:
                        return this;
                    default:
                        throw new IllegalStateException("Unrecognized transition to " + direction);
                }
            }
        },
        Z_MINUS("-z") {
            @Override
            public Position bend(Position direction) {
                switch (direction) {
                    case Y_PLUS:
                        return Z_MINUS;
                    case Y_MINUS:
                        return Z_MINUS;
                    case Z_PLUS:
                        return X_PLUS;
                    case Z_MINUS:
                        return X_MINUS;
                    case NO:
                        return this;
                    default:
                        throw new IllegalStateException("Unrecognized transition to " + direction);
                }
            }
        };

        private String rep;

        Position(String rep) {
            this.rep = rep;
        }

        public abstract Position bend(Position direction);

        public static Position fromRep(String rep) {
            switch (rep) {
                case "+y":
                    return Y_PLUS;
                case "-y":
                    return Y_MINUS;
                case "+z":
                    return Z_PLUS;
                case "-z":
                    return Z_MINUS;
                case "No":
                    return NO;
                default:
                    throw new IllegalArgumentException("Unrecognized representation  " + rep);
            }
        }

        @Override
        public String toString() {
            return this.rep;
        }
    }
}
