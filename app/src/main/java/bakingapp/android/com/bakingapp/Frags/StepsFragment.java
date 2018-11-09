package bakingapp.android.com.bakingapp.Frags;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import bakingapp.android.com.bakingapp.Adapters.StepAdapter;
import bakingapp.android.com.bakingapp.Models.Recipe;
import bakingapp.android.com.bakingapp.Models.Steps;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment{

    private SimpleExoPlayer simpleExoPlayer;
    private long position;

    @BindView(R.id.simpleExoPlayer)
    SimpleExoPlayerView simpleExoPlayerView;

    @BindView(R.id.description)
    TextView description;
    Steps steps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps_fragment, null);

        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if (bundle != null) {
            steps = bundle.getParcelable(Constants.STEP_PARCABLE);
            description.setText(steps.getDescription());

            String videoUrl = steps.getVideoUrl();

            if (savedInstanceState != null) {
                position = savedInstanceState.getLong(Constants.VIDEO_POSITION);
            } else {
                position = 0;
            }

            if (!(videoUrl.equals(""))) {
                simpleExoPlayerView.setVisibility(View.VISIBLE);
                initializePlayer(Uri.parse(videoUrl), position);
            }
        }

        if (getActivity().getResources().getBoolean(R.bool.isTablet)) {
            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
//            Log.d("bundle",bundle.containsKey(Constants.STEP_PARCABLE)+"");
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (simpleExoPlayer != null) {
            outState.putLong(Constants.VIDEO_POSITION, simpleExoPlayer.getCurrentPosition());
        }
    }

    private void initializePlayer(Uri uri, long position) {
        if (simpleExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
            simpleExoPlayer.seekTo(position);
            simpleExoPlayerView.setPlayer(simpleExoPlayer);

            String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(uri,
                    new DefaultDataSourceFactory(getContext(), userAgent),
                    new DefaultExtractorsFactory(), null, null);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(false);

        }
    }

    private void releasePlayer() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.stop();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

}
