package com.langrsoft.slides;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnAuto {
    private Auto auto;

    @BeforeEach
    void setUp() {
        auto = new Auto();
    }

    @Test
    void idlesEngineWhenStarted() {
        auto.DepressBrake();

        auto.PressStartButton();

        assertThat(auto.RPM()).isGreaterThan(950).isLessThan(1100);
    }

    @Test
    void doesNotStartWhenBrakeNotDepressed() {
        assertThrows(BrakeNotEngagedException.class, () ->
                auto.PressStartButton());
    }


    static class Auto {

        private boolean brakeDepressed;

        public void DepressBrake() {
            brakeDepressed = true;
        }

        public void PressStartButton() {
            if (!brakeDepressed) {
                throw new BrakeNotEngagedException();
            }
        }

        public int RPM() {
            return 1000;
        }
    }

    private static class BrakeNotEngagedException extends RuntimeException {
    }
}
