package gov.mt.dnrc.toggle.core.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * QR Code Generation Utility Tool for working with QR Code Images
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public class QRUtility {

    private static final Logger logger = LogManager.getLogger(QRUtility.class);

    private static final String QR_IMAGE_TYPE = "png";
    private static final String CHARSET_TYPE = "UTF-8";

    /**
     * Default Constructor
     */
    private QRUtility() {}

    /**
     * Converts the object into a encoded image object that is a QR Code
     *
     * @param object The object that will be converted to a QR Code.
     * @return Returns a Base64 Encoded String of the QR Code image. Returns null if there was a problem or no image.
     */
    public static String retrieveQRCodeImage(Object object) {

        // Preliminary check of object.
        if(object == null || object.toString().isEmpty()) {
            return null;
        }

        // Try with resources building the byte array stream for generating the encoded image file.
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            // Set the Hint Type encoding properties for the bit matrix encoder
            Map<EncodeHintType, Object> enumMap = new EnumMap<>(EncodeHintType.class);
            enumMap.put(EncodeHintType.CHARACTER_SET, CHARSET_TYPE);
            enumMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            enumMap.put(EncodeHintType.MARGIN, 4);

            int matrixWidth = 250;
            int matrixHeight = 250;

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(object.toString(), BarcodeFormat.QR_CODE, matrixWidth, matrixHeight, enumMap);

            int width = byteMatrix.getWidth();
            int height = byteMatrix.getHeight();

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            // Image pixel settings.
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            // Create the QR code based on the current pixel your working with.
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // Write the file to the output stream.
            ImageIO.write(bufferedImage, QR_IMAGE_TYPE, byteArrayOutputStream);

            // Encode the image so we can view it from the web
            byte[] encodeBase64 = Base64.encodeBase64(byteArrayOutputStream.toByteArray());

            // Build the image source format so the browser can easily view the image.
            StringBuilder encodedImage = new StringBuilder();
            encodedImage.append("data:image/png;base64,");
            encodedImage.append(new String(encodeBase64, CHARSET_TYPE));

            // Return the image string result.
            return encodedImage.toString();
        } catch (WriterException | IOException e) {
            logger.fatal("Unable to write the QR Image: {}", e);
        }

        // If anything at all happens unexpected, return null for handling.
        return null;
    }
}
