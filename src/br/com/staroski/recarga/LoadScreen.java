package br.com.staroski.recarga;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.jnlp.*;
import javax.swing.*;
import javax.swing.border.*;

public final class LoadScreen implements DownloadServiceListener {

	//	public static void main(String[] args) {
	//		try {
	//			LoadScreen ls = new LoadScreen();
	//			ls.progress(new URL("http://localhost"), "1.0", 10, 100, 10);
	//		} catch (Throwable e) {
	//			e.printStackTrace();
	//		}
	//	}

	/**
	 * A JNLP client's DownloadService implementation should call this method if a download fails or aborts unexpectedly.
	 */
	public void downloadFailed(URL url, String version) {}

	/**
	 * A JNLP client's DownloadService implementation should call this method several times during a download.
	 */
	public void progress(URL url, String version, long readSoFar, long total, int overallPercent) {
		updateProgressUI(overallPercent);
	}

	/**
	 * A JNLP client's DownloadService implementation should call this method at least several times when applying an incremental update to an in-cache
	 * resource.
	 */
	public void upgradingArchive(URL url, String version, int patchPercent, int overallPercent) {
		updateProgressUI(overallPercent);
	}

	/**
	 * A JNLP client's DownloadService implementation should call this method at least several times during validation of a download.
	 */
	public void validating(URL url, String version, long entry, long total, int overallPercent) {
		updateProgressUI(overallPercent);
	}

	private JFrame frame;
	private JProgressBar progressBar;
	private boolean uiCreated;

	private void create() {
		frame = new JFrame(Application.NAME);
		frame.setIconImage(Application.IMAGE_ICON);
		frame.setUndecorated(true);
		frame.setSize(480, 320);
		frame.setLocationRelativeTo(null);
		ImagePanel contentPane = new ImagePanel();
		frame.setContentPane(contentPane);
		contentPane.setImage(Application.IMAGE_SPLASH);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(100, 30));
		panel.add(progressBar, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Controle de Recarga de Muni\u00E7\u00F5es");
		lblNewLabel.setFont(new Font("Arial", lblNewLabel.getFont().getStyle() | Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);

		JLabel lblVendorUrl = new JLabel(Application.VENDOR_URL);
		lblVendorUrl.setForeground(Color.BLUE);
		lblVendorUrl.setFont(new Font("Arial", lblVendorUrl.getFont().getStyle() | Font.BOLD | Font.ITALIC, 12));
		lblVendorUrl.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVendorUrl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVendorUrl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				try {
					Desktop.getDesktop().browse(new URI(Application.VENDOR_URL));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(lblVendorUrl, BorderLayout.SOUTH);
		updateProgressUI(0);
	}

	private void updateProgressUI(int overallPercent) {
		if (overallPercent > 0 && overallPercent < 99) {
			if (!uiCreated) {
				uiCreated = true;
				// create custom progress indicator's UI only if there is more work to do,
				// meaning overallPercent > 0 and < 100 this prevents flashing when
				// RIA is loaded from cache
				create();
			}
			progressBar.setValue(overallPercent);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					frame.setVisible(true);
				}
			});
		} else {
			// hide frame when overallPercent is above 99
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					frame.setVisible(false);
					frame.dispose();
				}
			});
		}
	}
}
