package sm.deeplink.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
@Component
public class EncryptedDeepLinkUtils {
    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "AZERTYUIOPQSDFGH".getBytes();

    public EncryptedDeepLinkUtils() {
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }

    // Encryte Password with AES
    public static String encryteLink(String path_link) {
        String encryptedValue = "";
        try {

          /*  File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String valueToEnc ="";

            while ((valueToEnc = bufferedReader.readLine())!=null)
            {*/


            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //  System.out.println("valueToEnc.getBytes().length "+valueToEnc.getBytes().length);
            byte[] encValue = cipher.doFinal(path_link.getBytes("UTF-8"));
            //   System.out.println("encValue length" + path_link.length);
            byte[] encryptedByteValue = Base64.getEncoder().encode(encValue);
            encryptedValue = Base64.getEncoder().encodeToString(encValue);
            //System.out.println("encryptedValue " + encryptedByteValue);

            String output = new String(encryptedValue);
            System.out.println("encryptedValue " + output);
              /*  byte[]data = output.getBytes();
                FileOutputStream fos = new FileOutputStream(path.substring(0, path.lastIndexOf("\\")) + "\\Configuration.txt");
                fos.write(data);
                fos.close();*/


            //  }

            // System.out.println("test input"+ new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue))));

        } catch (Exception e) {

        }
        return encryptedValue;
    }

    public static String decrypteLink(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        /* byte[] decodeValue = cipher.doFinal(encryptedByteValue);
            byte[] decryptedByteValue = Base64.getDecoder().decode(decodeValue);
            String decryptedValue = decryptedByteValue.toString();
            String input = new String(decryptedValue);
            System.err.println("decryptedValue " + decryptedByteValue);*/
        // String originalInput = encryptedValue;
        //byte[] result = Base64.getDecoder().decode(originalInput);

        //System.out.println("test input"+ new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue))));
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)));
    }

    public static void main(String[] args) throws Exception {
        String path_link = "hote=maxgps.smartech-tn.com&port=9876&secured=0&timeout=60&username=maxadmin&password=maxadmin&pathDocLinks=" +
                "Attachments&storeroom=central&itemSet=set1&inspectionAddWo=1&qViewAsset=Asset%20with%20meter&qMeterReading=Asset%20with%20meter" +
                "&qReceiptPO=PoAppr&qViewPO=PoAppr&qWorkorderView=BT%20DES%207%20DERNIERS%20JOURS&qWorkorderInspection=inspection%20hebdomadaire&" +
                "qInventory=Central%20storeroom%20items&qSr=SR%20query%20for%20my%20reported%20SRs&sInventory=1&sInventoryAdjustments=1&sInventoryIssue=1" +
                "&sPurchaseOrder=1&sReceiptPO=1&sViewPO=1&sLaborReporting=1&sWorkOrders=1&sAddWO=1&sViewWO=1&sPerformInspection=1&sAssets=1&s" +
                "AddAssets=1&sViewAssets=1&sMeterReadingSec=1&sServiceRequests=1&sAddServiceRequest=1&sViewServiceRequests=1&version=48";
        String encrytedLink = encryteLink(path_link);
        System.out.println(decrypteLink(encrytedLink));
    }
}
