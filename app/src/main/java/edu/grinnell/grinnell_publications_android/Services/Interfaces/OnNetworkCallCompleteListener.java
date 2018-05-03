package edu.grinnell.grinnell_publications_android.Services.Interfaces;

/**
 * Callback listener for network calls.
 */
public interface OnNetworkCallCompleteListener {
    void onNetworkCallSucceeded();
    void onNetworkCallFailed(String message);
}
