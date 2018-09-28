package dictionary;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Audio {
	private static Audio audio;
	public synchronized static Audio getInstance() {

		if (audio == null) {
			audio = new Audio();
		}
		return audio;
	}
	//Singleton parttern
	
	public InputStream Speech(String inpuText,String languageOutput) throws IOException {

		URL url = new URL( UrlGoogle.GOOGLE_TRANSLATE_AUDIO+"ie=UTF-8&q="+inpuText+"&tl=en&client=tw-ob");
		URLConnection urlConn = url.openConnection();
		urlConn.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		InputStream audioSrc = urlConn.getInputStream();
		return new BufferedInputStream(audioSrc);
	}
	
	public void play(InputStream sound) throws JavaLayerException {
		new Player(sound).play();
	}

}
