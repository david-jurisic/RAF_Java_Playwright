package Maps.API;

import ExtendedTypes.API.QueryStringEx;
import org.raf3k.apitesting.APIReferences;

import java.text.MessageFormat;

public class SpotifyAPIMap {
    public static void initialize() {
        APIReferences.currentPageContext = "https://accounts.spotify.com/api";
    }
    public static void initializeRequests() {
        APIReferences.currentPageContext = "https://api.spotify.com/v1";
    }

    public static class Token {
        public static QueryStringEx request() {
            return new QueryStringEx("/token");
        }
    }

    public static class AlbumsAndSongs {
        public static QueryStringEx albums(String albumId) {
            return new QueryStringEx(MessageFormat.format("/albums/{0}", albumId));
        }
    }

    public static class API {
        public static QueryStringEx search() {
            return new QueryStringEx("/search");
        }

        public static QueryStringEx artistFollow() {
            return new QueryStringEx("/me/following");
        }
    }

    public static class Playlists {
        public static QueryStringEx Playlist(String PlaylistId) {
            return new QueryStringEx(MessageFormat.format("/playlists/{0}", PlaylistId));
        }

        public static QueryStringEx AddPlaylistTracks(String PlaylistId) {
            return new QueryStringEx(MessageFormat.format("/playlists/{0}/tracks?position=0&uris=spotify:track:4iV5W9uYEdYUVa79Axb7Rh,spotify:track:1301WleyT98MSxVHPZCA6M", PlaylistId));
        }

        public static QueryStringEx RemovePlaylistTracks(String PlaylistId) {
            return new QueryStringEx(MessageFormat.format("/playlists/{0}/tracks", PlaylistId));
        }
    }

    public static class Tracks {
        public static QueryStringEx Tracks() {
            return new QueryStringEx("/me/tracks");
        }
    }
}