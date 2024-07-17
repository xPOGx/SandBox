// Generated by view binder compiler. Do not edit!
package com.example.sandbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sandbox.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MusicItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView ivMusicImage;

  @NonNull
  public final ImageView ivMusicPlay;

  @NonNull
  public final TextView tvMusicArtist;

  @NonNull
  public final TextView tvMusicName;

  private MusicItemBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView ivMusicImage,
      @NonNull ImageView ivMusicPlay, @NonNull TextView tvMusicArtist,
      @NonNull TextView tvMusicName) {
    this.rootView = rootView;
    this.ivMusicImage = ivMusicImage;
    this.ivMusicPlay = ivMusicPlay;
    this.tvMusicArtist = tvMusicArtist;
    this.tvMusicName = tvMusicName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MusicItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MusicItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.music_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MusicItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_music_image;
      ImageView ivMusicImage = ViewBindings.findChildViewById(rootView, id);
      if (ivMusicImage == null) {
        break missingId;
      }

      id = R.id.iv_music_play;
      ImageView ivMusicPlay = ViewBindings.findChildViewById(rootView, id);
      if (ivMusicPlay == null) {
        break missingId;
      }

      id = R.id.tv_music_artist;
      TextView tvMusicArtist = ViewBindings.findChildViewById(rootView, id);
      if (tvMusicArtist == null) {
        break missingId;
      }

      id = R.id.tv_music_name;
      TextView tvMusicName = ViewBindings.findChildViewById(rootView, id);
      if (tvMusicName == null) {
        break missingId;
      }

      return new MusicItemBinding((ConstraintLayout) rootView, ivMusicImage, ivMusicPlay,
          tvMusicArtist, tvMusicName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
