package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    public void getScoreWhenStrike() {
        Score score = new Score(10, 2);
        assertThat(score.add(10).add(9).getValue()).isEqualTo(29);
    }

    @Test
    public void getScoreWhenSpare() {
        Score score = new Score(10, 1);
        assertThat(score.add(9).getValue()).isEqualTo(19);
    }

    @Test
    public void getScoreWhenMiss() {
        Score score = new Score(7, 0);
        assertThat(score.getValue()).isEqualTo(7);
    }
}
