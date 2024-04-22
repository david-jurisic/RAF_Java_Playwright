package Maps.API;

public class SpotifyJsonMap {

    public static class Artist {
        public static final String searchArtistId = "artists.items[0].id";
        public static final String searchArtistName = "artists.items[0].name";
    }

    public static class Error {
        public static final String errorMessage = "error.message";
    }

    public static class Album {
        public static final String id = "id";
        public static final String availableMarkets = "available_markets";
    }

    public static class Playlist {
        public static final String description = "description";
        public static final String snapshotId = "snapshot_id";
        public static final String mostPopularSong = "tracks.items[0].track.name";
    }

    public static class Track {
        public static final String trackArtistName = "tracks.items[0].artists[0].name";
        public static final String itemsTrackArtistName = "items[0].track.artists[0].name";
        public static final String trackName = "tracks.items[0].name";
        public static final String itemsTrackName = "items[0].track.name";
        public static final String trackId = "tracks.items[0].id";
    }
}
