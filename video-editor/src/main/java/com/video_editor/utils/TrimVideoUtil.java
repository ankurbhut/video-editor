package com.video_editor.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;

import com.video_editor.model.FrameEntity;
import com.video_editor.utils.SingleCallback;
import com.video_editor.videoTrimmer.utils.OptiBackgroundExecutor;

import java.util.ArrayList;
import java.util.List;

public class TrimVideoUtil {
    public void backgroundShootVideoThumb(final Context context, final Uri videoUri, final int imageSize, final long width, final int totalThumbsCount, final long startPosition,
                                          final long endPosition, final SingleCallback<Bitmap, Integer> callback) {
        Log.d("backgroundShootVideo", videoUri + " - " + imageSize + " - " + width + " - " + totalThumbsCount + " - " + startPosition + " - " + endPosition);
        OptiBackgroundExecutor.execute(new OptiBackgroundExecutor.Task("", 0L, "") {
            @Override
            public void execute() {
                try {
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    mediaMetadataRetriever.setDataSource(context, videoUri);
                    long interval = (endPosition - startPosition) / (totalThumbsCount - 1);
                    List<Bitmap> bitmapList = new ArrayList<>();
                    Log.d("interval", "interval" + interval);
                    for (long i = 0; i < totalThumbsCount; ++i) {
                        long frameTime = startPosition + interval * i;
                        Log.d("frameTime", "frameTime" + frameTime);
                        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(frameTime * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                        try {
                            bitmap = Bitmap.createScaledBitmap(bitmap, (int) width, imageSize, false);
                            bitmapList.add(bitmap);
                        } catch (final Throwable t) {
                            t.printStackTrace();
                        }

                    }

                    callback.onSingleCallback(bitmapList, (int) interval);
                    mediaMetadataRetriever.release();
                } catch (final Throwable e) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), e);
                }
            }
        });
    }

}
