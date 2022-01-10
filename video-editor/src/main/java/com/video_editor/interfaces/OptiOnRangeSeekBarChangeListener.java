package com.video_editor.interfaces;

import com.video_editor.utils.OptiCustomRangeSeekBar;

public interface OptiOnRangeSeekBarChangeListener {
    void onCreate(OptiCustomRangeSeekBar CustomRangeSeekBar, int index, float value);

    void onSeek(OptiCustomRangeSeekBar CustomRangeSeekBar, int index, float value);

    void onSeekStart(OptiCustomRangeSeekBar CustomRangeSeekBar, int index, float value);

    void onSeekStop(OptiCustomRangeSeekBar CustomRangeSeekBar, int index, float value);
}
