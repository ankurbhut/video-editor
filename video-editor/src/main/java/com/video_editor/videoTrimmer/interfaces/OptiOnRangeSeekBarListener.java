/*
 *
 *  Created by Optisol on Aug 2019.
 *  Copyright © 2019 Optisol Business Solutions pvt ltd. All rights reserved.
 *
 */

package com.video_editor.videoTrimmer.interfaces;

import com.video_editor.videoTrimmer.view.OptiRangeSeekBarView;

public interface OptiOnRangeSeekBarListener {
    void onCreate(OptiRangeSeekBarView rangeSeekBarView, int index, float value);

    void onSeek(OptiRangeSeekBarView rangeSeekBarView, int index, float value);

    void onSeekStart(OptiRangeSeekBarView rangeSeekBarView, int index, float value);

    void onSeekStop(OptiRangeSeekBarView rangeSeekBarView, int index, float value);
}
