package com.zhouqiang.framework.media;

import android.media.MediaPlayer;

import com.zhouqiang.framework.util.Logger;

class VoicePlayer extends MediaPlayer {
	private static final String TAG = "VoicePlayer";

	private boolean prepared;
	private SanmiVoicePlayer baseVoicePlayer;

	private PreparedListener preparedListener;
	private CompletionListener completionListener;
	private SeekCompleteListener seekCompleteListener;
	private ErrorListener errorListener;

	VoicePlayer(SanmiVoicePlayer baseVoicePlayer) {
		this.baseVoicePlayer = baseVoicePlayer;
		this.preparedListener = new PreparedListener();
		this.completionListener = new CompletionListener();
		this.seekCompleteListener = new SeekCompleteListener();
		this.errorListener = new ErrorListener();
		setListener();
	}

	private void setListener() {
		setOnPreparedListener(preparedListener);
		setOnErrorListener(errorListener);
		setOnCompletionListener(completionListener);
		setOnSeekCompleteListener(seekCompleteListener);
	}

	@Override
	public void reset() {
		super.reset();
		prepared = false;
		setListener();
	}

	@Override
	public void stop() throws IllegalStateException {
		super.stop();
		reset();
	}

	private class PreparedListener implements OnPreparedListener {

		@Override
		public void onPrepared(MediaPlayer mp) {
			prepared = true;
		}
	}

	private class CompletionListener implements OnCompletionListener {

		@Override
		public void onCompletion(MediaPlayer mp) {
			baseVoicePlayer.cancelTimeThread();
			SanmiVoicePlayer.BaseVoicePlayListener listener = baseVoicePlayer
					.getBaseVoicePlayListener();
			if (listener != null)
				listener.onComplete(baseVoicePlayer);

		}
	}

	private class SeekCompleteListener implements OnSeekCompleteListener {

		@Override
		public void onSeekComplete(MediaPlayer mp) {
			Logger.i( "onSeekComplete");
		}
	}

	private class ErrorListener implements OnErrorListener {

		@Override
		public boolean onError(MediaPlayer mp, int what, int extra) {
			SanmiVoicePlayer.BaseVoicePlayListener listener = baseVoicePlayer
					.getBaseVoicePlayListener();
			if (listener != null)
				listener.onError(baseVoicePlayer);
			return false;
		}
	}

	public boolean isPrepared() {
		return prepared;
	}

}
