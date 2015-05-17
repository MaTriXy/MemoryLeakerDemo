package com.elkriefy.apps.memoryleakerdemo.leakManager;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkriefy.apps.memoryleakerdemo.MemoryleakDemo;
import com.elkriefy.apps.memoryleakerdemo.R;
import com.squareup.leakcanary.RefWatcher;

public class MainSingletonLeakActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leek);

		if (savedInstanceState == null) {

			getFragmentManager().beginTransaction().add(R.id.container, new LeekFragment()).commit();
		}
		RefWatcher refWatcher = MemoryleakDemo.getRefWatcher(getApplication());

		refWatcher.watch(this);
	}

	public static class LeekFragment extends Fragment implements LeakSingletonManager.LeekListener {

		private View leek;

		public LeekFragment() {
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			// Add this fragment as a leek listener so
			// it can update its view accordingly
			RefWatcher refWatcher = MemoryleakDemo.getRefWatcher(getActivity());
			refWatcher.watch(LeakSingletonManager.get());
			LeakSingletonManager.get().addListener(this);
		}


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_leek, container, false);

			leek = rootView.findViewById(R.id.leek);

			return rootView;
		}

		@Override
		public void onShowLeek(final boolean show) {
			Log.d("test", "Show leek? " + show);
			if (leek != null) {
				// need to update views on UI thread
				leek.post(new Runnable() {
					@Override
					public void run() {
						if (leek != null) {
							leek.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
						}
					}
				});
			}
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			RefWatcher refWatcher = MemoryleakDemo.getRefWatcher(getActivity());
			refWatcher.watch(this);
		}
	}
}
