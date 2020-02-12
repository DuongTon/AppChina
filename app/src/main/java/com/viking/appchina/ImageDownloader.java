package com.viking.appchina;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public interface ImageDownloader {

    public enum Scheme {
        HTTP("http"),
        HTTPS("https"),
        FILE("file"),
        CONTENT("content"),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN("");
        
        private String scheme;
        private String uriPrefix;

        private Scheme(String str) {
            this.scheme = str;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("://");
            this.uriPrefix = sb.toString();
        }

        public static Scheme ofUri(String str) {
            Scheme[] values;
            if (str != null) {
                for (Scheme scheme2 : values()) {
                    if (scheme2.belongsTo(str)) {
                        return scheme2;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean belongsTo(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.uriPrefix);
        }

        public String wrap(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.uriPrefix);
            sb.append(str);
            return sb.toString();
        }

        public String crop(String str) {
            if (belongsTo(str)) {
                return str.substring(this.uriPrefix.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.scheme}));
        }
    }

    InputStream getStream(String str, Object obj) throws IOException;
}
