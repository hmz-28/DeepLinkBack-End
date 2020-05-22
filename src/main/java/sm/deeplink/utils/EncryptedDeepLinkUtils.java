package sm.deeplink.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
@Component
public class EncryptedDeepLinkUtils {
    //private static final String ALGORITHM = "AES";
    //private static final byte[] keyValue = "AZERTYUIOPQSDFGH".getBytes();
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String scrtKey = "AZERTYUIOPQSDFGH";

    public EncryptedDeepLinkUtils() {
    }

    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            //sha = MessageDigest.getInstance("AES");
           // key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
       /* catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encryteLink(String strToEncrypt)
    {
        try
        {
            setKey(scrtKey);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypteLink(String strToDecrypt)
    {
        try
        {
            setKey(scrtKey);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt.getBytes("UTF-8"))));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    public static void main(String[] args) throws Exception {

/*
        String path_link = "hote=maxgps.smartech-tn.com&port=9876&secured=0&timeout=60&username=maxadmin&password=maxadmin&pathDocLinks=" +
                "Attachments&storeroom=central&itemSet=set1&inspectionAddWo=1&qViewAsset=Asset%20with%20meter&qMeterReading=Asset%20with%20meter" +
                "&qReceiptPO=PoAppr&qViewPO=PoAppr&qWorkorderView=BT%20DES%207%20DERNIERS%20JOURS&qWorkorderInspection=inspection%20hebdomadaire&" +
                "qInventory=Central%20storeroom%20items&qSr=SR%20query%20for%20my%20reported%20SRs&sInventory=1&sInventoryAdjustments=1&sInventoryIssue=1" +
                "&sPurchaseOrder=1&sReceiptPO=1&sViewPO=1&sLaborReporting=1&sWorkOrders=1&sAddWO=1&sViewWO=1&sPerformInspection=1&sAssets=1&s" +
                "AddAssets=1&sViewAssets=1&sMeterReadingSec=1&sServiceRequests=1&sAddServiceRequest=1&sViewServiceRequests=1&version=48";
        String encrytedLink = encryteLink(path_link);
        System.out.println(encrytedLink);
        System.out.println("**********************************************************************************");
        String decrypteLink=decrypteLink(encrytedLink);
        System.out.println(decrypteLink);*/
    }
}
