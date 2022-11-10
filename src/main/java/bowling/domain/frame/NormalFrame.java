package bowling.domain.frame;

import java.util.List;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameFrameRecord;
import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;

public class NormalFrame extends Frame {
    private static final String INVALID_FRAME_NUMBER_EXCEPTION_MESSAGE = "일반 프레임의 숫자는 1과 9 사이의 숫자여야 합니다.";

    private final int frameNumber;
    private State state;

    public NormalFrame(int frameNumber) {
        validate(frameNumber);
        this.frameNumber = frameNumber;
        this.state = new Ready();
    }

    @Override
    public void bowl(int falledPins) {
        this.state = state.bowl(falledPins);
    }

    @Override
    public Frame createNextFrame() {
        if (frameNumber + 1 == LAST_FRAME) {
            return new LastFrame();
        }

        return new NormalFrame(frameNumber + 1);
    }

    @Override
    public BowlingGameFrameRecord createFrameRecord() {
        return new BowlingGameFrameRecord(List.of(state.createScore()));
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public boolean isFrameFinish() {
        return state.isFinish();
    }

    private void validate(int frameNumber) {
        if (frameNumber < Frame.START_FRAME || frameNumber > Frame.LAST_FRAME - 1) {
            throw new IllegalArgumentException(INVALID_FRAME_NUMBER_EXCEPTION_MESSAGE);
        }
    }
}
