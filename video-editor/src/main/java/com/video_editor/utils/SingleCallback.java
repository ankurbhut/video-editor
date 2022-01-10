package com.video_editor.utils;

import com.video_editor.model.FrameEntity;

import java.util.List;

public interface SingleCallback<T, V> {
    void onSingleCallback(List<T> t, Integer v);
}
