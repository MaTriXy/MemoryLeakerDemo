package com.elkriefy.apps.memoryleakerdemo.leakManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LeakSingletonManager {

	public static final int DELAY = 2000;
	// Singleton manager methods
	private static LeakSingletonManager INSTANCE = new LeakSingletonManager();
	public static LeakSingletonManager get() { return INSTANCE; }
	
	// Class variables
	private List<LeekListener> listeners;
	private Timer leekTimer;
	private boolean showingLeek;
	
	// Constructor
	public LeakSingletonManager() {
		listeners = new LinkedList<LeekListener>();
		showingLeek = false;
		leekTimer = new Timer();
		leekTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				shouldShowLeek();
			}
		}, DELAY, DELAY);
	}
	
	/**
	 * Simple method that will be called every so often 
	 * from the {@link #leekTimer}, to indicate to all listeners
	 * to show the leek. 
	 */
	private void shouldShowLeek() {
		// notify all listeners in current list
		for (LeekListener listener : listeners) {
			listener.onShowLeek(!showingLeek);
		}
		showingLeek = !showingLeek;
	}
	
	/**
	 * Add a listener to the pool of things that want a notification 
	 * when a leek should be shown.
	 * @param listener
	 */
	public void addListener(LeekListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes a listener from the pool of {@link LeekListener}s.
	 * @param listener
	 */
	public void removeListener(LeekListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * An interface that an object can implement if it would like
	 * a notificaiton of when a Leek should be shown from the {@link LeakSingletonManager}.
	 */
	public interface LeekListener {
		/**
		 * 
		 * @param show <code>true</code> if leek should be shown, <code>false</code> if it should be hidden
		 */
		public void onShowLeek(boolean show);
	}
	
}
