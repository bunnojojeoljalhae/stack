package music.playlist.stack.repository;

import se.michaelthelin.spotify.requests.AbstractRequest;

public abstract class AbstractDataRequest<T> extends AbstractRequest<T> {

    protected AbstractDataRequest(final Builder<T, ?> builder) {
        super(builder);
    }

    public static abstract class Builder<T, BT extends Builder<T, ?>> extends AbstractRequest.Builder<T, BT> {
        protected Builder(String token) {
            super();

            assert (token != null);
            assert (!token.equals(""));

            setHeader("Authorization", "Bearer " + token);
            System.out.println("Abstract Data Request accessToken : " + token);
        }
    }

}
