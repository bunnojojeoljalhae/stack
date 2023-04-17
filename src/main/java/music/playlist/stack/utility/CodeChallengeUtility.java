package music.playlist.stack.utility;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class CodeChallengeUtility {

    public static String generate(final String codeVerifier) {
        byte[] digest = null;
        try {
            byte[] bytes = codeVerifier.getBytes("US-ASCII");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes, 0, bytes.length);
            digest = messageDigest.digest();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            log.error("Unable to generate code challenge {}", e);
            System.out.println("Unable to generate code challenge {} : " + e);
        }
        return Base64.getEncoder().withoutPadding().encodeToString(digest);
    }
}
