package bakingapp.android.com.bakingapp.Frags;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
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

import bakingapp.android.com.bakingapp.Models.Steps;
import bakingapp.android.com.bakingapp.R;
import bakingapp.android.com.bakingapp.Utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsFragment extends Fragment {

    private SimpleExoPlayer simpleExoPlayer;
    private long position;

    @BindView(R.id.simpleExoPlayer)
    SimpleExoPlayerView simpleExoPlayerView;

    @BindView(R.id.description)
    TextView description;

    Steps steps;
    String videoUrl;
    String descrip;

    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.steps_fragment, null);

        ButterKnife.bind(this, view);

        bundle = getArguments();
        if (bundle != null) {
            steps = bundle.getParcelable(Constants.STEP_PARCABLE);


            videoUrl = steps.getVideoUrl();
            Log.d("videourl",videoUrl);

            descrip = steps.getDescription();
            description.setText(descrip);

            if (!(videoUrl.equals(""))) {
                simpleExoPlayerView.setVisibility(View.VISIBLE);
                initializePlayer(videoUrl, position);
            }
        }

        if (getActivity().getResources().getBoolean(R.bool.isTablet)) {
            Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            position = savedInstanceState.getLong(Constants.VIDEO_POSITION);
            steps = savedInstanceState.getParcelable(Constants.VIDEO_URL_KEY);
        } else {
            position = 0;
            steps =bundle.getParcelable(Constants.STEP_PARCABLE);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (simpleExoPlayer != null) {
            outState.putLong(Constants.VIDEO_POSITION, simpleExoPlayer.getCurrentPosition());
        }
        outState.putParcelable(Constants.VIDEO_URL_KEY,steps);
    }

    private void initializePlayer(String uri, long position) {
        if (simpleExoPlayer == null) {
            if (uri != null) {
                Uri ur = Uri.parse(uri);
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                simpleExoPlayer.seekTo(position);
                simpleExoPlayerView.setPlayer(simpleExoPlayer);

                String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
                MediaSource mediaSource = new ExtractorMediaSource(ur,
                        new DefaultDataSourceFactory(getContext(), userAgent),
                        new DefaultExtractorsFactory(), null, null);
                simpleExoPlayer.prepare(mediaSource);
                simpleExoPlayer.setPlayWhenReady(false);
            }
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

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 && simpleExoPlayer == null)) {
            initializePlayer(videoUrl,position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(videoUrl,position);
        }
    }

}
